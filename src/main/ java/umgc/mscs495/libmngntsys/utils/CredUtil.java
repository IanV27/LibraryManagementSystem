package umgc.mscs495.libmngntsys.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @Description This class is to provide common APIs such as encryption, decryption, 
 *              data validation, file reading, file writing, etc.
 * @author jimiewang
 * @CreateDate 01/22/2024
 */
public class CredUtil {
    public static final String ZERO_ATTEMPT = "0";
    public static final int MAX_ATTEMPT = 6;
    public static final String CONFIG_FILE_NAME = "appconfig.properties";
//    public static final String DECRY_KEY = "K+rccnWCZ9xaFzw2Ipmo8Q==";
//    public static final String DB_CRED = "mkCMWFxM78zT35A31ada0g==";
//    public static final String DB_USER = "mkCMWFxM78zT35A31ada0g==";
    private static final String INVALID_CHARS = "=<>{}+-/|";
    private static SecretKeySpec secretKey;
    private static byte[] key;

    /**
     * 
     * @description set secret key for decryption.
     * @param myKey
     */
    private void setKey(final String myKey) throws Exception
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            //define cryption algorithm
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            //get the secret key
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    
    /**
     * 
     * @description Write content to specified file.
     * @param fileDir
     * @param fileName
     * @param content 
     */
    private void writeFile(String fileDir, String fileName, String content)
    {
//        String prevLoginRecordFile = System.getProperty("user.dir") + File.separatorChar + "Config" 
//                + File.separatorChar + "prevlogin.txt";
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        //Write the content to text file
        try {
            //validate the input file name
            boolean isValidPath = validateInputFileName(fileDir, fileName);
            //if it is valid
            if(isValidPath) {
                String filePath = fileDir + File.separatorChar + fileName;
                fileWriter = new FileWriter(filePath);
                bufWriter = new BufferedWriter(fileWriter);
                bufWriter.write(content);
            } else {
                System.out.println("Input file name is invalid.");
            }
        }
        // print error message if there is one
        catch (IOException io) {
            System.out.println("File IO Exception" + io.getMessage());
        }
        //close the file
        finally 
        {
            try 
            {
                if (bufWriter != null) 
                {
                    bufWriter.close();
                }
                if (fileWriter != null)
                {
                    fileWriter.close();
                }
            }
            //print error message if there is one
            catch (IOException io) {
                System.out.println("Issue closing the File." + io.getMessage());
            }
        }
    }
       
    public String getConfigFileDir() throws Exception {
		String configFilePath = System.getProperty("user.dir") + File.separatorChar + 
				"src" + File.separatorChar + "main" + File.separatorChar + "resources" + 
				File.separatorChar+ "config" + File.separatorChar;
		
		return configFilePath;
    }
    
    public String getConfigFileFullPath() throws Exception {
		String configFilePath = System.getProperty("user.dir") + File.separatorChar + 
				"src" + File.separatorChar + "main" + File.separatorChar + "resources" + 
				File.separatorChar+ "config" + File.separatorChar;
		validateInputFileName(configFilePath, CONFIG_FILE_NAME);
		
		return configFilePath + CONFIG_FILE_NAME;
    }
    
    
    /**
     * 
     * @description Get the value of a property
     * @param configFileName
     * @param propName
     * @return String - value of the property
     */
    public String getPropValue(String configFileName, String propName) throws IOException
    {
        String value = "";
        FileReader configReader = null;
        try
        {
            Properties props = new Properties();
            //load the configuration property file
            configReader = new FileReader(configFileName);
            props.load(configReader);
            //read the property from config file
            value = props.getProperty(propName);
        }
        catch(IOException e)
        {
            throw new IOException(e);
        }
        finally
        {
            if(configReader != null)
            {
                try
                {
                    configReader.close();
                }
                catch(IOException ex)
                {
                    
                }
            }
        }
        return value;
    }
    
    /**
     * 
     * @description Validate input file path and file name and we limit the 
     * input file only as text file
     * @param intendedDir
     * @param fileName
     * @return false: validation failed; true: validation succeeds.
     */
    public boolean validateInputFileName(String intendedDir, String fileName) throws IOException {

        boolean suc = true;
        try {
	        File inputFile = new File(intendedDir + File.separatorChar + fileName);
	        //get canonical full path of the input file
	        String canonicalPath = inputFile.getCanonicalPath();
	        //get canonical path of input file
	        File inputFilePath = new File(intendedDir);
	        String canonicalID = inputFilePath.getCanonicalPath();
	        //If input file sub path is canonical path
	        if (canonicalPath.startsWith(canonicalID)) {
	            final String fileNameRegex = "[^A-Za-z0-9._]";  //file name convention
	            // Check file type to see if it is a prop file
	            if(fileName != null && fileName.endsWith(".properties")) {
	                // if the file is a text file, check file name convention
	                Pattern pattern = Pattern.compile(fileNameRegex);
	                Matcher matcher = pattern.matcher(fileName);
	                // File name contains bad chars
	                if (matcher.find()) {
	                    suc = false;
	                }
	            } 
	            // if the file is not a text file
	            else 
	            {
	                suc = false;
	            }
	        }
	        //If input file sub path is not canonical path
	        else {
	          suc = false;
	          //throw new IllegalStateException("File is outside extraction target directory.");
	        }
        } catch(Exception e) {
        	suc = false;
        	e.printStackTrace();
        }
        
        return suc;
    }
    
    /**
     * validate the input password
     * @param inputPass 
     * @return true if the inputPass does not contain illegal character 
     * return true otherwise return false
     */
    public boolean containInvalidChar(String inputPass)
    {
        boolean suc = false;
        if(inputPass != null && !inputPass.trim().isEmpty()) {
            for(int i=0; i<INVALID_CHARS.length(); i++) {
                if(inputPass.contains(String.valueOf(INVALID_CHARS.charAt(i)))) {
                    suc = true;
                }
            }
        }
        return suc;
    }
}
