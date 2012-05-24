package com.bizintelapps.zytoon.ajax;

import com.bizintelapps.zytoon.service.UsersService;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author intesar
 */
@net.bull.javamelody.MonitoredWithSpring
public class GeneralAjaxService {

    @Autowired
    protected UsersService usersService;
    private static final Logger logger = Logger.getLogger(GeneralAjaxService.class);

    public void signUp(String fullname, String username, String password, String confirmPassword, HttpServletRequest request) {
        String clientIP = request.getRemoteAddr();
        this.usersService.signUp(fullname, username, password, confirmPassword, clientIP);
    }

    public void emailAccessCode(String email) {
        this.usersService.emailAccessCode(email);
    }

    public void resetPassword(String email, String accessCode, String password, String confirmPassword) {
        this.usersService.resetPassword(email, accessCode, password, confirmPassword);
    }

    public int fbAccessToken(String accessToken, int c, HttpServletRequest request) {
        int code = c == -1 ? -2 : -1;
        try {
            String clientIP = request.getRemoteAddr();
            this.usersService.fbAccessToken(accessToken, clientIP);
            code = 1;
        } catch (RuntimeException ex) {
            logger.error(ex);
        }
        return code;
    }
}
