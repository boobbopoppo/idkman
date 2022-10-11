package realmp3player;

import jaco.mp3.player.MP3Player;
import java.io.File;
import java.util.Scanner;
import static realmp3player.Realmp3player.input;
import static realmp3player.WalkTheTree.allmymusic;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author costantinot
 */
public class InputClass {

    static MP3Player player = new MP3Player();

    public static MP3Player input() {
        File f = null;
        Scanner selection = new Scanner(System.in);
        try {
            input = selection.nextLine();
            switch (input) {
                case "play":
                    if (input.equalsIgnoreCase("pause")){
                        player.pause();
                        break;
                    }
                    System.out.println("Select the desired song (index):");
                    input = selection.nextLine();
                    f = allmymusic.get(Integer.valueOf(input)).file;
                    player = new MP3Player(f);
                    break;
                case "list":
                    input = selection.nextLine();
                    switch (input) {
                        case "songs", "Songs":
                            System.out.println("This is a list of all your songs: ");
                            break;
                        case "playlist", "Playlist":
                            System.out.println("This is a list of the current playlist: ");
                            break;
                        case "artists", "Artists":
                            System.out.println("This is a list of all your artists: select one (index): ");
                            break;
                        case "albums", "Albums":
                            System.out.println("This is a list of all your albums: select one (index): ");
                            break;
                    }
                    break;

            }

        } catch (NumberFormatException ex) {
            System.out.println("Sorry, it seems like " + input + " is not a valid input, please insert the number for the correspoding song you want to reproduce.");
            InputClass.input();
        } catch (NullPointerException e) {
            System.out.println("Sorry, but it seems that we couldn't find the desired song to reproduce: check your input or go fuck yourself.");
            InputClass.input();
        }

        return player;
    }

}
