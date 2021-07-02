package Info_Display.V20.lib.Installation;

import Info_Display.V20.lib.Exception.Install.CreateApplicationPropertiesFileException;

import java.io.*;
import java.util.logging.Logger;

public class Install {

    private static File appProperties;
    private static Logger log = Logger.getLogger("");

    private static String username;
    private static String password;
    private static String port;

    private static String pathInProject = "/src/main/resources/application.properties";
    private static  String userDir = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException, CreateApplicationPropertiesFileException {

        log.info("Set variable with start parameter!");

       try{
           username = args[0];
           password = args[1];
           port = args[2];
       }catch (ArrayIndexOutOfBoundsException e){
           log.warning("Using default settings!");
           username = "InfoDisplay";
           password = "InfoDisplay";
           port = "8080";
       }

        log.info("Username = " + username + ", password = " + password + ", Serverport = " + port);


        appProperties = new File(userDir + pathInProject);
        if(appProperties.createNewFile()){

            String contentOfApplicationProperties = "spring.datasource.url=jdbc:h2:file:" + System.getProperty("user.dir") + "/Database/InfoDisplayDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE\n" +
                    "spring.datasource.driver-class-name=org.h2.Driver\n" +
                    "spring.datasource.data-username=" + username + "\n" +
                    "spring.datasource.data-password=" + password + "\n" +
                    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect\n" +
                    "spring.jpa.hibernate.ddl-auto=update\n" +
                    "server.port=" + port;

            log.info("File: application.properties are created");
            FileWriter myWriter = new FileWriter(appProperties);
            myWriter.write(contentOfApplicationProperties);
            myWriter.close();

            if(checkWriteFile()){
                log.info("Application.properties created successfully!");
            }else{
                log.info("Something failed by writing application.properties! \n Please do this manually");
            }

        }else{
            log.info("File: application.properties already exists!");
        }
    }

    private static boolean checkWriteFile() throws IOException, CreateApplicationPropertiesFileException {

        BufferedReader buffer = new BufferedReader(new FileReader(userDir + pathInProject));

        if(buffer.readLine() != null){
            return true;
        }else{
            throw new CreateApplicationPropertiesFileException("No the same content in application.properties!");
        }
    }

}