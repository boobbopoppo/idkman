/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package realmp3player;

import java.nio.file.Path;

/**
 *
 * @author costantinot
 */
class Song {
    private Path file;
    String title;
    String album;
    String artist;
    private String year;
    Song (Path File, String Title, String Album, String Artist){
        this.file = File;
        this.title = Title;
        this.album = Album;
        this.artist = Artist;
    }
}
