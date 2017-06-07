package com.fc.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by fangcong on 2017/2/17.
 * 加解密算法
 */
public class EncryptUtils {

    private static final String CHARSET = "gbk";

    /**
     * 不可逆加密算法：MD5
     * 对称加密算法:AES(更安全)与DES
     * 加密过程是将得到的二进制字节数据转成16进制并得到字符串
     * @param str   待加密串
     * @param key   秘钥
     * @return      加密后的字符串
     * @throws Exception
     */
    public static String aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null){
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(CHARSET), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes(CHARSET));
        String encryptStr = parseBytes2Hex(bytes);
        return encryptStr;
    }

    /**
     * AES解密
     * 解密过程是将16进制字符串先转成二进制字节数组，然后执行解密操作
     * @param str   带解密的字符串
     * @param key   秘钥
     * @return      原串
     */
    public static String aesDecrypt(String str, String key) throws Exception{
        if (str == null || key == null){
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(CHARSET), "AES"));
        byte[] strBytes = parseHexStr2bytes(str);
        byte[] bytes = cipher.doFinal(strBytes);
        return new String(bytes, CHARSET);
    }

    /**
     * 将二进制转成16进制，加密时用
     * @param bytes 加密得到的二进制字节数组
     * @return
     */
    public static String parseBytes2Hex(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < bytes.length;i++){
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1){
                hex = "0" + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制串转成二进制数组，用于解密
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2bytes(String hexStr){
        if (hexStr.length() < 1){
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0;i < result.length;i++){
            int high = Integer.parseInt(hexStr.substring(2 * i, 2 * i + 1), 16);
            int low = Integer.parseInt(hexStr.substring(2 * i + 1, 2 * i + 2), 16);
            result[i] = (byte)(high * 16 + low);
        }
        return result;
    }

    /**
     * 将二进制字节数组转成16进制
     * @param bytes
     * @return
     */
    public static String byteArr2HexStr(byte[] bytes) {
        int length = bytes.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < length;i++) {
            int tmp = bytes[i];
            //负数转为整数
            while (tmp < 0) {
                tmp = tmp + 256;
            }
            //小于0F，高位补0
            if (tmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(tmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转为二进制数据
     * @param strIn
     * @return
     * @throws Exception
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] bytes = strIn.getBytes();
        int length = bytes.length;
        byte[] outs = new byte[length/2];
        for (int i = 0;i < length;i = i + 2) {
            String tmp = new String(bytes, i, 2);
            outs[i/2] = (byte) Integer.parseInt(tmp, 16);
        }
        return outs;
    }

    /**
     * base64加密，产生的字节位数是8的倍数，不足位“=”填充
     * <p>base64加解密属于一种编码格式，不是严格意义上的加密算法</p>
     * @param str
     * @return
     */
    public static String base64Encrypt(String str) throws Exception{
        byte[] bytes = str.getBytes(CHARSET);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encodeBuffer(bytes);
    }

    /**
     * base64解密，生成字节数组
     * <p>new String():根据参数是字节数组，解码为对应字符</p>
     * <p>toString():打印对象，hash码</p>
     * @param key
     * @return
     * @throws Exception
     */
    public static String base64Decrypt(String key) throws Exception{
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(key);
        return new String(bytes, CHARSET);
    }

    public static void main(String[] args) throws Exception{
        /*String name = "zhangsan";
        String key = "*(&^!#$^#@2f%&9$";
        String encryptStr = aesEncrypt(name, key);
        System.out.println(encryptStr);
        String decryptStr = aesDecrypt(encryptStr, key);
        System.out.println(decryptStr);*/
        String base64EncryptStr = base64Encrypt("张三");
        System.out.println(base64EncryptStr);
        String base64DecryptStr = base64Decrypt(base64EncryptStr);
        System.out.println(base64DecryptStr);
    }

}
