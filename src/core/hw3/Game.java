package core.hw3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Game {
    public static void main(String[] args) {
        GameProgress hero1 = new GameProgress(100, 85, 33, 45.3);
        GameProgress hero2 = new GameProgress(33, 48, 99, 12.8);
        GameProgress hero3 = new GameProgress(94, 32, 74, 34);

        saveGame("/Users/evgenijputilin/Desktop/Games/savegames/save1.dat", hero1);
        saveGame("/Users/evgenijputilin/Desktop/Games/savegames/save2.dat", hero2);
        saveGame("/Users/evgenijputilin/Desktop/Games/savegames/save3.dat", hero3);

        List<String> nameFiles = new ArrayList<>();
        nameFiles.add("/Users/evgenijputilin/Desktop/Games/savegames/save1.dat");
        nameFiles.add("/Users/evgenijputilin/Desktop/Games/savegames/save2.dat");
        nameFiles.add("/Users/evgenijputilin/Desktop/Games/savegames/save3.dat");

        zipFiles(nameFiles);


        deleteFiles(nameFiles);

        openZip();

        openProgress(nameFiles.get(0), hero1);
        openProgress(nameFiles.get(1), hero2);
        openProgress(nameFiles.get(2), hero3);


    }

    static void saveGame(String path, GameProgress hero) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(hero);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void zipFiles(List<String> name) {


        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("/Users/evgenijputilin/Desktop/Games/savegames/package.zip"))) {
            for (String d : name) {
                File nameFile = new File(d);
                try (FileInputStream fis = new FileInputStream(d)) {
                    ZipEntry entry = new ZipEntry(nameFile.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception s) {
                    System.out.println(s.getMessage());
                }
            }

        } catch (Exception exe) {
            System.out.println(exe.getMessage());
        }
    }

    static void deleteFiles(List<String> nameFiles) {
        for (String g : nameFiles) {
            File save = new File(g);
            if (save.delete())
                System.out.println("Файл удален");
        }

    }

    static void openZip() {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("/Users/evgenijputilin/Desktop/Games/savegames/package.zip"))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                FileOutputStream fout = new FileOutputStream("/Users/evgenijputilin/Desktop/Games/savegames/" + entry.getName());
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void openProgress(String path, GameProgress hero) {
        try (FileInputStream fis = new FileInputStream(path)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            hero = (GameProgress) ois.readObject();
            System.out.println(hero);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


}
