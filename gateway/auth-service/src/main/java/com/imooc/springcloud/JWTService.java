package com.imooc.springcloud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JWTService {

    //生成环境不要使用,要从外部传入
    private static final String KEY = "changeIT";
    private static final String ISSUER = "ta1o";

    private static final Long TOKEN_EXPIRE_TIME = 60000L; //60s

    private static final String USERNAME = "username";

    /**
     * 生成token
     * @param acct
     * @return
     */
    public String token(Account acct){
        //验证完用户名与密码，生成token

        Date now = new Date();

        Algorithm algorithm = Algorithm.HMAC256("changeIT");
        String token = JWT.create().withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                .withClaim(USERNAME, acct.getUsername())
//                .withClaim("ROLE","")
                .sign(algorithm);
        log.info("jwt generated user={},token={}",acct,token);
        return token;
    }

    /**
     * jwt校验器
     * @param token
     * @param username
     * @return
     */
    public boolean verify(String token,String username){
        log.info("verifying jwt  -- username={}",username);
        try {
            Algorithm algorithm = Algorithm.HMAC256("changeIT");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withClaim(USERNAME,username)
                    .build();
            verifier.verify(token); //校验token
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error("auth failed : {}",e.getMessage());
        }
        return false;
    }

}
