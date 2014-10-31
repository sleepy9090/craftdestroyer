package craftdestroyer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Missile {

    private int x;
    private int y;
    private final Image image;
    boolean visible;
    private int width;
    private int height;

    //private final int BOARD_WIDTH = 390;
    private int boardWidth;
    private final int MISSILE_SPEED = 10;

    public Missile(int x, int y) {

        ImageIcon ii =
            new ImageIcon(this.getClass().getResource("images/weapons/missile.png"));
        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        
        // TODO: images of shis are bigger because of transparency and this mucks up where the missile shoots from
        //this.x = x;
        //this.y = y;
        // hax
        this.x = x + 250;
        this.y = y + 100;
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        boardWidth = dim.width;

    }


    public Image getImage() {
        return image;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        x += MISSILE_SPEED;
        //if (x > BOARD_WIDTH) {
        if (x > boardWidth) {
            visible = false;
        }
    }
}
