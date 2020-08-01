package mx.com.qtx.sec.servicios;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class TokensJwtUtil {
	private static SecretKey llave = generarLlave();

	// 10 horas por defecto
	private long duracionTokens = 1000 * 60 * 60 * 10;
	
	private static String getLlaveBase64() {
		return Encoders.BASE64.encode(llave.getEncoded());
	}
	
	public void setPeriodoExpiracionHoras(int horas) {
		this.duracionTokens = 1000 * 60 * 60 * horas;
	}
	public void setPeriodoExpiracionMinutos(int min) {
		this.duracionTokens = 1000 * 60 * min;
	}
	
	public String generarToken(String nombreUsuario) {
		Date ahora = new Date();
		Date expiracion = new Date(System.currentTimeMillis() + duracionTokens);
		String id = UUID.randomUUID()
				        .toString()
				        .replace("-", "");
		
		return Jwts.builder()
					  .setId(id)
					  .setIssuedAt(ahora)
		              .setSubject(nombreUsuario)
		              .setExpiration(expiracion)
		              .signWith(llave)
		              .compact();	
	}
	public String generarToken(String nombreUsuario, Map<String,Object> mapClaims) {
		Date ahora = new Date();
		Date expiracion = new Date(System.currentTimeMillis() + duracionTokens);
		String id = UUID.randomUUID()
				        .toString()
				        .replace("-", "");
		Claims claims = Jwts.claims(mapClaims);
		claims.setIssuedAt(ahora)
		       .setExpiration(expiracion)
		       .setSubject(nombreUsuario)
		       .setId(id);
		
		return Jwts.builder()
					  .setClaims(claims)
		              .signWith(llave)
		              .compact();	
	}
	public String extraerUsuario(String token) throws Exception{
		Jws<Claims> contenido = this.extraerJwsClaimsTokenFirmado(token);
		return contenido.getBody()
				        .getSubject();
	}
	public Date extraerExpiracion(String token) throws Exception{
		Jws<Claims> contenido = this.extraerJwsClaimsTokenFirmado(token);
		return contenido.getBody()
				        .getExpiration();
	}
	public boolean tokenExpirado(String token) throws Exception{
		return this.extraerExpiracion(token)
				      .before(new Date());
	}
	public boolean tokenValido(String tokenFirmado, String nombreUsuario) throws Exception {
		String usuarioEnToken = this.extraerUsuario(tokenFirmado);
		if(!nombreUsuario.equals(usuarioEnToken))
			return false;
		if(this.tokenExpirado(tokenFirmado))
			return false;
		return true;
	}
	private Jws<Claims> extraerJwsClaimsTokenFirmado(String tokenFirmado) throws Exception{
		//Usar con tokens firmados
		Jws<Claims> jwsClaims = Jwts.parserBuilder()
								    .setSigningKey(llave)
								    .build()
								    .parseClaimsJws(tokenFirmado);
		return jwsClaims;
	}
	private Jwt<Header,Claims> extraerJwtTokenSinFirmar(String tokenSinFirma){
		// Solamente se debe usar con tokens SIN firmar
		Jwt<Header,Claims> jwtHeaderClaims = Jwts.parserBuilder()
											    .build()
											    .parseClaimsJwt(tokenSinFirma);
		return jwtHeaderClaims;
	}
	public String extraerContenidoJwtTokenSinFirmarStr(String tokenSinFirma) {
		Jwt<Header,Claims> jwtHeaderClaims = this.extraerJwtTokenSinFirmar(tokenSinFirma);
		return jwtHeaderClaims.getHeader() + " " + jwtHeaderClaims.getBody();
	}
	public String extraerContenidoTokenFirmadoStr(String tokenFirmado) throws Exception{
		Jws<Claims> contenido = this.extraerJwsClaimsTokenFirmado(tokenFirmado);
		return contenido.getHeader() + " " + contenido.getBody() + " " + contenido.getSignature();
	}
	public <R> R extraerClaimEspecifica(String tokenFirmado, Function<Claims, R> getCampo) throws Exception{
		Jws<Claims> contenido = this.extraerJwsClaimsTokenFirmado(tokenFirmado);
		Claims claims = contenido.getBody();
		return getCampo.apply(claims);
	}
	private static SecretKey generarLlave() {
		return Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}

}
