package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

@Deprecated
public class FieldBirthCommand implements Command<String> {
    private String cardId;

    public FieldBirthCommand(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 根据身份证号码获取出生日期
     *
     * @param context
     * @return
     */
    @Override
    public String execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(cardId), "身份证号码不能为空");
        String returnValue = new FieldCheckCardIdCommand(cardId).execute(context);
        if (returnValue.indexOf("该身份证有效") != -1) {
            String Ai = "";
            if (cardId.length() == 18) {
                Ai = cardId.substring(0, 17);
            } else if (cardId.length() == 15) {
                Ai = cardId.substring(0, 6) + "19" + cardId.substring(6, 15);
            }
            // 判断出生年月是否有效
            String strYear = Ai.substring(6, 10);// 年份
            String strMonth = Ai.substring(10, 12);// 月份
            String strDay = Ai.substring(12, 14);// 日期
            return strYear + strMonth + strDay;
        } else {
            return returnValue;
        }
    }
}
