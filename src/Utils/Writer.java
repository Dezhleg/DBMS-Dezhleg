package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Writer { // Класс в котором записываются, перезаписываются, удаляются файлы/директории.

    // Обновление текста в файле.
    // Обнавляет i элемент в j строке.(Принимает массив с индексомми [j,i])
    public void updateFile(String pathToFile, String value, int[] index) {
        Path path = Paths.get(pathToFile);
        if(index.length != 2) {
            System.out.println("Array does not have length = 2");
            return;
        }
        try {
            List<String> lines = Files.readAllLines(path);
            String[] texts = lines.get(index[0]).split(",");
            texts[index[1]] = value;
            String text = String.join(",", texts);
            writeInFile(pathToFile, text);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    // Переписывает файл полностью. Обнволяет содержимое файла на новые данные.
    public void writeInFile(String pathToFile, String value){
        Path path = Paths.get(pathToFile);
        try {
            Files.write(path, value.getBytes());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    // Создает директории.
    public void createDirectory(String pathToFile) {
        Path path = Paths.get(pathToFile);
        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
    // Удаляет директорию.
    public void deleteDirectory(String pathToDir) {
        Path path = Paths.get(pathToDir);
        try {
            // Удаляем все содержимое директории рекурсивно
            Files.walk(path) // Получаем поток всех файлов и папок внутри
                 .sorted((p1, p2) -> p2.compareTo(p1)) // Сортируем в обратном порядке (сначала содержимое, потом директория)
                 .forEach(p -> {
                     try {
                         Files.delete(p); // Удаляем файл или директорию
                     } catch (IOException e) {
                         throw new RuntimeException("Ошибка при удалении: " + p, e);
                     }
                 });
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при удалении директории: " + pathToDir, e);
        }
    }
}
