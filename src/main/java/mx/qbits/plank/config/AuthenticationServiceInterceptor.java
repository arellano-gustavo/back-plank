package mx.qbits.plank.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import mx.qbits.plank.api.service.JwtManagerServiceImpl;

@Component
public class AuthenticationServiceInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String errMsg="{'error':'Not Authorized'}".replace("'", "\"");
        String uri = request.getRequestURI();
        //logger.info("URI:"+uri);
        // verificando acceso...
        String jwt = request.getHeader("jwt");
        boolean verified = verifyToken(jwt, uri);
        if(!verified) {
            logger.error("No se verificó el token !!!"); 
            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().write(errMsg);
            return false;
        }
        return true;
    }
    
    @Override
    public void postHandle(
       HttpServletRequest request, HttpServletResponse response, Object handler, 
       ModelAndView modelAndView) throws Exception {
       //logger.info("postHandle");
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
       Object handler, Exception exception) throws Exception {
       //logger.info("afterCompletion");
    }
    
    private boolean verifyToken(String jwt, String uri) {
        if(isSecured(uri)) {
            try {
                Claims claims = Jwts.parser()
                  .setSigningKey(JwtManagerServiceImpl.ENCRYPT_KEY.getBytes())
                  .parseClaimsJws(jwt).getBody();
                String usr = claims.getId();
                String rol = getRol(usr);
                String[] rolPriv = getRolPriv(rol);
                boolean allowed = verifyAllows(uri, rolPriv);
                if(allowed) {
                    logger.debug("ID: " + usr);
                    logger.debug("Subject: " + claims.getSubject());
                    logger.debug("Issuer: " + claims.getIssuer());
                    logger.debug("Expiration: " + claims.getExpiration());
                    logger.debug("IssuedAt: " + claims.getIssuedAt());
                    return true;
                }
                return false;
            } catch(Exception e) {
                logger.error("Given token has not been verified. Error: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    private String[] getRolPriv(String rol) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean verifyAllows(String uri, String[] rolPriv) {
		// TODO Auto-generated method stub
		return false;
	}

	private String getRol(String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isSecured(String uri) {
        String[] secureUris = {
                "/api/chatbot/admin/all-roles.json",
                "/api/chatbot/admin/all-roles2.json"
                };
        for(String secured : secureUris) {
            if(uri.equals(secured)) return true;
        }
        return false;
    }
    
}
