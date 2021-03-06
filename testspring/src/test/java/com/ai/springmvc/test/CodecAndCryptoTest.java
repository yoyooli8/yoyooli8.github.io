package com.ai.springmvc.test;

import java.security.Key;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;

public class CodecAndCryptoTest{
    @org.junit.Test
    public void testBase64(){
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        String str2 = Base64.decodeToString(base64Encoded);
        Assert.assertEquals(str, str2);
        System.out.println("hello:base64==>"+base64Encoded);
    }
    
    @org.junit.Test
    public void testHex(){
        String str = "hello";
        String hexEncoded = Hex.encodeToString(str.getBytes());
        String str2 = new String(Hex.decode(hexEncoded.getBytes()));
        Assert.assertEquals(str, str2);
        System.out.println("hello:hex==>"+hexEncoded);
    }
    @org.junit.Test
    public void testMd5(){
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()
        Assert.assertNotNull(md5);
        System.out.println("hello：md5hash==>"+md5);
        
        md5 = new Md5Hash(str, salt).toBase64();
        Assert.assertNotNull(md5);
        System.out.println("hello：md5Base64==>"+md5);
    }
    @org.junit.Test
    public void testSha1(){
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha1Hash(str, salt).toString();
        Assert.assertNotNull(sha1);
        System.out.println("hello：sha1.toHex==>"+sha1);
    }
    @org.junit.Test
    public void testSha256(){
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha256Hash(str, salt).toString();
        Assert.assertNotNull(sha1);
        System.out.println("hello：Sha256.toHex==>"+sha1);
    }
    @org.junit.Test
    public void testSha384(){
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha384Hash(str, salt).toString();
        Assert.assertNotNull(sha1);
        System.out.println("hello：Sha384.toHex==>"+sha1);
    }
    @org.junit.Test
    public void testSha512(){
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha512Hash(str, salt).toString();
        Assert.assertNotNull(sha1);
        System.out.println("hello：Sha512.toHex==>"+sha1);
    }
    @org.junit.Test
    public void testCodecSupport() {
        String str = "hello";
        byte[] bytes = CodecSupport.toBytes(str, "utf-8");
        String str2 = CodecSupport.toString(bytes, "utf-8");
        Assert.assertEquals(str, str2);
    }
    @org.junit.Test
    public void testRandom() {
        //生成随机数
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        System.out.println("randomNumberGenerator==>"+randomNumberGenerator.nextBytes().toHex());
    }
    
    @org.junit.Test
    public void testAesCipherService(){
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);//设置key长度
        
        //生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        //加密
        String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
        
        Assert.assertEquals(text, text2);
        System.out.println("hello:AesKey==>"+new String(key.getEncoded()));
        System.out.println("hello:Aes128==>"+encrptText);
    }
    @org.junit.Test
    public void testBlowfishCipherService(){
        BlowfishCipherService blowfishCipherService = new BlowfishCipherService();
        blowfishCipherService.setKeySize(128);
        
      //生成key
        Key key = blowfishCipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = blowfishCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 = new String(blowfishCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        Assert.assertEquals(text, text2);
        System.out.println("hello:Blowfish128==>"+encrptText);
    }
    @org.junit.Test
    public void testDefaultBlockCipherService(){
        //对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
        DefaultBlockCipherService cipherService = new DefaultBlockCipherService("AES");
        cipherService.setKeySize(128);
        
        //生成key
        Key key = cipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = cipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 = new String(cipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        Assert.assertEquals(text, text2);
        System.out.println("hello:DefaultBlock.Aes128==>"+encrptText);
    }
    @org.junit.Test
    public void testHashService(){
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数
        
        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        
        String hex = hashService.computeHash(request).toHex();
        Assert.assertNotNull(hex);
        System.out.println("hello:MD5.hex==>"+hex);
    }
}
