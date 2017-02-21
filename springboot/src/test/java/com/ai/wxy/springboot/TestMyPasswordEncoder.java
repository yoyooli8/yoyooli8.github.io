package com.ai.wxy.springboot;

import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.codec.Hex;

import com.ai.wxy.frame.springboot.security.MyPasswordEncoder;

public class TestMyPasswordEncoder{
    //@Test
    public void testEncodepwd(){
        MyPasswordEncoder pwdencoder = new MyPasswordEncoder("abc123#!");
        
        String encoderpwd = pwdencoder.encode("abc123");
        Assert.assertNotNull(encoderpwd);
        System.out.println(encoderpwd);//e8e9c3fac0cb89150cca4aafe21d90884bb47fa4ecf2eb3869216e89a05752898d572d3929c66b98
    }
    
    //@Test
    public void testNormalEncode(){
        MyPasswordEncoder pwdencoder = new MyPasswordEncoder("abc123#!");
        
        boolean rtn = pwdencoder.matches("6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090",
                                          "e8e9c3fac0cb89150cca4aafe21d90884bb47fa4ecf2eb3869216e89a05752898d572d3929c66b98");
        Assert.assertTrue(rtn);
        System.out.println("----------->"+rtn);
    }
    @Test
    public void testAESEncode(){
//        org.apache.commons.lang3.time.DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        
        String content  = "47e977c89bdf75b290aa373f1ea356fc";
        String password = "www.asiainfo.com";
        byte[] input = Hex.decode(content);
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
//            int plaintextLength = input.length;
//            byte[] plaintext = new byte[plaintextLength];
//            System.arraycopy(input, 0, plaintext, 0, input.length);
            SecretKey aeskey = new SecretKeySpec(password.getBytes(),"AES");
            IvParameterSpec ivspec = new IvParameterSpec("5075428636499153".getBytes());
            
            cipher.init(Cipher.DECRYPT_MODE, aeskey,ivspec);
            byte[] encodeRs = cipher.doFinal(input);
            
            String restr = new String(encodeRs);
            
            System.out.println(restr.trim());
        }catch(Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
