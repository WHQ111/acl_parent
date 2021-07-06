package com.atgui.security.security;

import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {

    //有效时长
    private long tokenExpiration = 24*60*60+1000;

    //编码秘钥
    private String tokenSignKey = "123456";

    //使用jwt根据用户名生成token
    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                        .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    //根据token字符串生成用户信息
    public String getUserInfoFromToken(String token) {
        String userInfo = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return userInfo;
    }

    //删除token
    public void removeToken(String token) {

    }
}
