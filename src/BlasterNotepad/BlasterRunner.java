package BlasterNotepad;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

@SuppressWarnings("serial")
public class BlasterRunner {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BlasterRunner.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BlasterMain b = new BlasterMain();
                b.setVisible(true);
                b.setLocationRelativeTo(null);
            }
        });

    }

}
