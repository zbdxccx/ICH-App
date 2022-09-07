package com.contest.ichapp.util.JWTUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.contest.ichapp.common.Constant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT生成及校验工具类
 */
public class JWTUtil {
    //实现签名方法
    public static String createToken(Integer userId) {

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + Constant.EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(Constant.PRIVATE_SIGN);
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
    public static boolean checkToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(Constant.PRIVATE_SIGN);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Token过期");
        }
        return false;
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
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("token".equals(name)) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }
}
