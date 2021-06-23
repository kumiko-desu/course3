package com.course3.Utils;

import com.course3.utils.UserUtils;
import org.junit.jupiter.api.Test;

public class UserUtilsTest {
    @Test
    public void createSalt(){
        System.out.println(UserUtils.createSalt());
    }

    @Test
    public void createHashPwd(){
        System.out.println(UserUtils.createHashPwd("DOZqBLO9Dy9DngxdYUvZ",""));
    }
}
