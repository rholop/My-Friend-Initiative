package config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {
    Properties properties;
    /**
     * Retrieves our key from the config file
     */
    public Config()
    {
        properties = new Properties();
        try {
            FileReader reader = new FileReader("config.properties");
            properties.load(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setConfig(String directory, String url) {
        String userDirectory = System.getProperty("user.dir");
        File newFile = new File(userDirectory + "/config.properties");
        try {
            if (newFile.exists()) {
                newFile.delete();
            }
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(newFile);
            myWriter.write("directory = " + directory + "\n");
            myWriter.write("url = " + url);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getDirectory() {
        return this.properties.getProperty("directory");
    }

    public String getURL() {
        return this.properties.getProperty("url");
    }
}
