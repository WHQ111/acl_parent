package com.atgui.security.security;

import com.atgui.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int strength) {

    }

    //进行md5加密
    @Override
    public String encode(CharSequence rawPassword) {

        MD5.encrypt(rawPassword.toString());
        return null;
    }



    /**
     * 密码比对
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
