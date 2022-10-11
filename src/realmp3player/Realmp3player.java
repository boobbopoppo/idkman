/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package realmp3player;

import jaco.mp3.player.MP3Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static realmp3player.WalkTheTree.allmymusic;

/**
 *
 * @author costantinot
 */
public class Realmp3player {

    
    int countplaylist = 0;
    static ArrayList<Integer> indexesofsongs;
    static String input;

    public static void main(String[] args) throws IOException, InterruptedException {

        Path fileDir = modifyStartPath();
        PrintSongInfo(fileDir);
        boolean quit = false;
        while (quit == false) {
            MP3Player player = InputClass.input();
            player.play();

        }
    }

    static public void PrintSongInfo(Path fileDir) throws IOException {
        ArrayList<Song> allmymusic = WalkTheTree.allmymusic;
        WalkTheTree visitor = new WalkTheTree();
        Files.walkFileTree(fileDir, visitor);
        for (int i = 0; i < allmymusic.size(); i++) {
            System.out.print(i + "|");
            System.out.print(allmymusic.get(i).title + " --- ");
            System.out.print(allmymusic.get(i).artist + " --- ");
            System.out.println(allmymusic.get(i).album);
        }
    }

    static public Path modifyStartPath() throws FileNotFoundException, IOException {
        Path fileDir = null;
        File options = new File("src/realmp3player/options.txt");
        System.out.println("Select the directory from which you want to scan the system for mp3 files (leave blank for dfault) : ");
        Scanner input = new Scanner(System.in);
        String newFileDir = input.nextLine();
        if (!newFileDir.equalsIgnoreCase("")) {
            BufferedReader file = new BufferedReader(new FileReader("src/realmp3player/options.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                line = newFileDir; // replace the line here
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/realmp3player/options.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            fileDir = Paths.get(Files.readAllLines(Paths.get(options.toString())).get(0));
        } else {
            if (options.length() == 0) {
                if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                    String linuxdefault = "/home";
                    fileDir = Paths.get(linuxdefault);
                } else if (System.getProperty("os.name").equalsIgnoreCase("Windows")) {
                    String windowsdefault = "C:\\Users";
                    fileDir = Paths.get(windowsdefault);
                } else if (System.getProperty("os.name").equalsIgnoreCase("MacOs")) {
                    String defaultMacOs = "Users";
                    fileDir = Paths.get(defaultMacOs);
                }
            } else {
                fileDir = Paths.get(Files.readAllLines(Paths.get(options.toString())).get(0));
            }
        }
        return fileDir;
    }

}
