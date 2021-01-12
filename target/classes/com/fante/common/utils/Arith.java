package com.fante.common.utils;

import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 精确的浮点数运算
 *
 * @author fante
 */
public class Arith {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 这个类不能实例化
     */
    private Arith() {
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        if (b1.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.doubleValue();
        }
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }


    private static String ZERO = "0";

    private static int DEF_SCALE = 2;

    /**
     * 加法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(String v1, String v2) {
        return add(v1, v2, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法
     * @param v1
     * @param v2
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal add(String v1, String v2, int newScale, int roundingMode) {
        v1 = StringUtils.isBlank(v1) ? ZERO : v1;
        v2 = StringUtils.isBlank(v2) ? ZERO : v2;
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(newScale, roundingMode);
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal sub(String v1, String v2) {
        return sub(v1, v2, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal sub(String v1, String v2, int newScale, int roundingMode) {
        v1 = StringUtils.isBlank(v1) ? ZERO : v1;
        v2 = StringUtils.isBlank(v2) ? ZERO : v2;
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).setScale(newScale, roundingMode);
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal mul(String v1, String v2){
        return mul(v1, v2, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal mul(String v1, String v2, int newScale, int roundingMode) {
        v1 = StringUtils.isBlank(v1) ? ZERO : v1;
        v2 = StringUtils.isBlank(v2) ? ZERO : v2;
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(newScale, roundingMode);
    }

    /**
     * 除法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal div(String v1, String v2) {
        return div(v1, v2, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法
     * @param v1
     * @param v2
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal div(String v1, String v2, int newScale, int roundingMode) {
        Checker.check(StringUtils.isBlank(v1) || StringUtils.isBlank(v2), "请传入被除数和除数");
        Checker.check(ZERO.equals(v2), "除数不能为0");
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, newScale, roundingMode);
    }




    /**
     * 加法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        return add(v1, v2, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法
     * @param v1
     * @param v2
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2, int newScale, int roundingMode) {
        v1 = ObjectUtils.isEmpty(v1) ? BigDecimal.ZERO : v1;
        v2 = ObjectUtils.isEmpty(v2) ? BigDecimal.ZERO : v2;
        return v1.add(v2).setScale(newScale, roundingMode);
    }


    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        return sub(v1, v2, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2, int newScale, int roundingMode) {
        v1 = ObjectUtils.isEmpty(v1) ? BigDecimal.ZERO : v1;
        v2 = ObjectUtils.isEmpty(v2) ? BigDecimal.ZERO : v2;
        return v1.subtract(v2).setScale(newScale, roundingMode);
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2){
        return mul(v1, v2, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2, int newScale, int roundingMode) {
        v1 = ObjectUtils.isEmpty(v1) ? BigDecimal.ZERO : v1;
        v2 = ObjectUtils.isEmpty(v2) ? BigDecimal.ZERO : v2;
        return v1.multiply(v2).setScale(newScale, roundingMode);
    }

    /**
     * 除法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return div(v1, v2, DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法
     * @param v1
     * @param v2
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int newScale, int roundingMode) {
        Checker.check(ObjectUtils.isEmpty(v1) || ObjectUtils.isEmpty(v2), "请传入被除数和除数");
        Checker.check(Objects.equals(v2, BigDecimal.ZERO), "除数不能为0");
        return v1.divide(v2, newScale, roundingMode);
    }

    /**
     * Int正数转负数
     * @param target
     * @return
     */
    public static Integer negate(Integer target) {
        return target * (-1);
    }

}
