package com.contest.ichapp.util.JWTUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.contest.ichapp.common.Constant.EXPIRE_DATE;
import static com.contest.ichapp.common.Constant.PRIVATE_SIGN;

/**
 * JWT生成及校验工具类
 */
@Slf4j
public class JWTUtil {
    //实现签名方法
    public static String createToken(Integer userId) {

        String token;
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(PRIVATE_SIGN);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，存入token，生成签名
            token = JWT.create().withHeader(header).withClaim("userId", userId).withExpiresAt(date).sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    //验证token
    public static boolean checkTokenWrong(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(PRIVATE_SIGN);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return false;
        } catch (Exception e) {
            log.info("Token out of date");
        }
        return true;
    }

    public static Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();

        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("token".equals(name)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (token == null) return request.getHeader("token");
        return token;
    }

    public static Integer getUserId_X(HttpServletRequest request) {
        String token;
        try {
            token = JWTUtil.getToken(request);
            if (JWTUtil.checkTokenWrong(token)) return -1;
            if (token == null) return -2;
        } catch (NullPointerException e) {
            return -2;
        }
        return JWTUtil.getUserId(token);
    }
}
