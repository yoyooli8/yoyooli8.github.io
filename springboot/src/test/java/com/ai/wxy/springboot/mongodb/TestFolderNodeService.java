package com.ai.wxy.springboot.mongodb;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ai.wxy.frame.springboot.ApplicationStarter;
import com.ai.wxy.frame.springboot.services.cms.beans.BaseBean;
import com.ai.wxy.frame.springboot.services.cms.beans.FolderNode;
import com.ai.wxy.frame.springboot.services.cms.service.FolderNodeServiceI;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
@WebAppConfiguration
public class TestFolderNodeService{
    @Autowired
    private FolderNodeServiceI folderService;
    
    public void testSaveFolder(){
        FolderNode folder = new FolderNode();
        folder.setName("品牌");
        folder.setFolderFlag("brand");
        folder.setDesc("手机品牌");
        folder.setValide(1);
        setCreateInfo(folder);
        
        String folderUid = folderService.saveFolderNode(folder);
        Assert.assertNotNull(folderUid);
        System.out.println("--58a3cb5d6f71a999101272a0---------------------->"+folderUid);
    }
    @Test
    public void testUpdFolder(){
        String uuid = "58a3cb5d6f71a999101272a0";
        FolderNode folder = new FolderNode();
        folder.setName("品牌");
        folder.setFolderFlag("brand");
        folder.setDesc("手机品牌---->");
        folder.setValide(1);
        folder.setUuId(uuid);
        setUpdInfo(folder);
        
        boolean rtn = folderService.updFolder(folder);
        Assert.assertTrue(rtn);
    }
    private void setCreateInfo(BaseBean bean){
        Date now = Calendar.getInstance().getTime();
        bean.setCreaterUid(0);
        bean.setCreaterUname("testUser");
        bean.setCreateTime(now);
        bean.setUpdateTime(now);
        bean.setUpdateUid(0);
        bean.setUpdateUname("testUser");
    }
    private void setUpdInfo(BaseBean bean){
        Date now = Calendar.getInstance().getTime();
        bean.setUpdateTime(now);
        bean.setUpdateUid(1);
        bean.setUpdateUname("testUpdUser");
    }
}
