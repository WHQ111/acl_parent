package com.atgui.security.security;

import com.atgui.utils.R;
import com.atgui.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//退出处理器
public class TokenLogoutHandler implements LogoutHandler {


    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String token = request.getHeader("token");
        if(token != null) {
            tokenManager.removeToken(token);

            String username = tokenManager.getUserInfoFromToken(token);

            redisTemplate.delete(username);
        }

        ResponseUtil.out(response, R.ok());
    }
}
