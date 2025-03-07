package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

    // Читают файлы.
    // Прочтет весь файл и вернет список строк.
    public List<String> readFile(String pathToFile) throws IOException {
        Path path = Paths.get(pathToFile);
        return Files.readAllLines(path);
    }
    // Прочтет весь файл и вернет строку по индексу.
    public String readFile(String pathToFile, int index) throws IOException {
        Path path = Paths.get(pathToFile);
        return Files.readAllLines(path).get(index).toString();
    }
    // Прочтет весь файл и вернет массив со словами из строки по индексу.
    public String[] readFileReturnStringArr(String pathToFile, int index) throws IOException {
        Path path = Paths.get(pathToFile);
        return Files.readAllLines(path).get(index).toString().split(",");
    }
    // Прочтет весь файл и вернет текст по индексу
    public String readFile(String pathToFile, int[] index) throws IOException {
        Path path = Paths.get(pathToFile);
        return Files.readAllLines(path).get(index[0]).toString().split(",")[index[1]];
    }

    // Проверяют наличие директорий по пути
    public boolean verifyPath(String pathToFile) {
        Path path = Paths.get(pathToFile);
        return Files.exists(path);
    }
}
