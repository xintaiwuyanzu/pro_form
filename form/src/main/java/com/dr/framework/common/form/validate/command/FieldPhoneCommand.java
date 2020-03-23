package com.dr.framework.common.form.validate.command;

import com.dr.framework.common.form.engine.Command;
import com.dr.framework.common.form.engine.CommandContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import static com.dr.framework.common.form.util.Constans.isChinaPhoneLegal;
import static com.dr.framework.common.form.util.Constans.isHKPhoneLegal;

@Deprecated
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


}
