package BlasterNotepad;

import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;

public class EventClass extends BlasterMain {

    private JTextArea JTA;
    private JLabel Text;
    String file;
    IconsCode icon = new IconsCode();
    ArrayList linelength = new ArrayList();
    boolean isNewFile = true;
  

    public EventClass(JTextArea JTA) {
        this.JTA = JTA;
    }

    public EventClass(JLabel text) {
        this.Text = text;
    }

    public EventClass(boolean isNewFile, JTextArea JTA) {
        this.isNewFile = isNewFile;
        this.JTA = JTA;
    }

    public void getGotoEvent() {

        String linetext = null;
        int line = 0;
        boolean fnd;

        do {
            fnd = true;
            linetext = JOptionPane.showInputDialog("Goto Line?");
            try {
                line = Integer.parseInt(linetext);
            } catch (NumberFormatException ez) {
                if (linetext != null) {
                    JOptionPane.showMessageDialog(null, "Enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                    fnd = false;
                }

            }

            if (line <= 0 && linetext != null) {
                JOptionPane.showMessageDialog(null, "Enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                fnd = false;
            }

        } while (!fnd);
        if (linetext != null) {
            getLinePosition();

            if ((line - 1) >= linelength.size()) {
                JOptionPane.showMessageDialog(null, "Number does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                try {
                    JTA.requestFocus();
                    // there might be a problem here with vector and arraylist
                    JTA.setCaretPosition(((Integer) linelength.get(line - 1)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Bad Position", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private void getLinePosition() {
        linelength = new ArrayList();
        String text = JTA.getText();
        int width = JTA.getWidth();
        StringTokenizer st = new StringTokenizer(text, "\n", true);
        String str = " ";
        int len = 0;
        linelength.add(new Integer(0));
        while (st.hasMoreTokens()) {
            String tokens = st.nextToken();
            int w = JTA.getGraphics().getFontMetrics(JTA.getGraphics().getFont()).stringWidth(str + tokens);
            if (w > width || tokens.charAt(0) == '\n') {
                len = len + str.length();
                if (tokens.charAt(0) == '\n') {
                    linelength.add(new Integer(len));
                } else {
                    linelength.add(new Integer(len - 1));
                }
                str = tokens;
            } else {
                str = str + tokens;
            }
        }
    }

   

    
    
    

    public void confirmClosing() {

        Object object[] = {"Save", "Don't Save", "Cancel"};
        int s = JOptionPane.CANCEL_OPTION;

        if (isNewFile && !(JTA.getText().equals("".trim()))) {
            s = JOptionPane.showOptionDialog(null, "Do you want to save this file?", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, object, object[0]);

            if (s == JOptionPane.YES_OPTION) {
                saveAs();
                new Timer(1000, new ActionListener() {
                    int count = 0;

                    public void actionPerformed(ActionEvent e) {
                        count++;
                        if (count == 1) {

                            System.exit(0);
                        }
                    }

                }).start();

            } else if (s == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else {
            }

        } else {
            System.exit(0);
        }

    }

    public void getAboutUs() {
        String er = "KPANSH PAD is a modern notepad use in any text files\n Produced by Multimega Groups \n Licensed As GNS";
        JOptionPane.showMessageDialog(printButton, er, "About KPANSH PAD", 0, icon.getNewImageIcon());
    }

    public void getTimeDate() {
        String pm;
        Calendar cal = new GregorianCalendar();
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int am = cal.get(Calendar.AM_PM);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        if (am == 1) {
            pm = "PM";
        } else {
            pm = "AM";
        }
        JTA.setText(JTA.getText() + "  " + hour + ":" + min + " " + pm + " " + day + "/" + month + "/" + year);

    }

    public void getBackGroundColor() {
        Color selectColor = JColorChooser.showDialog(null, "Choose A Background Color", JTA.getForeground());
        if (selectColor != null) {
            JTA.setBackground(selectColor);
        } else if (selectColor == null) {
            JTA.setBackground(Color.WHITE);
        }
    }

    public void getForegroundColor() {
        Color selectColor = JColorChooser.showDialog(null, "Choose A Background Color", JTA.getForeground());
        if (selectColor != null) {
            JTA.setForeground(selectColor);
        } else if (selectColor == null) {
            JTA.setForeground(Color.WHITE);
        }
    }



    public void syntaxHighlighter() {
        String text = JTA.getText();
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("abstract", 1);
        map.put("double", 2);
        map.put("int", 3);
        map.put("super", 4);
        map.put("assert", 5);
        map.put("else", 6);
        map.put("switch", 7);
        map.put("boolean", 8);
        map.put("enum", 9);
        map.put("long", 10);
        map.put("sybchronised", 11);
        map.put("break", 12);
        map.put("extends", 13);
        map.put("native", 14);
        map.put("this", 15);
        map.put("byte", 16);
        map.put("for", 17);
        map.put("new", 18);
        map.put("throw", 19);
        map.put("case", 20);
        map.put("final", 21);
        map.put("package", 22);
        map.put("throws", 23);
        map.put("char", 24);
        map.put("float", 25);
        map.put("protected", 26);
        map.put("try", 27);
        map.put("catch", 28);
        map.put("finally", 29);
        map.put("private", 30);
        map.put("transient", 31);
        map.put("class", 32);
        map.put("goto", 33);
        map.put("public", 34);
        map.put("void", 35);
        map.put("cost", 36);
        map.put("if", 37);
        map.put("return", 38);
        map.put("volatile", 39);
        map.put("continue", 40);
        map.put("implements", 41);
        map.put("short", 42);
        map.put("while", 43);
        map.put("default", 44);
        map.put("import", 45);
        map.put("static", 46);
        map.put("do", 47);
        map.put("instanceOf", 48);
        map.put("strictfp", 49);
        map.put("interface", 50);

        StringTokenizer textBreak = new StringTokenizer(text, " " + "[\n\t\r./,;:!?(){", true);
        for (int i = 1; i < textBreak.countTokens(); i++) {
            String text1 = textBreak.nextElement().toString();

            if (text1.length() > 0) {
                if (map.get(text1) == null) {

                } else {
                    int off;
                    //JTA.setSelectionColor(text1);
                    JTA.setSelectedTextColor(Color.red);
                    JTA.setSelectionStart(0);
                    JTA.setSelectionEnd(5);
                    try {
                        String t = JTA.getText(0, 4);

                    } catch (BadLocationException ex) {
                        Logger.getLogger(EventClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(map.get(text1));
                }
            }
        }
    }

}
