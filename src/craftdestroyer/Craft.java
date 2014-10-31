package craftdestroyer;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Craft {

    private String craft = "images/defaults/default.png";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image;
    private ArrayList missiles;
    private int width;
    private int height;
    private boolean visible;
    
    // TODO: fix this, will vary between ships
    private final int CRAFT_SIZE = 0;

    /**
     * Constructor for default craft
     */
    public Craft() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(this.craft));
        image = ii.getImage();
        x = 0;
        y = 0;
        missiles = new ArrayList();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    /**
     * Constructor for passing in a craft
     * 
     * @param craft The craft 
     */
    public Craft(String craft) {
        this.craft = "images/ships/" + craft;

        ImageIcon ii = new ImageIcon(this.getClass().getResource(this.craft));
        image = ii.getImage();
        x = 0;
        y = 0;
        missiles = new ArrayList();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }
    
    public String getCraft() {
        return craft;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
        
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    public void fire() {
        //missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE/2));
        Sound.SHOOT.play();
        missiles.add(new Missile(x + dx, y + dy));
    }
    
    public ArrayList getMissiles() {
        return missiles;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }

}