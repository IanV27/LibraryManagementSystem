package umgc.mscs495.libmngntsys.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create common APIs for the input parameters validations.
 * @author jimiewang
 * @CreateDate 01/29/2024
 */
public class ValidationUtil {

    /**
     * validate the email format
     * @param email 
     * @return true if the email format is correct otherwise return false
     */
    public boolean validateEamil(String email)
    {
        boolean isValid = true;
        //define the email regex pattern
        String regexPatternStr = "^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$";
        Pattern emailRegexPattern = Pattern.compile(regexPatternStr);
        //check email format
        Matcher regMatcher = emailRegexPattern.matcher(email);
        //if email format is illegal
        if(!regMatcher.matches()) {
            isValid = false;
            //return "Valid Email Address";
        }
        return isValid;
    }
    
    /**
     * Check if the input string is digital.
     * @param inputString
     * @return true if inputString is digits, otherwise return false
     */
    public boolean isDigits(String inputString) 
    {
    	final String digits = "1234567890";
    	boolean suc = true;
    	if(inputString != null && !inputString.trim().isEmpty()) {
    		for(char inputchar: inputString.toCharArray()) {
    			if(!digits.contains(String.valueOf(inputchar))) {
    				suc = false;
    			}
    		}
    	} else {
    		suc = false;
    	}
    	
    	return suc;
    }

    /**
     * Check if the input string is alphanumeric.
     * @param inputString
     * @return true if inputString is digits, otherwise return false
     */
    public boolean isAlphanumeric(String inputString) 
    {
    	final String digits = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    	boolean suc = true;
    	if(inputString != null && !inputString.trim().isEmpty()) {
    		for(char inputchar: inputString.toCharArray()) {
    			if(!digits.contains(String.valueOf(inputchar))) {
    				suc = false;
    			}
    		}
    	} else {
    		suc = false;
    	}

    	return suc;
    }
    
    
}
