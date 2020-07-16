package com.dr.framework.common.form.util;

import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author dr
 */
public class StringUtils {

    static final String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q",
            "r", "s", "t",
            "u", "v", "w",
            "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    /**
     * <li>短8位UUID思想其实借鉴微博短域名的生成方式，但是其重复概率过高，而且每次生成4个，需要随即选取一个。
     * <li>本算法利用56个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，然后通过模36操作，结果作为索引取出字符，
     * <li>这样重复率大大降低。
     * <li>建议:表的编码字段加唯一索引
     *
     * @return
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x24]);
        }
        return shortBuffer.toString();
    }
}
