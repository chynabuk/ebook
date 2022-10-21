package com.application.electronic_book.util.jwt;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    @Value("secret_key")
    private String jwtSecret;

    @Value("1000000")
    private int jwtExpirationMs;

    public String generateJwtToken(UserDetails userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }
    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret )
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

//    private static final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//
//    private static Date accessTokenLife() {
//        return new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(10));
//    }
//
//    public static String generateAccessToken(String username, String issuer, Collection<GrantedAuthority> claim) {
//        return JWT.create()
//                .withSubject(username)
//                .withExpiresAt(accessTokenLife())
//                .withIssuer(issuer)
//                .withClaim("roles", claim.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .sign(algorithm);
//    }


}
