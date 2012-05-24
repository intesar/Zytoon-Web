package com.bizintelapps.zytoon.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 *
 * @author atefahmed
 */
@Component
public class PasswordValidator {

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%=]).{6,20})";
    private static final String EMAIL_PATTERN =
            "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

    public PasswordValidator() {
        
    }

    public boolean isValidPassword(final String password) {
        //Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        //Matcher matcher = pattern.matcher(password);
        //return matcher.matches();
        if ( password != null && password.length() >= 6 ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidEmail(final String email) {
        CharSequence inputStr = email;
        boolean isValid = false;

        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(inputStr);

        if (emailMatcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

    public void isValidEmailPassword(final String username, final String password, final String confirmPassword) {
        // isValidPassword email with regex
        
        boolean isEmailValid = isValidEmail(username);
        if (!isEmailValid) {
            throw new RuntimeException("Enter valid email. Example: yourname@gmail.com  " + username);
        }

        // compare password & confirmPassword
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Password and Confirm Password do not match");
        }
        // password validation

        if (password.length() < 6) {
            throw new RuntimeException("Password is too short, minimum 6 chars required");
        }


        boolean isPassValid = isValidPassword(password);

        if (!isPassValid) {
            throw new RuntimeException("Password must contain at least one of the following: \n 1. number"
                    + "2. Special character"
                    + "3. Lower case"
                    + "4. Upper case");
        }
    }
}
