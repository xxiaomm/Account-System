package com.example.accountmanagementsystem.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

/** reference
 * token
 * https://blog.csdn.net/careful_thebrave/article/details/124027847
 * https://blog.csdn.net/weixin_56995925/article/details/120075631
 * https://blog.csdn.net/yw99999/article/details/118564854
 * https://huaweicloud.csdn.net/638752cddacf622b8df8ad4d.html?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~activity-3-117815293-blog-124027847.pc_relevant_multi_platform_whitelistv3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~activity-3-117815293-blog-124027847.pc_relevant_multi_platform_whitelistv3&utm_relevant_index=4
 * https://blog.csdn.net/xq610928/article/details/118875654
 */


@Service
public class TokenService {

    /**
     * Use HMAC256 algo to encode token: default expired_date is 30 days.
     * reference: https://blog.csdn.net/weixin_30565199/article/details/95304668
      */

    public String createToken(String id, String name) {
        // HMAC256加密算法, TOKEN_SECRET是用来加密数字签名的密钥。
        Algorithm algo = Algorithm.HMAC256("TOKEN_SECRET");
//        LocalDate expired_date = LocalDate.now().plusDays(30);
        Date expired_date = new Date(System.currentTimeMillis() + 2592000000L); // after 30 days

        return JWT.create().withClaim("name", name)
                .withClaim("id", id)
                .withExpiresAt(expired_date)
                .sign(algo);
    }


    public String getAccountId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("id").asString();
//        return jwt.getId();
    }


    public Date getExpiredDate(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();
    }

}
