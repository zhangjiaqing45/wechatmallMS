package com.fante.framework.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fante.common.utils.StringUtils;
import com.fante.framework.jwt.domain.JwtToken;
import com.fante.framework.jwt.enums.JwtEnum;
import com.fante.framework.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: JWT工具类
 */
@Component
public class JwtUtils {

    private static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private RedisUtil redisUtil;

    /** 自定义声明：用户标识键值 */
    private static final String KEY_USER_ID = "userId";
    /** 自定义声明：用户端标识键值 */
    private static final String KEY_CLIENT_ID = "clientId";
    /** 自定义声明：用户端来源 */
    private static final String KEY_SOURCE = "source";

    /** 密匙：自定义 */
    @Value("${jwt.jwtSecretKey}")
    private String jwtSecretKey;
    /** 过期时间（秒） */
    @Value("${jwt.jwtExpireTime}")
    private long jwtExpireTime;
    /** Token前缀 */
    @Value("${jwt.jwtPrefix}")
    private String jwtPrefix;

    /**
     * 生成Token
     * @param jwt
     * @return
     */
    public String createToken(JwtToken jwt) {
        // 当前时间毫秒
        long now = System.currentTimeMillis();
        // 加密算法
        Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
        // 生成Token
        String token = JWT.create()
                .withClaim(KEY_USER_ID, jwt.getUserId())
                .withClaim(KEY_CLIENT_ID, jwt.getClientId())
                .withClaim(KEY_SOURCE, jwt.getSource())
                .withExpiresAt(new Date(now + jwtExpireTime * 1000))
                .sign(algorithm);
        // Redis缓存Token，过期时间与JWT过期时间一致
        redisUtil.set(buildKey(jwtPrefix, jwt.getSource(), jwt.getUserId()), token, jwtExpireTime);
        return token;
    }

    /**
     * 校验Token
     * @param token
     * @return
     */
    public JwtEnum verifyToken(String token) {
        try {
            if (StringUtils.isBlank(token)) {
                // token信息不存在
                return JwtEnum.TOKEN_NULL;
            }
            // 获取Redis中Token缓存
            String redisKey = buildKey(jwtPrefix, getSourceByToken(token), getUserIdByToken(token));
            Object tokenObj = redisUtil.get(redisKey);
            if (ObjectUtils.isEmpty(tokenObj)) {
                // 未获取到Redis中Token信息
                return JwtEnum.EXPIRE;
            }
            String redisToken = String.valueOf(tokenObj);
            if (!Objects.equals(redisToken, token)) {
                // Token信息与Redis中的不一致
                return JwtEnum.KICK_OUT;
            }
            // 当前时间毫秒
            long now = System.currentTimeMillis();
            // 加密算法
            Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
            // 得到算法相同的JWTVerifier
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(KEY_USER_ID, getUserIdByToken(token))
                    .withClaim(KEY_CLIENT_ID, getClientIdByToken(token))
                    .acceptExpiresAt(now + jwtExpireTime * 1000)
                    .build();
            // 验证token
            verifier.verify(redisToken);
            // Redis缓存JWT续期
            redisUtil.set(redisKey, token, jwtExpireTime);
            return JwtEnum.SUCCESS;
        } catch (Exception e) {
            //捕捉到任何异常都视为校验失败
            log.error(e.toString());
            return JwtEnum.FAIL;
        }
    }

    /**
     * 构建RedisKey
     * @param jwtPrefix
     * @param source
     * @param userId
     * @return
     */
    private String buildKey(String jwtPrefix, String source, long userId) {
        return new StringBuilder()
                .append(jwtPrefix)
                .append(source).append("-")
                .append(userId).toString();
    }

    /**
     * 根据Token获取userId
     * @param token
     * @return
     */
    public long getUserIdByToken(String token) {
        return JWT.decode(token).getClaim(KEY_USER_ID).asLong();
    }

    /**
     * 根据Token获取clientId
     * @param token
     * @return
     */
    public String getClientIdByToken(String token) {
        return JWT.decode(token).getClaim(KEY_CLIENT_ID).asString();
    }

    /**
     * 根据Token获取source
     * @param token
     * @return
     */
    public String getSourceByToken(String token) {
        return JWT.decode(token).getClaim(KEY_SOURCE).asString();
    }
}
