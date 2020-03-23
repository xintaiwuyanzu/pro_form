package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
@Deprecated
public class FieldSexCommand implements Command<String> {
    private String cardId;

    public FieldSexCommand(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 根据身份证号获取性别
     *
     * @param context
     * @return
     */
    @Override
    public String execute(CommandContext context) {
        Assert.isTrue(StringUtils.isNotEmpty(cardId), "身份证号码不能为空");
        String returnValue = new FieldCheckCardIdCommand(cardId).execute(context);
        if (returnValue.indexOf("该身份证有效") != -1) {
            String sex = "";
            if (cardId.length() == 18) {
                // 判断性别
                if (Integer.parseInt(cardId.substring(16).substring(0, 1)) % 2 == 0) {
                    sex = "0";
                } else {
                    sex = "1";
                }
            } else if (cardId.length() == 15) {
                String uSex = cardId.substring(14, 15);
                if (Integer.parseInt(uSex) % 2 == 0) {
                    sex = "0";
                } else {
                    sex = "1";
                }
            }
            return sex;
        } else {
            return returnValue;
        }
    }
}
