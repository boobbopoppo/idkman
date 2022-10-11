/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package realmp3player;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author costantinot
 */
public class WalkTheTree extends SimpleFileVisitor<Path>{
    static ArrayList<Song> allmymusic = new ArrayList<Song>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException, NoClassDefFoundError {
        String pathtostr = file.toString();
        if (pathtostr.endsWith(".mp3") || pathtostr.endsWith(".MP3")) {
                 Mp3File mp3file = null;
            try {
                mp3file = new Mp3File(file.toString());
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(WalkTheTree.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(WalkTheTree.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
                 if (mp3file.hasId3v1Tag()) {
                     ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                     
                    Song song = new Song(file.toFile(), id3v1Tag.getTitle() , id3v1Tag.getArtist(), id3v1Tag.getAlbum());
                    UnknownParameters(song);
                    // System.out.println(file.getFileName());
                    allmymusic.add(song);
}
                 else if (mp3file.hasId3v2Tag()){
                     ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                     // System.out.println(file.getFileName());
                     Song song = new Song(file.toFile(), id3v2Tag.getTitle(), id3v2Tag.getArtist(), id3v2Tag.getAlbum());
                     UnknownParameters(song);
                     allmymusic.add(song);
                 }
                 else {
                     mp3file.removeCustomTag();
                     Song song = new Song (file.toFile(), file.toString(), "Unknown artist", "Unknown album");
                     UnknownParameters(song);
                     allmymusic.add(song);
                 }
    }
        return FileVisitResult.CONTINUE;
        
}
    @Override
    public FileVisitResult visitFileFailed (Path file, IOException exc) throws IOException{
        return FileVisitResult.CONTINUE;
    }
    public void UnknownParameters (Song song){
        if (song.title == null || song.title.equalsIgnoreCase("")){
            song.title = "Unknown song";
        }
        else if (song.artist == null || song.artist.equalsIgnoreCase("")){
            song.artist = "Unknown Artist";
        }
        else if (song.album == null || song.album.equalsIgnoreCase("")){
            song.album = "Unknown Album";
        }
    }
}
