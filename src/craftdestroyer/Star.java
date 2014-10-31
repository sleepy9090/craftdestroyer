package craftdestroyer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;


public class Star {

    private final String star = "images/other/star.png";

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;

    public Star(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(star));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }


    public void move() {
        if (x < 0) {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            x = dim.width;
        }
        x -= 1;
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
}
