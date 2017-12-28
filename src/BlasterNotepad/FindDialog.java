package BlasterNotepad;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class FindDialog extends BlasterMain {

    JRadioButton up, down;
    ButtonGroup bg;
    public boolean matchCase;
    private JTextArea JTA;
    JTextField text;
    private String textfield;

    public FindDialog(JTextArea JTA) {
        //super("KPANSH Editor");
        this.JTA = JTA;
        Init();

    }

    public void Init() {
        final JDialog pane = new JDialog(this, false);
        pane.setResizable(false);
        pane.setTitle("Find");
        pane.setVisible(true);
        pane.setSize(400, 150);
        pane.setLayout(new FlowLayout());
        pane.setLocation(getX() + 100, getY() + 200);
        pane.setLocationRelativeTo(null);
        pane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel paneLabel = new JPanel();
        pane.add(paneLabel);
        paneLabel.setVisible(true);

        JLabel findWhat = new JLabel("Find What");
        findWhat.setLocation(2, 2);
        text = new JTextField(20);

        paneLabel.add(findWhat);
        paneLabel.add(text);

        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        paneButton.setVisible(true);
        paneButton.setLocation(getX() - 20, getY() - 20);
        paneButton.setBorder(new TitledBorder("Buttons"));
        pane.add(paneButton);

        JButton findButton = new JButton("Find Next");
        JButton cancelButton = new JButton("Cancel");

        paneButton.add(findButton);
        paneButton.add(cancelButton);

        JPanel paneRadio = new JPanel();
        pane.add(paneRadio);
        paneRadio.setLocation(50, 30);
        paneRadio.setBorder(new TitledBorder("Direction"));
        bg = new ButtonGroup();
        up = new JRadioButton("Up", true);
        down = new JRadioButton("Down", false);
        paneRadio.add(up);
        paneRadio.add(down);
        bg.add(up);
        bg.add(down);
        

        JPanel paneCase = new JPanel();
        JLabel match = new JLabel("Match Case");
        final JCheckBox matchcase = new JCheckBox();
        pane.add(paneCase);
        paneCase.add(matchcase);
        paneCase.add(match);
        matchcase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (matchcase.isSelected()) {
                    matchCase = true;
                } else {
                    matchCase = false;
                }

            }
        });

        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                pane.dispose();
            }

        });

    }

    public void search() {
        String text1 = JTA.getText();

        textfield = text.getText();
        bool = true;
        int off = 0;

        if (!matchCase) {
            text1 = text1.toLowerCase();
            textfield = textfield.toLowerCase();
        }
        if (bg.getSelection() == down.getModel()) {
            off = text1.indexOf(textfield, JTA.getSelectionEnd());
        } else {
            off = text1.lastIndexOf(textfield, JTA.getSelectionStart() - 1);
        }

        if (off != 1) {
            try {
                JTA.setCaretPosition(off);
                JTA.moveCaretPosition(off + textfield.length());
                JTA.getCaret().setSelectionVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Cant find " + textfield);
            }
        }
    }

}
