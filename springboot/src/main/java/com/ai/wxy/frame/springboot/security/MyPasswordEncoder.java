package com.ai.wxy.frame.springboot.security;

import static org.springframework.security.crypto.util.EncodingUtils.concatenate;
import static org.springframework.security.crypto.util.EncodingUtils.subArray;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder{
    private static final int DEFAULT_ITERATIONS = 1;
    private final String aespwd  = "www.asiainfo.com";
    private final String aessalt = "5075428636499153";
    private final Digester digester;
    private final byte[] secret;
    private final BytesKeyGenerator saltGenerator;
    public MyPasswordEncoder() {
        this("");
    }
    
    public MyPasswordEncoder(CharSequence secret) {
        this("SHA-256", secret);
    }
    private MyPasswordEncoder(String algorithm, CharSequence secret) {
        this.digester = new Digester(algorithm, DEFAULT_ITERATIONS);
        this.secret = Utf8.encode(secret);
        this.saltGenerator = KeyGenerators.secureRandom();
    }
    
    public String encode(CharSequence rawPassword) {
        System.out.println("==========rawPassword=============>"+rawPassword);
        if("userNotFoundPassword".equals(rawPassword)){
            return rawPassword.toString();
        }
        
        String orignpwd = decrytRawPwd(rawPassword);
        return encode(orignpwd, saltGenerator.generateKey());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("==========rawPassword============="+rawPassword);
        System.out.println("==========encodedPassword========="+encodedPassword);
        
        String orignpwd = decrytRawPwd(rawPassword);
        System.out.println("==========orignpwd========="+orignpwd);
        byte[] digestoldpwd = decode(encodedPassword);
        byte[] salt     = subArray(digestoldpwd, 0, saltGenerator.getKeyLength());
        
        byte[] digestpwd = digest(orignpwd);
        byte[] digest    = digestFinal(digestpwd,salt);
        
        return matches(digestoldpwd, digest);
    }
    private String decrytRawPwd(CharSequence rawPassword){
        try{
            byte[] input = Hex.decode(rawPassword);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKey aeskey = new SecretKeySpec(aespwd.getBytes(),"AES");
            IvParameterSpec ivspec = new IvParameterSpec(aessalt.getBytes());
            
            cipher.init(Cipher.DECRYPT_MODE, aeskey,ivspec);
            byte[] encodeRs = cipher.doFinal(input);
            
            String restr = new String(encodeRs);
            
            return restr.trim();
        }catch(Exception e){
            throw new RuntimeException("解密失败");
        }
        
    }
    
//    private byte[] digestRawPwd(CharSequence rawPassword,byte[] salt){
//        byte[] digestpwd = decode(rawPassword);
//        byte[] digest    = digestFinal(digestpwd,salt);
//        return digest;
//    }
    
    private String encode(CharSequence rawPassword, byte[] salt) {
        byte[] digestpwd = digest(rawPassword);
        byte[] digest    = digestFinal(digestpwd,salt);
        return new String(Hex.encode(digest));
    }
    private byte[] digest(CharSequence rawPassword){
        byte[] digest = digester.digest(Utf8.encode(rawPassword));
        return digest;
    }
    
    private byte[] digestFinal(byte[] digestpwd,byte[] salt){
        byte[] digest = digester.digest(concatenate(salt,secret,digestpwd));
        
        return concatenate(salt, digest);
    }
    
//    private byte[] digest(CharSequence rawPassword, byte[] salt) {
//        byte[] digest = digester.digest(concatenate(salt, secret,
//                Utf8.encode(rawPassword)));
//        return concatenate(salt, digest);
//    }

    private byte[] decode(CharSequence encodedPassword) {
        return Hex.decode(encodedPassword);
    }
    private boolean matches(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }
}
