package Multi_UI.console;

import java.util.Scanner;

import DataBase.DataBase;
import Utils.Config;

public class Menu {
    /*
     * !!!!!!!!!!
     * После выхода из подпунктов не выводится меню! Исправить!
     */

    private Scanner scan = new Scanner(System.in);

    private Boolean leaver = false;

    //private DataBase dataBase = new DataBase();
    private Config config = new Config();


    public Menu() {
        if(!config.connectToDB){
            while (!config.connectToDB) {
                DataBaseMenu(true);
                if (leaver == true) {
                    leaver = false;
                    break;
                } else {
                    config = new Config();
                }
            }
        }
        if(config.connectToDB) GeneralMenu();
    }

    public void GeneralMenu() {
        class GeneralMenuFunctions {
            void printGLmenu() {
                System.out.println("Управление СУБД: ");
                System.out.println("1. Управление базами данных.");
                System.out.println("2. Управление таблицами.");
                System.out.println("0. Выход.");
                System.out.print("Выберите пункт: ");
            }
        }
        GeneralMenuFunctions gmf = new GeneralMenuFunctions();
        boolean cycle = true;
        while (cycle) {
            gmf.printGLmenu();
            try {
                byte choise = scan.nextByte();
                switch (choise) {
                    case 1:{
                        DataBaseMenu(false);
                    }
                    break;
                    case 2:{
                    }
                    break;
                    case 0:{
                        cycle = false;
                    }
                    break;
                    default:{
                        wrongChoise(true);
                    }
                    break;
                }
            } catch (Exception e) {
                wrongChoise(true);
            }
        }
    }

    public void DataBaseMenu(boolean printTitle) {
        class DataBaseMenuFunctions{
            void printDBMenu() {
                if(printTitle) System.out.println("Управление базами данных:");
                System.out.println("1. Создать базу данных.");
                System.out.println("2. Войти в базу данных.");
                if(config.connectToDB) System.out.println("3. Выйти из базы данных.");
                if(config.connectToDB) System.out.println("4. Удалить базу данных.");
                System.out.println("0. Выход");
                System.out.print("Выберите пункт: ");
            }
            void connectDB() {
                ressc();
                DataBase dataBase = new DataBase();
                System.out.println("Введите абсолютный путь к БД для подключения(Пример C:\\package\\DataBase): ");
                String pathToDB = scan.nextLine();
                if(dataBase.connect(pathToDB)){
                    System.out.println("Успешное подключение к БД!");
                } else {
                    System.out.println("Не удалось подключится к БД!!!");
                    System.out.println("Проверьте путь к БД, возмонжно путь ведет не к БД!");
                }
                config = new Config();
            }
            void createDB() {
                ressc();
                System.out.print("Введи название для БД: ");
                String DBname = scan.nextLine();
                System.out.print("Введите путь для БД: ");
                String pathToDB = scan.nextLine();
                DataBase dataBase = new DataBase();
                dataBase.create(DBname, pathToDB);
            }
            void leaveDB() {
                DataBase dataBase = new DataBase();
                dataBase.leave();
                config = new Config();
            }
            void deleteDB() {
                ressc();
                System.out.println("Введите путь к БД для удаления: ");
                String pathToDelDB = scan.nextLine();
                DataBase dataBase = new DataBase();
                Boolean bool = dataBase.delete(pathToDelDB);
                if(bool) {
                    System.out.println("БД успешно удалена!");
                    config = new Config();
                } else {
                    System.out.println("Ошибка при удалении, возможно выбраный путь не является БД!");
                }
            }
        }
        DataBaseMenuFunctions dbmf = new DataBaseMenuFunctions();
        boolean cycle = true;
        while (cycle) {
            dbmf.printDBMenu();
            try {
                byte choise = scan.nextByte();
                if(config.connectToDB){
                    if(choise < 0 || choise > 4) wrongChoise(true);
                } else {
                    if(choise < 0 || choise > 2) wrongChoise(true);
                }
                switch (choise) {
                    case 1:{
                        dbmf.createDB();
                    }
                        break;
                    case 2:{
                        dbmf.connectDB();
                    }
                    break;
                    case 3:{
                        dbmf.leaveDB();
                        cycle = false;
                    }
                    break;
                    case 4:{
                        dbmf.deleteDB();
                    }
                    break;
                    case 0:{
                        if(!config.connectToDB) leaver = true;
                        cycle = false;
                    }
                    break;
                }
            } catch (Exception e) {
                wrongChoise(true);
            }
        }
    }

    private void wrongChoise(boolean enterValues) {
        System.out.println("Неверный выбор!");
        if(enterValues) System.out.print("Выберите пункт: ");
    }

    private void ressc() {
        if(scan.hasNextLine()) scan.nextLine();
    }
}
