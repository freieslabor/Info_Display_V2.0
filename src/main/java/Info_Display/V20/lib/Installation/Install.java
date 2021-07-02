package Info_Display.V20.lib.Installation;

import Info_Display.V20.lib.Exception.Install.CreateApplicationPropertiesFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class Install {

    private static File appProperties;
    private static Logger log = Logger.getLogger("");

    private static String username;
    private static String password;
    private static String port;

    private static String pathInProject = "/src/main/resources/application2.properties";

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

        String userDir = System.getProperty("user.dir");
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

            if(checkWriteFile(contentOfApplicationProperties)){
                log.info("Application.properties created successfully!");
            }else{
                log.info("Something failed by writing application.properties! \n Please do this manually");
            }

        }else{
            log.info("File: application.properties already exists!");
        }
    }

    private static boolean checkWriteFile(String controlString) throws IOException, CreateApplicationPropertiesFileException {
        String path = System.getProperty("user.dir") + pathInProject;

        List<String> lines = Files.readAllLines(Path.of(path));

        log.info(String.valueOf(lines));

        if(lines.get(0).equals(controlString)){
            return true;
        }else{
            throw new CreateApplicationPropertiesFileException("No the same content in application.properties!");
        }
    }

}
