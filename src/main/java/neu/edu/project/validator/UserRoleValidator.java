package neu.edu.project.validator;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class UserRoleValidator implements AuthenticationSuccessHandler{
	protected Log logger = LogFactory.getLog(this.getClass());		 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
    protected String determineTargetUrl(Authentication authentication) {
	    boolean isVender = false;
	    boolean isBuyer = false;
	    boolean isDelivery = false;
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    for (GrantedAuthority grantedAuthority : authorities) {
	        if (grantedAuthority.getAuthority().equals("VENDER")) {
		        isVender = true;
		        break;
		    } else if (grantedAuthority.getAuthority().equals("BUYER")) {
		        isBuyer = true;
		        break;
		    } else if(grantedAuthority.getAuthority().equals("DELIVERY")){
            	isDelivery = true;
            	break;
            }
        } // for
        if (isVender) {
	        return "/vender";
		} else if (isBuyer) {
		    return "/buyer";
		} else if(isDelivery){
			return "/delivery";
        } else{
            throw new IllegalStateException();
        }
    }
	 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
	 
    protected void handle(HttpServletRequest request, 
		HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Cannot process it!" + targetUrl);
            return;
        }
	    redirectStrategy.sendRedirect(request, response, targetUrl);
    }
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
	    HttpServletResponse response, Authentication authentication) throws IOException {
	    handle(request, response, authentication);
	    clearAuthenticationAttributes(request);
	}	
}
