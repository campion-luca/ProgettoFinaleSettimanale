package lucacampion.ProgettoFinaleSettimanale.tools;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.exceptions.UnauthorizedException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT {
    @Value("${JWT_SECRET}")
    private String secret;

    public String createToken(User user){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 5))
                .subject(user.getUsername())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String accesToken){
        try{
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(accesToken);
        } catch (Exception e) {
            throw new UnauthorizedException("Problemi con il token, rifare il login");
        }

    }

    public String getIdFromToken(String accessToken) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(accessToken)
                .getPayload().getSubject();
    }
}