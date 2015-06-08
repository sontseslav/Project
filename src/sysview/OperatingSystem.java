/*
    System.getProperty("");
    First, if there is a security manager, its checkPropertyAccess method is 
    called with the key as its argument. 
    This may result in a SecurityException.
 */
package sysview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

/**
 *
 * @author user
 */
public class OperatingSystem {
    //System.getProperty("") or System.getProperties() and then parse output
    private String osName = "os.name";
    private String osVerison = "os.version";
    private String osArch = "os.arch";
    private String dataModel = "sun.arch.data.model";
    private String tempDir = "java.io.tmpdir";
    private String encoding = "sun.jnu.encoding";
    
    private String javaName = "java.runtime.name";
    private String javaVWVendor = "java.vm.vendor";
    private String javaRuntimeVer = "java.runtime.version";
    private String javaHomeDir = "java.home";
    
    private String userName = "user.name";
    private String userHost;  //InetAddress.getLocalHost().getHostName() or Runtime.getRuntime().exec("hostname")
    private String userHomeDir = "user.home";
    private String userCountry = "user.country";
    private String userLanguage = "user.language";
    
    protected OperatingSystem() throws SecurityException{
        //use hash table?
        osName = System.getProperty(osName);
        osVerison = System.getProperty(osVerison);
        osArch = System.getProperty(osArch);
        dataModel = System.getProperty(dataModel);
        tempDir = System.getProperty(tempDir);
        encoding = System.getProperty(encoding);
        
        javaName = System.getProperty(javaName);
        javaVWVendor = System.getProperty(javaVWVendor);
        javaRuntimeVer = System.getProperty(javaRuntimeVer);
        javaHomeDir = System.getProperty(javaHomeDir);
        
        userName = System.getProperty(userName);
        userHost = this.getHostname();
        if (userHost == null){
            userHost = this.getHostnameViaHostnameCmd();
        }
        userHomeDir = System.getProperty(userHomeDir);
        userCountry = System.getProperty(userCountry);
        userLanguage = System.getProperty(userLanguage);
    }
    
    protected String getOSInfo(){
        return String.format("Information about machine %s:%n"
                + "OS: %s %s %s; bitness: %s;%n"
                + "Temp dir: %s;%nencoding: %s%n%n"
                + "Java VM: %s;%n"
                + "Vendor: %s; version: %s;%n"
                + "Home dir: %s%n%n"
                + "User info:%n"
                + "Name: %s, country: %s, language: %s%n"
                + "Home dir: %s",
                userHost,osName,osVerison,osArch,dataModel,tempDir,encoding,
                javaName,javaVWVendor,javaRuntimeVer,javaHomeDir,
                userName,userCountry,userLanguage,userHomeDir);
    }
    
    private String getHostname(){
        try{
            return java.net.InetAddress.getLocalHost().getHostName();
        }catch(UnknownHostException ex){
            return null;
        }
    }
    
    private String getHostnameViaHostnameCmd(){
        try{
            Process proc = Runtime.getRuntime().exec("hostname");
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            return input.readLine();
        }catch(IOException ex){
            return ex.toString();
        }
    }

}

