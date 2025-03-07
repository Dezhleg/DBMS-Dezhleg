package DataBase;

import Utils.*;

public class DataBase{

    private Config config = new Config();
    private Writer writer = new Writer();
    private Reader reader = new Reader();

    public DataBase() {

    }

    public void create(String dataBaseName, String dataBasePath) {
        dataBaseName = "/" + dataBaseName;
        for (int i = 0; i < config.dataBaseDirectories.length; i++) {
            writer.createDirectory(dataBasePath + dataBaseName + config.dataBaseDirectories[i]);
        }
        for (int i = 0; i < config.dataBaseFiles.length; i++) {
            writer.writeInFile(dataBasePath + dataBaseName + "/" + config.dataBaseFiles[i], "");
        }
        writer.updateFile("config", dataBasePath + dataBaseName, new int[]{0,1});
        config.setConfig();
    } 

    public Boolean connect(String pathToDB) {
        if(!checkDB(pathToDB)) return false;
        writer.updateFile("config", "true", new int[]{0,0});
        writer.updateFile("config", pathToDB, new int[]{0,1});
        config.setConfig();
        return true;
    }

    public Boolean delete(String pathToDir) {
        if(!config.connectToDB) return false;
        if(!checkDB(pathToDir)) return false;
        writer.deleteDirectory(pathToDir);
        config.resetConfigFile();
        config.setConfig();
        return true;
    }

    public void leave() {
        config.resetConfigFile();
    }

    public Boolean checkDB(String pathToDB) {
        boolean bool = false;

        for (int i = 0; i < config.dataBaseDirectories.length; i++) {
            bool = reader.verifyPath(pathToDB + config.dataBaseDirectories[i]);
            if (bool == false) return bool;
        }
        for (int i = 0; i < config.dataBaseFiles.length; i++) {
            bool = reader.verifyPath(pathToDB + "/" +  config.dataBaseFiles[i]);
            if (bool == false) return bool;
        }
        return bool;
    }
}