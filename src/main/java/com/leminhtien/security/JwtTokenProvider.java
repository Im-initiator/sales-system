package com.leminhtien.security;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.leminhtien.dto.MyUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {

	//mã bí mật chỉ phía server biết
	private final String JWT_SECRET = "chuoibimat";
	
	//thời gian hiểu lực của chuỗi jwt
	private final long JWT_EXPIRAION = 604800000L;
	
	//tạo jwt từ thông tin user
	public String generateToken(MyUser  userDetails) {
		Date now = new Date();
		
		//Thời gian hết hạn
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRAION);
		
		//tạo chuỗi json web token từ id của USER
		return Jwts.builder()
				.setSubject(Long.toString(userDetails.getId()))//xác định chủ thể JWT
				.setIssuedAt(now)//xác định thời điểm phát hành
				.setExpiration(expiryDate)//Thời điểm hết hạn
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)//tạo chữ ký với khóa bí mật
				.compact();//kết thúc và tạo ra một chuỗi JWT
	}
	
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(JWT_SECRET)
							.parseClaimsJws(token)
							.getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public boolean validateToken(String authToken) {
		try {
			//Kiểm tra chuỗi token
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
            System.err.print("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	System.err.print("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.err.print("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	System.err.print("JWT claims string is empty.");
        }
		return false;
	}
	
	
}
