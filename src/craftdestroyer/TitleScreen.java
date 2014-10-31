package craftdestroyer;

import static craftdestroyer.ShipSelect.createImageIcon;
import static craftdestroyer.ShipSelect.getFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sleepy
 */
public class TitleScreen extends JPanel implements ActionListener {
    private final JButton buttonStart;
    
    private final JButton buttonOptions;
    
    private final JButton buttonQuit;
    
    private static JFrame jframe;
    
    private final JLabel picture;
    
    File titleImageFile = new File("images/title/titletemp.png");
    
    public TitleScreen() {
        super(new BorderLayout());
        
        
        
        buttonStart = new JButton("Start");
        buttonOptions = new JButton("Options");
        buttonQuit = new JButton("Quit");
        
        ActionListener buttonStartActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                // Kill the title screen
                getFrame().setVisible(false);
                getFrame().dispose();
                
                ShipSelect shipSelect = new ShipSelect();
                String[] args = {};
                shipSelect.main(args);
            }
        };
        buttonStart.addActionListener(buttonStartActionListener);
        
        ActionListener buttonOptionsActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // options not implemented yet
            }
        };
        buttonOptions.addActionListener(buttonOptionsActionListener);
        
        ActionListener buttonQuitActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setVisible(false);
                getFrame().dispose();
            }
        };
        buttonQuit.addActionListener(buttonQuitActionListener);

        //Set up the picture.
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
        
        updateLabel(titleImageFile.toString());
        
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        
        add(picture, BorderLayout.PAGE_START);
        add(buttonStart, BorderLayout.WEST);
        add(buttonOptions, BorderLayout.CENTER);
        add(buttonQuit, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowTitleScreen();
            }
        });
    }

    private static void createAndShowTitleScreen() {
        //Create and set up the window.
        JFrame frame = new JFrame("Craft Destroyer 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new TitleScreen();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        setFrame(frame);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // do nothing
    }
    
    public static JFrame getFrame() {
        return jframe;
    }
    
    public static void setFrame(JFrame frame) {
        jframe = frame;
    }
    
    protected void updateLabel(String name) {
        System.out.println("NAME: " + name);
        ImageIcon icon = createImageIcon(name);
        picture.setIcon(icon);
        picture.setToolTipText(name.toLowerCase());
        if (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }
    }
}
