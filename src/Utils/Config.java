package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Config {

    public Boolean connectToDB = false;
    public String pathToDB = "";
    public String DBname;


    /* Структура конфиг файла.
     * connectToDB - 0
     * pathToDB - 1
     * DBname - 2
     */
    private final String[] configObject = new String[]{
        "false",
        " ",
        " "
    };

    // Название папок и файлов не должны совпдаать на одинковом уровне.
    // Папки в корневой папке базы данных.
    public final String[] dataBaseDirectories = new String[]{
        "/tables",
    };

    // Файлы в корневой папке базы данных.
    public final String[] dataBaseFiles = new String[] {
        "config",
    };

    public Config() {
        setConfig();
    }
    public Config(boolean mainClass) {
        if(mainClass) createConfigFile();
        setConfig();
    }

    private void createConfigFile() {
        Path path = Paths.get("config");
        if(!Files.exists(path)) {
            Writer writer = new Writer();
            writer.writeInFile("config", String.join(",", configObject));
        }
    }
    public void resetConfigFile() {
        Writer writer = new Writer();
        writer.writeInFile("config", String.join(",", configObject));
    }

    public void setConfig() {
        try {
            connectToDB = getConfigBoolean(0);
            pathToDB = getConfigString(1);
            DBname = getConfigString(2);
        } catch (Exception e) {
            errorTemplateDefault(e);
        }
    }

    // Получение конфига.
    public String getConfigString(int index) throws IOException {
        Reader reader = new Reader();
        String text = reader.readFile("config", new int[]{0,index});
        return text;
    }
    public Boolean getConfigBoolean(int index) throws IOException {
        Reader reader = new Reader();
        String text = reader.readFile("config", new int[]{0,index});
        return Boolean.parseBoolean(text);
    }

    // Шаблон для обработки ошибок
    public void errorTemplateDefault(Exception e) {
        System.out.println("Error: " + e);
        e.printStackTrace();
    }
}
