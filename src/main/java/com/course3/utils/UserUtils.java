package com.course3.utils;

import org.springframework.util.DigestUtils;

import java.security.SecureRandom;
import java.util.Base64;

public class UserUtils {
    public static String createSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }

    public static String createHashPwd(String password, String salt){
        String base = password + salt;
        String hashPwd = DigestUtils.md5DigestAsHex(base.getBytes());
        return hashPwd;
    }
}
