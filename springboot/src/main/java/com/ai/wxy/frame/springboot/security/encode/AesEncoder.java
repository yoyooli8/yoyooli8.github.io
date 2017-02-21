package com.ai.wxy.frame.springboot.security.encode;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ai.wxy.frame.springboot.exception.AppExceptione;
import com.ai.wxy.frame.springboot.exception.ErroCodes;

public class AesEncoder{
    private static final String ENCODING = "UTF-8";
    private static final String KEY_ALGORITHM = "AES";// 产生密钥的算法
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";// 加解密算法 格式：算法/工作模式/填充模式 注意：ECB不使用IV参数
    /**
      * 产生密钥
      * @brief   产生密钥
      * @details 产生密钥
      * @throws NoSuchAlgorithmException
      * @return 
      * @exception 
      * @author wangxiangyang
      * @date 2017年2月9日 下午3:57:06
      * @note wangxiangyang@ 2017年2月9日添加了此方法
     */
    public static byte[] getKey() throws NoSuchAlgorithmException{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(256);//初始化密钥长度,128,192,256（选用192和256的时候需要配置无政策限制权限文件--JDK6）
        SecretKey key =keyGenerator.generateKey();//产生密钥
        return key.getEncoded();
    }
    /**还原密钥：二进制字节数组转换为Java对象**/
    public static Key toKey(byte[] keyByte){
        return new SecretKeySpec(keyByte, KEY_ALGORITHM);
    }
    /**
     * AES加密
     * @param data     待加密数据
     * @param keyByte  密钥
     **/
    public static byte[] aes_Encrypt(String data, byte[] keyByte){
        try{
            Key key = toKey(keyByte);//还原密钥
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);//JDK下用
            cipher.init(Cipher.ENCRYPT_MODE, key);//设置加密模式并且初始化key
            return cipher.doFinal(data.getBytes(ENCODING));
        }catch(UnsupportedEncodingException e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.AESENCODING_ERROR.getErrorCode(),"AES加密失败",e);
        }
    }
    /**
     * AES加密
     * @param data     待加密数据
     * @param keyByte  密钥
     **/
    public static String aes_encrypt2Hex(String data, byte[] keyByte){
        byte[] encodedByte = aes_Encrypt(data, keyByte);
        
        return Base64.encodeBase64String(encodedByte);
    }
    /**
     * AES解密
     * @param data     待解密数据为字节数组
     * @param keyByte  密钥
     **/
    public static byte[] aes_Decrypt(byte[] data, byte[] keyByte){
        try{
            Key key = toKey(keyByte);//还原密钥
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);//JDK下用
            cipher.init(Cipher.DECRYPT_MODE, key);//设置解密模式并且初始化key
            return cipher.doFinal(data);
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.AESDECRYPT_ERROR.getErrorCode(),"AES解密失败",e);
        }
    }
    /**
     * AES解密
     * @param data     待解密数据为字节数组,经过base64加密码的数据
     * @param keyByte  密钥
     **/
    public static byte[] aes_Decrypt(String data, byte[] keyByte){
        try{
            Key key = toKey(keyByte);//还原密钥
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);//JDK下用
            cipher.init(Cipher.DECRYPT_MODE, key);//设置解密模式并且初始化key
            return cipher.doFinal(Base64.decodeBase64(data));
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.AESDECRYPT_ERROR.getErrorCode(),"AES解密失败",e);
        }
    }
}
