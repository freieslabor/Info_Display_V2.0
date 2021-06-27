package Info_Display.V20.lib.Installation;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class Install {

    private static File appProperties;
    private static Logger log = Logger.getLogger(String.valueOf(this.getClass()));
    private static FileWriter writer;

    public static void main(String[] args) throws IOException {

        log.info("Set variable with start parameter!");
        String username = args[0];
        String password = args[1];
        String port = args[2];

        log.info("Username = " + username + ", password = " + password + ", Serverport = " + port);

        String userDir = System.getProperty("user.dir");
        appProperties = new File(userDir + "/src/main/resources/application.properties");
        if(appProperties.createNewFile()){
            log.info("File: application.properties are created");
            writer = new FileWriter(appProperties);
            writer.write("spring.datasource.url=jdbc:h2:file:" + System.getProperty("user.dir") + "/Database/InfoDisplayDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE\n" +
                    "spring.datasource.driver-class-name=org.h2.Driver\n" +
                    "spring.datasource.data-username=" + username + "\n" +
                    "spring.datasource.data-password=" + password + "\n" +
                    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect\n" +
                    "spring.jpa.hibernate.ddl-auto=update\n" +
                    "\n" +
                    "server.port=" + port);



        }else{
            log.info("File: application.properties already exists!");
        }

        private void checkPropertiesFile(){

        }

    }
}
