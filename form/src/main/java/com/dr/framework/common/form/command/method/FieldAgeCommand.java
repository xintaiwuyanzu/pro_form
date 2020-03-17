package com.dr.framework.common.form.command.method;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FieldAgeCommand implements Command<String> {
    private String cardId;

    public FieldAgeCommand(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 根据身份证号获取年龄
     *
     * @param context
     * @return
     */
    @Override
    public String execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(cardId), "身份证号码不能为空");
        String returnValue = new FieldCheckCardIdCommand(cardId).execute(context);
        if (returnValue.indexOf("该身份证有效") != -1) {
            String birthday = cardId.substring(6, 10) + "/"
                    + cardId.substring(10, 12) + "/"
                    + cardId.substring(12, 14);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date now = new Date();
            Date birth = new Date();
            try {
                birth = sdf.parse(birthday);
            } catch (ParseException e) {
            }
            long intervalMilli = now.getTime() - birth.getTime();
            int age = (int) (intervalMilli / (24 * 60 * 60 * 1000)) / 365;
            return age + "";
        } else {
            return returnValue;
        }
    }
}
