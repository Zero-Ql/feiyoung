package com.example.passwordgui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RC4Tool {
    private static final int BYTE_SIZE = 'Ā';
    private static final byte[] s = new byte[BYTE_SIZE];

    //ksa初始化
    RC4Tool(byte[] key) {
        final byte[] k = new byte[BYTE_SIZE];
        byte temp;
        int j;
        for (int i = 0; i < BYTE_SIZE; i++) {
            //初始化s表
            s[i] = (byte) i;
            //填充k表
            k[i] = key[(i % key.length)];
        }
        for (int i = j = 0; i < BYTE_SIZE; i++) {
            //置换s[i]和s[j]
            j = j + s[i] + k[i] & 0xFF;
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }

    //加密
    public static String Encrypt(String key, String password) throws NoSuchAlgorithmException {
        var message = MessageDigest.getInstance("md5");
        message.update(new RC4Tool(StringToByteArray(key)).password_stream(password.getBytes()));
        return GetMD5ToString(message.digest());
    }

    private static String GetMD5ToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(String.format("%02x", aByte));
        }
        return sb.toString();
    }

    //String转byte[]
    private static byte[] StringToByteArray(String key) {
        byte[] bytes = new byte[key.length()];
        for (int i = 0; i < key.length(); i++) {
            bytes[i] = (byte) Integer.parseInt(((Character) (key.charAt(i))).toString());
        }
        return bytes;
    }

    //生成密钥流
    private byte[] password_stream(byte[] password) {
        byte[] password_stream = new byte[password.length];
        int i = 0, j = 0, m = 0;
        byte temp;
        while (m < password.length) {
            i = i + 1 & 0xFF;
            j = j + s[i] & 0xFF;
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            password_stream[m] = (byte) (s[(s[i] + s[j] & 0xFF)] ^ password[m]);
            m++;
        }
        return password_stream;
    }
}

