package com.ai.wxy.frame.springboot.security.encode;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;

import com.ai.wxy.frame.springboot.exception.AppExceptione;
import com.ai.wxy.frame.springboot.exception.ErroCodes;

public class BaseEncoder{
    private static final String ENCODING = "UTF-8";
    /**一般Base64加密**/
    public static String encode(String data){
        try{
            byte[] encodedBytes = Base64.encodeBase64(data.getBytes(ENCODING));
            return new String(encodedBytes,ENCODING);
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    /**安全Base64加密**/
    public static String encodeSafe(String data){
        try{
            /**
             * 注意：这里采用的encodeBase64(byte[] bytes, boolean arg1)
             * arg1为true时，加密后的字符串每行为76个字符，不论每行够不够76个字符，都要在行尾添加“\r\n”
             */
            byte[] encodedByte = Base64.encodeBase64(data.getBytes(ENCODING),true);
            return new String(encodedByte, ENCODING);
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    /**Base64解密**/
    public static String decode(String data){
        try{
            byte[] decodedByte = Base64.decodeBase64(data.getBytes(ENCODING));
            return new String(decodedByte, ENCODING);
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    /**HEX加密**/
    public static String hex_Encode(String data){
        try{
            char[] encodedChars = Hex.encodeHex(data.getBytes(ENCODING));
            
            return new String(encodedChars);
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    /**HEX解密**/
    public static String hex_Decode(String data){
        try{
            byte[] decodedBytes = Hex.decodeHex(data.toCharArray());
            
            return new String(decodedBytes,ENCODING);
        }catch(UnsupportedEncodingException e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.HEXDECODE_ERROR.getErrorCode(),"HEX解密失败",e);
        }
    }
    
    /**MD5加密,加密后的结果为二进制字节数组**/
    public static byte[] md5_Encode(String data){
        try{
            return DigestUtils.md5(data.getBytes(ENCODING));
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    /**MD5加密,加密后的结果为二进制字节数组，并且在这里将二进制字节数组转为了32位的16进制*/
    public static String md5_Encode2Hex(String data){
        try{
            return new String(DigestUtils.md5Hex(data.getBytes(ENCODING)));
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    /**SHA加密,加密后的结果为二进制字节数组**/
    public static byte[] sha_Encode(String data){
        try{
            return DigestUtils.sha256(data.getBytes(ENCODING));//SHA-256
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    /**SHA加密,加密后的结果为二进制字节数组，并且在这里将二进制字节数组转为了16进制字符串**/
    public static String sha_Encode2Hex(String data){
        try{
            return new String(DigestUtils.sha256Hex(data.getBytes(ENCODING)));
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    /**
      * HmacMD5加密
      * @brief   HmacMD5加密
      * @details HmacMD5加密
      * @param data    待加密数据
      * @param keyByte 密钥
      * @return
      * @exception 
      * @see 
      * @author wangxiangyang
      * @date 2017年2月9日 下午3:38:06
      * @note wangxiangyang@ 2017年2月9日添加了此方法
     */
    public static byte[] hmacMd5_encode(String data, byte[] keyByte){
        try{
            return HmacUtils.hmacMd5(keyByte, data.getBytes(ENCODING));
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
    public static String hmacMd5_encode2HEX(String data, byte[] keyByte){
        try{
            return HmacUtils.hmacMd5Hex(keyByte, data.getBytes(ENCODING));
        }catch(Exception e){
            throw new AppExceptione(ErroCodes.ENCODING_ERROR.getErrorCode(),"不支持UTF-8编码转换",e);
        }
    }
}
