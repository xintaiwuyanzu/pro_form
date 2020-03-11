package com.dr.framework.common.form;

import com.dr.framework.common.form.engine.CommandExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = FormApplication.class)
@RunWith(SpringRunner.class)
public class AbstractApplicationTest {
    Logger logger = LoggerFactory.getLogger(AbstractApplicationTest.class);

    @Autowired
    CommandExecutor commandExecutor;

    @Test
    public void testInit() {
        logger.info(commandExecutor.toString());
    }

}
