package com.dr.framework.common.form;

import com.dr.framework.core.orm.support.mybatis.spring.boot.autoconfigure.EnableAutoMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dr")
@EnableAutoMapper(basePackages = "com.dr")
public class FormApplication {
}
