package com.sean.partner.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sean on 2017/4/15.
 */

public class StringUtils {


    //判断字符串是否不为空
    public static boolean isNotBlank(String value) {
        return value != null && !value.trim().equals("");
    }

    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i : ch) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     *
     * 检查字符串是否在数字英文中文范围内
     *
     *
     * 各种字符的unicode编码的范围：
     * 汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
     * 数字：[0x30,0x39]（或十进制[48, 57]）
     * 小写字母：[0x61,0x7a]（或十进制[97, 122]）
     * 大写字母：[0x41,0x5a]（或十进制[65, 90]）
     * */
    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }

    /**
     * http://blog.csdn.net/cnmilan/article/details/50779861
     *正则表达式检查邮箱有效性
     *合法E-mail地址：
     1. 必须包含一个并且只有一个符号“@”
     2. 第一个字符不得是“@”或者“.”
     3. 不允许出现“@.”或者.@
     4. 结尾不得是字符“@”或者“.”
     5. 允许“@”前的字符中出现“＋”
     6. 不允许“＋”在最前面，或者“＋@”
     * */
    public static boolean isConformEmailAddr(String email){
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().equals("");
    }

    public static boolean isMobileNumber(String str) {
        if (isBlank(str)) return false;
        try {
            Pattern p = Pattern.compile("^1\\d{10}$");
//            Pattern p = Pattern.compile("^((13)|(14)|(15)|(18)|(17))\\d{9}$");
            Matcher m = p.matcher(str);
            return m.matches();
        } catch (Exception e) {
            return false;
        }
    }
}
