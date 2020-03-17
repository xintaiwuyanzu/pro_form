package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FieldPhoneCommand implements Command<Boolean> {
    private String phoneLegal;

    public FieldPhoneCommand(String phoneLegal) {
        this.phoneLegal = phoneLegal;
    }

    /**
     * 手机号校验
     *
     * @param context
     * @return
     */
    @Override
    public Boolean execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(phoneLegal), "电话号码不能为空");
        return isChinaPhoneLegal(phoneLegal) || isHKPhoneLegal(phoneLegal);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
