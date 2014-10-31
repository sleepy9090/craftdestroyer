package craftdestroyer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class CraftDestroyer extends JFrame {

    public CraftDestroyer(String ship) {

        add(new Board(ship));     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
        setLocationRelativeTo(null);
        
        setResizable(false);

        ActionListener escListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    dispose();
                } else {
                    // do nothing
                }
            }
        };

        getRootPane().registerKeyboardAction(escListener,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        setVisible(true);
    }
}
