/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package realmp3player;

import jaco.mp3.player.MP3Player;
import java.io.File;
import static realmp3player.Realmp3player.input;
import static realmp3player.WalkTheTree.allmymusic;

/**
 *
 * @author costantinot
 */
public class PlaySong {
     
     public static void reproduce() {
    new MP3Player(new File(allmymusic.get(Integer.valueOf(input)).file.toString())).play();
  }

}
