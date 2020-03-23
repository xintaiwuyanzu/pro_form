package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class FieldCheckCardIdCommand implements Command<String> {
    private String cardId;

    public FieldCheckCardIdCommand(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 身份证校验
     *
     * @param context
     * @return
     */
    @Override
    public String execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(cardId), "身份证号码不能为空");
        String tipInfo = "该身份证有效！";
        String Ai = "";
        if (cardId.length() != 15 && cardId.length() != 18) {
            tipInfo = "身份证号码长度应该为15位或18位。";
            return tipInfo;
        }
        if (cardId.length() == 18) {
            Ai = cardId.substring(0, 17);
        } else if (cardId.length() == 15) {
            Ai = cardId.substring(0, 6) + "19" + cardId.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            tipInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return tipInfo;
        }
        // 判断出生年月是否有效
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 日期
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            tipInfo = "身份证出生日期无效。";
            return tipInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                tipInfo = "身份证生日不在有效范围。";
                return tipInfo;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            tipInfo = "身份证月份无效";
            return tipInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            tipInfo = "身份证日期无效";
            return tipInfo;
        }
        // 判断地区码是否有效
        //如果身份证前两位的地区码不在Hashtable，则地区码有误
        if (areaTable.get(Ai.substring(0, 2)) == null) {
            tipInfo = "身份证地区编码错误。";
            return tipInfo;
        }
        if (isVarifyCode(Ai, cardId) == false) {
            tipInfo = "身份证校验码无效，不是合法的身份证号码";
            return tipInfo;
        }
        return tipInfo;
    }

    /**
     * 判断字符串是否为数字,0-9重复0次或者多次
     *
     * @param strnum
     * @return
     */
    private static boolean isNumeric(String strnum) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(strnum);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    static final Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");

    /**
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     *
     * @param
     * @return
     */
    public static boolean isDate(String strDate) {
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private static final Hashtable<String, String> areaTable;

    static {
        areaTable = new Hashtable();
        areaTable.put("11", "北京");
        areaTable.put("12", "天津");
        areaTable.put("13", "河北");
        areaTable.put("14", "山西");
        areaTable.put("15", "内蒙古");
        areaTable.put("21", "辽宁");
        areaTable.put("22", "吉林");
        areaTable.put("23", "黑龙江");
        areaTable.put("31", "上海");
        areaTable.put("32", "江苏");
        areaTable.put("33", "浙江");
        areaTable.put("34", "安徽");
        areaTable.put("35", "福建");
        areaTable.put("36", "江西");
        areaTable.put("37", "山东");
        areaTable.put("41", "河南");
        areaTable.put("42", "湖北");
        areaTable.put("43", "湖南");
        areaTable.put("44", "广东");
        areaTable.put("45", "广西");
        areaTable.put("46", "海南");
        areaTable.put("50", "重庆");
        areaTable.put("51", "四川");
        areaTable.put("52", "贵州");
        areaTable.put("53", "云南");
        areaTable.put("54", "西藏");
        areaTable.put("61", "陕西");
        areaTable.put("62", "甘肃");
        areaTable.put("63", "青海");
        areaTable.put("64", "宁夏");
        areaTable.put("65", "新疆");
        areaTable.put("71", "台湾");
        areaTable.put("81", "香港");
        areaTable.put("82", "澳门");
        areaTable.put("91", "国外");
    }

    /**
     * 判断第18位校验码是否正确
     * 第18位校验码的计算方式：  1. 对前17位数字本体码加权求和公式为：S = Sum(Ai * Wi), i = 0, ... , 16其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2. 用11对计算结果取模 Y = mod(S, 11)
     * 3. 根据模的值得到对应的校验码 对应关系为： Y值：     0  1  2  3  4  5  6  7  8  9  10 　校验码： 1  0  X  9  8  7  6  5  4  3   2
     *
     * @param Ai
     * @param IDStr
     * @return
     */
    private static boolean isVarifyCode(String Ai, String IDStr) {
        String[] VarifyCode = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = VarifyCode[modValue];
        Ai = Ai + strVerifyCode;
        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                return false;
            }
        }
        return true;
    }

}
