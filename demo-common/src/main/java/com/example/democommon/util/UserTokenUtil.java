package com.example.democommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;


public class UserTokenUtil {
    private static final String secret="ukc8BDbRigUDaY6pZFfWus2jZWLPHO";
    private static final Integer EXPIRE= 1000 * 60 * 60 * 24;
    public static String generateToken(Integer number, String name,Boolean isTeacher){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, EXPIRE);
        return   JWT.create()
                .withHeader(new HashMap<>())  // Header
                .withClaim("number", number)  // Payload
                .withClaim("name", name)
                .withClaim("isTeacher", isTeacher)
                .withExpiresAt(calendar.getTime())  // 过期时间
                .sign(Algorithm.HMAC256(secret));  // 签名用的secret
    }
    public static DecodedJWT analysisToken(String token){
        // 创建解析对象，使用的算法和secret要与创建token时保持一致
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        // 解析指定的token
        return jwtVerifier.verify(token);
    }

}
