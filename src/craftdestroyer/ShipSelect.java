package craftdestroyer;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;
import javax.swing.*;

/*
 * ShipSelect uses additional files in images directory (shipNameStrings).
 */
public class ShipSelect extends JPanel implements ActionListener {
    private final JLabel picture;
    private final JButton button;
    private String ship;
    private static JFrame jframe;

    public ShipSelect() {
        super(new BorderLayout());
        
        



//        File folder = new File("/home/sleepy/workspaces/craftdestroyer/src/craftdestroyer/images/ships/");
        
        // update this to work when jar is created
        File folder = new File("../craftdestroyer/src/craftdestroyer/images/ships");
        String[] shipNameStrings = folder.list();

        // Debug
//        File[] listOfFiles = folder.listFiles();
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                System.out.println("File " + file.getName());
//            } else if (file.isDirectory()) {
//                System.out.println("Directory " + file.getName());
//            }
//        }

        final JComboBox shipList = new JComboBox(shipNameStrings);
        shipList.setSelectedIndex(0);
        shipList.addActionListener(this);
        this.ship = ( (String) shipList.getSelectedItem());

        
        
        
        
        
        button = new JButton("OK");
        
        ActionListener buttonActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CraftDestroyer craftDestroyer = new CraftDestroyer(getShip());
                
                // Kill the ship select window
                getFrame().setVisible(false);
                getFrame().dispose();
            }
        };
        button.addActionListener(buttonActionListener);
        
        
        
        
        
        
        
        //Set up the picture.
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
        updateLabel(shipNameStrings[shipList.getSelectedIndex()]);
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        //The preferred size is hard-coded to be the width of the
        //widest image and the height of the tallest image + the border.
        //A real program would compute this.
//        picture.setPreferredSize(new Dimension(177, 122+10));
        picture.setPreferredSize(new Dimension(640, 480));

        //Lay out the demo.
        add(shipList, BorderLayout.PAGE_START);
//        add(picture, BorderLayout.PAGE_END);
        add(picture, BorderLayout.CENTER);
        add(button, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    }

    /** Listens to the combo box.
     * @param e */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String shipName = (String) cb.getSelectedItem();
        setShip(shipName);
        updateLabel(shipName);
    }

    protected void updateLabel(String name) {
        ImageIcon icon = createImageIcon("images/ships/" + name);
        picture.setIcon(icon);
        picture.setToolTipText(name.toLowerCase());
        if (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid.
     * @param path
     * @return  */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ShipSelect.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Select Ship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ShipSelect();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        setFrame(frame);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        Sound.SELECT_SHIP.play();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    public String getShip() {
        return ship;
    }
    
    public void setShip(String ship) {
        this.ship = ship;
    }
    
    public static JFrame getFrame() {
        return jframe;
    }
    
    public static void setFrame(JFrame frame) {
        jframe = frame;
    }
}
