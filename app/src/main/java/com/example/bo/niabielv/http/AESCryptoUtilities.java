package com.example.bo.niabielv.http;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密解密工具包 AESCryptoUtilities
 *
 * @author huangaming
 * @Date 2014-9-17
 */
public class AESCryptoUtilities {
    private final static String HEX = "0123456789ABCDEF";
    private final static String Encoding = "UTF-8";

    /**
     * MD5加密(32)
     *
     * @param plainText 大写32位md5字符串
     * @return
     */
    public final static String MD5(String plainText) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] strTemp = plainText.getBytes(Encoding);
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
        }
        return "";
    }

//    /**
//     * 加密方法
//     *
//     * @param seed      密钥
//     * @param cleartext 明文
//     * @return 加密后的大写十六进制字符串
//     * @throws Exception
//     */
//    public static String encrypt(String seed, String cleartext)
//            throws Exception {
//        // byte[] rawKey = getRawKey(seed.getBytes());
//        byte[] rawKey = seed.getBytes(Encoding);
//        byte[] result = encrypt(rawKey, cleartext.getBytes(Encoding));
//        return toHex(result);
//    }
//
//    public static String encrypt(String cleartext) {
//        try {
//            return encrypt(StoreJni.getStore().getAesKey(), cleartext);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }

//    public static String decrypt(String cleartext) {
//        try {
//            return decrypt(StoreJni.getStore().getAesKey(), cleartext);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }

    /**
     * 解密方法
     *
     * @param seed      密钥
     * @param encrypted 密文
     * @return 解密后的明文字符串
     * @throws Exception
     */
    public static String decrypt(String seed, String encrypted)
            throws Exception {
        // byte[] rawKey = getRawKey(seed.getBytes());
        byte[] rawKey = seed.getBytes(Encoding);
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result, Encoding);
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted)
            throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        }
        return result;
    }

    /**
     * 字符串转换成十六进制字符串
     *
     * @param str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * bytes字符串转换为Byte值
     *
     * @param  src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int j = i * 2;
            String subStr = "0x" + src.substring(j, m)
                    + src.substring(m, n);
            ret[i] = Byte.decode(subStr);
        }
        return ret;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

}
