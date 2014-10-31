package craftdestroyer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;


public class Alien {

    private int x;
    private int y;
    private final int width;
    private final int height;
    private boolean visible;
    private Image image;

    public Alien(int x, int y) {
        
        // Pull a random enemy ship from the enemyships directory
        //File folder = new File("/home/sleepy/workspaces/craftdestroyer/src/craftdestroyer/images/enemyships/");
        // update this to work when jar is created
        File folder = new File("../craftdestroyer/src/craftdestroyer/images/enemyships");
        String[] enemyShipNameStrings = folder.list();
        String randomAlienCraft = "images/enemyships/" + (enemyShipNameStrings[new Random().nextInt(enemyShipNameStrings.length)]);
        
        
        ImageIcon ii = new ImageIcon(this.getClass().getResource(randomAlienCraft));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }


    public void move() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        if (x < 0) {
            x = dim.width;
        }
        x -= 1;
        
        //System.out.println("x: " + x + " y: " + y + " dim.width: " + dim.width + " dim.height: " + dim.height);

        if (y > 0 || y < dim.height) {
            Random randomGenerator = new Random();
            int direction = randomGenerator.nextInt(2);
            //System.out.println("direction: " + direction);
            if (direction == 0) {
                y -= 1;
            } else {
                y += 1;
            }
            
        } else if (y < 0) {
            y += 1;
        } else {
            y -= 1;
        }
    }

    public void move(int x1, int y1) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        if (x < 0) {
            x = dim.width;
        }
        x -= x1;
        
        //System.out.println("x: " + x + " y: " + y + " dim.width: " + dim.width + " dim.height: " + dim.height);

        if (y > 0 || y < dim.height) {
            Random randomGenerator = new Random();
            int direction = randomGenerator.nextInt(2);
            //System.out.println("direction: " + direction);
            if (direction == 0) {
                y -= y1;
            } else {
                y += y1;
            }
            
        } else if (y < 0) {
            y += y1;
        } else {
            y -= y1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public void setImage(Image image) {
        this.image = image;
        
    }
}
