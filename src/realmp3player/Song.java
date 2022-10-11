/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package realmp3player;

import jaco.mp3.player.MP3Player;
import java.io.File;
import static realmp3player.InputClass.player;


/**
 *
 * @author costantinot
 */
class Song {
    File file;
    String title;
    String album;
    String artist;
    private String year;
    Song (File File, String Title, String Album, String Artist){
        this.file = File;
        this.title = Title;
        this.album = Album;
        this.artist = Artist;
    }
    public static void PlaySong(File f) {
        System.gc();
        player = new MP3Player(f);
        player.play();
    }
}
