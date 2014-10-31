package craftdestroyer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    private final Timer timer;
    private final Craft craft;
    private ArrayList aliens;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private ArrayList stars;
    private final Random randomGenerator = new Random();

    // this is dependent on resolution
    // ((max - min) + 1) + min)
    // 20 enemies
    private final int[][] enemyPositions = {
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},

        
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - (dim.width - 200)) + 1) + (dim.width - 200), randomGenerator.nextInt((dim.height - 0) + 1) + 0,}
     };

    // 100 stars
    private final int[][] starPos = {
        // x/y starts in upper left 0,0
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,},
        {randomGenerator.nextInt((dim.width - 0) + 1) + 0, randomGenerator.nextInt((dim.height - 0) + 1) + 0,}
     };

    public Board(String ship) {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        ingame = true;

        initAliens();
        initStars();
        
        if ((ship != null) && !ship.isEmpty()) {
            craft = new Craft(ship);
        } else {
            craft = new Craft();
        }



        timer = new Timer(5, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();   
    }

    private void initAliens() {
        aliens = new ArrayList();
        for (int[] po : enemyPositions) {
//            // Debug
//            System.out.println("Alien position (x): " + po[0] + ", (y): " + po[1]);
//            System.out.println("dim.width (x): " + dim.width + ", dim.height (y): " + dim.height);
            
//            if (po[1] > 1080 || po[1] < 0) {
//                System.out.println("Alien position (x): " + po[0] + ", (y): " + po[1]);
//                System.out.println("dim.width (x): " + dim.width + ", dim.height (y): " + dim.height);
//            }
            
            
            // Keep the aliens from overlapping each other????????????/
            
            aliens.add(new Alien(po[0], po[1]));
        }
    }

    private void initStars() {
        stars = new ArrayList();
        for (int[] starPo : starPos) {
//            // Debug
//            System.out.println("Star position: " + starPo[0] + ", " + starPo[1]);
//            System.out.println("dim.width (x): " + dim.width + ", dim.height (y): " + dim.height);
            stars.add(new Star(starPo[0], starPo[1]));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //System.out.println("Aliens Left: " + aliens.size());
        
        if (ingame) {

            Graphics2D g2d = (Graphics2D)g;

            if (craft.isVisible())
                g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                              this);

            ArrayList missilesArrayList = craft.getMissiles();

            for (int i = 0; i < missilesArrayList.size(); i++) {
                Missile m = (Missile)missilesArrayList.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < aliens.size(); i++) {
                Alien alien = (Alien)aliens.get(i);
                if (alien.isVisible()) {
                    g2d.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
                }
            }

            for (int i = 0; i < stars.size(); i++) {
                Star star = (Star)stars.get(i);
                
                if (star.isVisible()) {
                    g2d.drawImage(star.getImage(), star.getX(), star.getY(), this);
                }
            }

            g2d.setColor(Color.WHITE);
            g2d.drawString("Aliens left: " + aliens.size(), 5, 15);


        } else {
            if (aliens.isEmpty() && craft.isVisible()) {
                
                String msg = "Congratulations, You Win!";
                Font small = new Font("Helvetica", Font.BOLD, 50);
                FontMetrics metr = this.getFontMetrics(small);

                g.setColor(Color.white);
                g.setFont(small);
                int x = (this.getWidth() - metr.stringWidth(msg)) / 2;
                int y = ((this.getHeight() - metr.getHeight()) / 2) - 200;
                g.drawString(msg, x, y);

                String gameOver = "images/other/gameoverwin1.gif";
                ImageIcon ii = new ImageIcon(this.getClass().getResource(gameOver));
                Image image = ii.getImage();
                x = (this.getWidth() - image.getWidth(null)) / 2;
                y = (this.getHeight() - image.getHeight(null)) / 2;
                g.drawImage(image, x, y, this);
                
                msg = "Commander Riker is impressed!";
                small = new Font("Helvetica", Font.BOLD, 50);
                metr = this.getFontMetrics(small);

                g.setColor(Color.white);
                g.setFont(small);
                x = (this.getWidth() - metr.stringWidth(msg)) / 2;
                y = ((this.getHeight() - metr.getHeight()) / 2) + 300;
                g.drawString(msg, x, y);
                
                restart();
            } else {

                String gameOver = "images/other/gameover.gif";
                ImageIcon ii = new ImageIcon(this.getClass().getResource(gameOver));
                Image image = ii.getImage();
                int x = (this.getWidth() - image.getWidth(null)) / 2;
                int y = (this.getHeight() - image.getHeight(null)) / 2;
                g.drawImage(image, x, y, this);

                restart();
            }
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();

    }

    public void checkCollisions() {

        Rectangle craftRectangle = craft.getBounds();
        ArrayList missileArrayList = craft.getMissiles();

        // Keep the stars from overlapping the ship
        for (int i = 0; i < stars.size(); i++) {
            final Star star = (Star) stars.get(i);
            Rectangle starsRectangle = star.getBounds();
            if (craftRectangle.intersects(starsRectangle)) {
                star.setVisible(false);
            } else {
                star.setVisible(true);
            }
            
            // Keep the stars from overlapping the enemy ships
            for (int j = 0; j < aliens.size(); j++) {
                final Alien alien = (Alien) aliens.get(j);
                Rectangle alienRectangle = alien.getBounds();
                if (alienRectangle.intersects(starsRectangle)) {
                    star.setVisible(false);
                } else {
                    //star.setVisible(true);
                }
            }
            
//            // Keep the aliens from overlapping each other
//            for (int j = 0; j < aliens.size(); j++) {
//                final Alien alien = (Alien) aliens.get(j);
//                Rectangle alienRectangle = alien.getBounds();
//                for (int k = 0; k < aliens.size(); k++) {
//                    final Alien alien2 = (Alien) aliens.get(j);
//                    Rectangle alienRectangle2 = alien.getBounds();
//                    if (alienRectangle.intersects(alienRectangle2)) {
//                        alien2.move(1, 0);
//                    } else {
//                        //star.setVisible(true);
//                    }
//                }
//
//            }
        }


        // Check for collision between craft and alien ship
        for (int j = 0; j < aliens.size(); j++) {
            final Alien a = (Alien) aliens.get(j);
            Rectangle alienRectangle = a.getBounds();

            if (craftRectangle.intersects(alienRectangle)) {
                
                // explosion sound
                Sound.EXPLOSION_1.play();
                
                // ship explosion
                String explosion = "images/explosions/explosion2.gif";
                ImageIcon ii = new ImageIcon(this.getClass().getResource(explosion));
                Image image = ii.getImage();
                craft.setImage(image);
                // enemy ship explosion
                explosion = "images/explosions/explosion1.gif";
                ii = new ImageIcon(this.getClass().getResource(explosion));
                image = ii.getImage();
                a.setImage(image);
                

                Timer animationtimer = new Timer(1500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        craft.setVisible(false);
                        a.setVisible(false);
                        ingame = false;
                    }
                });

                animationtimer.start();

            }
        }

        // Check if missile hit alien ship
        for (int i = 0; i < missileArrayList.size(); i++) {
            Missile m = (Missile) missileArrayList.get(i);

            Rectangle r1 = m.getBounds();

            for (int j = 0; j<aliens.size(); j++) {
                final Alien a = (Alien) aliens.get(j);
                Rectangle r2 = a.getBounds();

                if (r1.intersects(r2)) {

                    m.setVisible(false);

                    // explosion sound
                    Sound.EXPLOSION_1.play();
                    
                    // ship explosion
                    String explosion = "images/explosions/explosion1.gif";
                    ImageIcon ii = new ImageIcon(this.getClass().getResource(explosion));
                    Image image = ii.getImage();
                    a.setImage(image);
                    
                    Timer animationtimer = new Timer(500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            a.setVisible(false);
                        }
                    });

                    animationtimer.start();
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (aliens.isEmpty()) {
            ingame = false;
        }

        ArrayList ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = (Missile) ms.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }

        for (int i = 0; i < aliens.size(); i++) {
            Alien a = (Alien) aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }

        for (int i = 0; i < stars.size(); i++) {
            Star star = (Star) stars.get(i);
            star.move();
        }

        craft.move();
        checkCollisions();
        repaint();
    }

    public void restart() {

        ActionListener escListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (JOptionPane.showConfirmDialog(null, "Try Again?", "RESTART",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    //setVisible(false);
                    killAll();
                    ShipSelect shipSelect = new ShipSelect();
                    String[] args = {};
                    shipSelect.main(args);
                    
                } else {
                    // do nothing
                }
            }
        };

        getRootPane().registerKeyboardAction(escListener,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    
    private void killAll() {
        Window w = SwingUtilities.getWindowAncestor(Board.this);
        w.setVisible(false);
        w.dispose();
    }
                
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}