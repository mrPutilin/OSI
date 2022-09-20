package core.hw3;

import java.io.*;



public class Main {

    static StringBuilder log = new StringBuilder();

    public static void main(String[] args) {
        File dir = new File("/Users/evgenijputilin/Desktop/Games");

        File myFile = new File("/Users/evgenijputilin/Desktop/Games");


        createDir(dir, "src");

        createDir(dir, "src/main");
        createFile(myFile, "src/main/Main.java");
        createFile(myFile, "src/main/Utils.java");


        createDir(dir, "src/test");

        createDir(dir, "res");
        createDir(dir,"res/drawables");
        createDir(dir, "res/vectors");
        createDir(dir, "res/icons");

        createDir(dir, "savegames");

        createDir(dir, "temp");
        createFile(myFile, "temp/temp.txt");


        try (FileWriter writer = new FileWriter("/Users/evgenijputilin/Desktop/Games/temp/temp.txt", false)) {
            writer.write(log.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void createDir(File nameDir1, String nameDir) {
        File dir1 = new File(nameDir1, nameDir);
        if (dir1.mkdir()) {
            log.append("Каталог создан" + '\n');
            System.out.println("Каталог создан");
        } else
            System.out.println("Каталог НЕ создан");

    }

    static void createFile(File directory, String path) {
        File myFile = new File(directory, path);
        try {
            if (myFile.createNewFile()) {
                log.append("Файл был создан" + '\n');
                System.out.println("Файл был создан");
            } else
                System.out.println("Файл НЕ был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

