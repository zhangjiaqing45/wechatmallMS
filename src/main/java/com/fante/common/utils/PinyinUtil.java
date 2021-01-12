package com.fante.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description:
 */
public class PinyinUtil {

    private static final Logger log = LoggerFactory.getLogger(PinyinUtil.class);


    /**
     * 得到用于搜索的拼音
     * @param str
     * @return
     */
    public static String getSearchPinyin(String str)
    {
        return new StringBuilder()
                .append(getPinyinHeadChar(str))
                .append("|")
                .append(getPinyin(str))
                .toString();
    }

    /**
     * 将汉字转换为全拼
     *
     * @param src
     * @return
     */
    public static String getPinyin(String src)
    {
        String[] pinyin;
        char[] hanZiArr = src.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();         // 设置汉字拼音输出的格式
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);                      // 设置转换成小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);                   // 设置不要发音
        format.setVCharType(HanyuPinyinVCharType.WITH_V);                       // 设置读音 ü 变成 v
        StringBuffer pinyinStr = new StringBuffer();                            //存放拼音字符串
        try {
            for (int i = 0; i < hanZiArr.length; i++) {
                if (Character.toString(hanZiArr[i]).matches("[\\u4E00-\\u9FA5]+")) {  //判断是否为汉字字符
                    pinyin = PinyinHelper.toHanyuPinyinStringArray(hanZiArr[i], format);
                    pinyinStr.append(pinyin[0]);
                } else {
                    pinyinStr.append(Character.toString(hanZiArr[i]));           //如果不是汉字字符，维持原样
                }
            }
        }catch (Exception e) {
            log.warn(e.getMessage());
        }
        return pinyinStr.toString();
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return
     */
    public static String getPinyinHeadChar(String str)
    {
        StringBuffer convert = new StringBuffer();
        try {
            for (int i = 0; i < str.length(); i++)
            {
                char word = str.charAt(i);
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);     // 提取汉字的首字母
                if (pinyinArray != null) {
                    convert.append(pinyinArray[0].charAt(0));
                } else {
                    convert.append(word);
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return convert.toString().toLowerCase();
    }
}
