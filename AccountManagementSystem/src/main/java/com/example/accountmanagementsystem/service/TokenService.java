package com.example.accountmanagementsystem.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenService {

    private final Logger logger= LoggerFactory.getLogger(TokenService.class);

    /**
     * Create token: encode user id, name and expired_date in it;
     * Use HMAC256 algo to encode token: default expired_date is 30 days.
     * reference: https://blog.csdn.net/weixin_30565199/article/details/95304668
     *
     * Mary, Mia, Ann, Anna, Andy, Lily(2) -> signature "TOKEN_SECRET"
     */
    public String createToken(String id, String name) {
        // HMAC256加密算法, "Michelle"是用来加密数字签名的密钥。
        Algorithm algo = Algorithm.HMAC256("Michelle");
//        LocalDate expired_date = LocalDate.now().plusDays(30);
        // after 30 days:, 5 days: 432000000L
        Date expired_date = new Date(System.currentTimeMillis() + 2592000000L);

        return JWT.create().withClaim("user_name", name)
                .withClaim("user_id", id)
                .withExpiresAt(expired_date)
                .sign(algo);
    }


    /**
     * Verify the signature of the token
     * reference:
     * https://www.cnblogs.com/jing5464/p/12162968.html
     * https://yihuishou.github.io/2018/09/01/3864825553/
     * Mary, Mia, Ann, Anna, Andy, Lily -> signature "TOKEN_SECRET"
     */
    public boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("Michelle");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            logger.warn(e.getMessage());
            logger.error("SOS!!! Invalid token, signature has been tampered !!!");
            return false;
        }
    }


    /**
     * Decode token to get user id.
     */
    public String getUserId(String token) {

        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("user_id").asString();
//        return jwt.getId();
    }


    /**
     * Decode token to get token's expired date.
     */
    public Date getExpiredDate(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();
    }

    /**
     * Decode token to get user name.
     */
    public String getAccountName(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("user_name").asString();
    }


}
