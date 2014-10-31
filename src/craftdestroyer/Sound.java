package craftdestroyer;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author Shawn M. Crawford
 */


public class Sound {
    public static final AudioClip SELECT_SHIP = Applet.newAudioClip(Sound.class.getResource("sound/selectship.wav"));
    
    public static final AudioClip EXPLOSION_1 = Applet.newAudioClip(Sound.class.getResource("sound/explosion1.wav"));
    
    public static final AudioClip SHOOT = Applet.newAudioClip(Sound.class.getResource("sound/shoot.wav"));
    
}
