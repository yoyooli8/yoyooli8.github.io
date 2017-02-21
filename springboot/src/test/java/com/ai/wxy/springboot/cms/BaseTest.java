package com.ai.wxy.springboot.cms;

import java.util.Calendar;
import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ai.wxy.frame.springboot.ApplicationStarter;
import com.ai.wxy.frame.springboot.services.cms.beans.BaseBean;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
@WebAppConfiguration
public abstract class BaseTest{
    protected void setCreateInfo(BaseBean bean){
        Date now = Calendar.getInstance().getTime();
        bean.setCreaterUid(0);
        bean.setCreaterUname("testUser");
        bean.setCreateTime(now);
        bean.setUpdateTime(now);
        bean.setUpdateUid(0);
        bean.setUpdateUname("testUser");
    }
    protected void setUpdInfo(BaseBean bean){
        Date now = Calendar.getInstance().getTime();
        bean.setUpdateTime(now);
        bean.setUpdateUid(1);
        bean.setUpdateUname("testUpdUser");
    }
}
