package BlasterNotepad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.undo.*;

public class Dashboard extends JPanel implements ActionListener {

    public static JLabel errorStatus;
    public static JLabel fileExtensionStatus;
    public static JLabel lineIndicatorStatus;
    public static JLabel title, close, minimized, maximized;
    JPopupMenu popmenu;

    /**
     * Declaring all private, public and static variables here
     *
     */
    public UndoManager manager = new UndoManager();
    public JFileChooser jFileChooser1 = new JFileChooser();
    public JTextArea JTA;
    IconsCode icon;
    File file;
    private int linenum = 1, mouseX, mouseY;
    AutoComplete autocomplete;
    private boolean hasListener = false, mousePressed;
    public File lastpath;
    private JPanel north, west;
    public JToolBar statusBar;
    public final Font font = new Font("Monospaced", Font.PLAIN, 13);
    public String textfield, getErrors, textselect;
    public URL imgURL = getClass().getResource("icons/i.png");
    public boolean isNewFile = true, bool = false, isSelect = false;

    // Declaring all
    //public JMenuItem undoItem, redoItem, cutItem, copyItem, pasteItem, 
    JMenuItem black, undo, redo, cut, copy, delete, paste, select;
    JMenuItem showUnicode, insertUnicode, openIME, right, reconversion, backGround, foreGround, deleteItem;
    //       findItem, findnextItem, replaceItem, gotoItem, selectAllItem, timeDateItem;
    // public JCheckBoxMenuItem wordwrapItem, StatusBarItem, right, LineNumber;
    public JTextArea editArea;

    JMenu findItem, findnextItem, replaceItem, gotoItem, selectAllItem, timeDateItem;

    public JButton saveButton;
    public JButton openButton;
    public JButton closeButton;
    public JButton printButton;
    public JButton saveAllButton;
    public JButton newButton;
    public JButton findButton;
    public JButton closeAllButton;
    public JButton cutButton;
    public JButton copyButton;
    public JButton pasteButton;
    public JButton undoButton;
    public JButton redoButton;
    public JButton rec;
    public JButton replaceButton;

    JMenuItem newItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JMenuItem openItem;
    JMenuItem saveAsItem;
    JMenuItem pageItem;
    JMenuItem printItem;

    public Dashboard() {
        icon = new IconsCode();

        Init();

    }

    private void Init() {

        JPanel dashboard = new JPanel(new BorderLayout());
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(1, 1, 1, 1));
        JTA = new JTextArea();
        //north.setBackground(new Color(18, 30, 48));
        JTA.setRequestFocusEnabled(true);
        JTA.setCaretColor(Color.BLACK);
        JTA.setMargin(new Insets(3, 5, 0, 5));
        JTA.setWrapStyleWord(true);
        JTA.setFont(font);
        JTA.setBackground(Color.WHITE);
        JTA.setForeground(Color.BLACK);

        final JScrollPane Scroll = new JScrollPane(JTA);
        Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        dashboard.add(Scroll, BorderLayout.CENTER);

        statusBar = new JToolBar();
        statusBar.setLayout(new GridLayout());
        statusBar.setBorderPainted(false);
        statusBar.setRollover(false);

        errorStatus = new JLabel();
        lineIndicatorStatus = new JLabel();
        fileExtensionStatus = new JLabel("Text File");
        statusBar.add(errorStatus);
        statusBar.addSeparator();
        statusBar.addSeparator();
        statusBar.add(errorStatus);
        statusBar.addSeparator();
        statusBar.addSeparator();
        statusBar.add(fileExtensionStatus);
        statusBar.add(lineIndicatorStatus);
        dashboard.add(statusBar, BorderLayout.SOUTH);

        // Give the status update value
        updateStatus(1, 1);
        add(dashboard, BorderLayout.CENTER);

        // Declaring menu Bar
        // Create different themes menu
        popmenu = new JPopupMenu();

        undo = new JMenuItem("Undo", icon.getUndoImageIcon());
        redo = new JMenuItem("Redo", icon.getRedoImageIcon());
        cut = new JMenuItem("Cut", icon.getCutImageIcon());
        copy = new JMenuItem("Copy", icon.getCopyImageIcon());
        delete = new JMenuItem("Delete", icon.getDeleteImageIcon());

        delete.setEnabled(false);
        copy.setEnabled(false);
        cut.setEnabled(false);

        paste = new JMenuItem("Paste", icon.getPasteImageIcon());
        select = new JMenuItem("Select All");
        right = new JCheckBoxMenuItem("Right to left reading order");
        showUnicode = new JMenuItem("Show Unicode control characters");
        insertUnicode = new JMenuItem("Insert Unicode control characters");
        openIME = new JMenuItem("Open IME");
        reconversion = new JMenuItem("Reconversion");
        popmenu.add(undo);
        popmenu.add(redo);
        popmenu.addSeparator();
        popmenu.add(cut);
        popmenu.add(copy);
        popmenu.add(paste);
        popmenu.add(delete);
        popmenu.addSeparator();
        popmenu.add(select);
        popmenu.addSeparator();
        popmenu.add(right);
        popmenu.add(showUnicode);
        popmenu.add(insertUnicode);
        popmenu.addSeparator();
        popmenu.add(openIME);
        popmenu.add(reconversion);
        JTA.setComponentPopupMenu(popmenu);

        // Adding action listener to popup menu
//        black.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        right.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        delete.addActionListener(this);
        select.addActionListener(this);

        final LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(JTA);
        lineNumberingTextArea.setFont(font);
        Scroll.setRowHeaderView(lineNumberingTextArea);

        JTA.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                lineNumberingTextArea.updateLineNumbers();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                lineNumberingTextArea.updateLineNumbers();
                // isNewFile = true;
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                lineNumberingTextArea.updateLineNumbers();
                //  isNewFile = true;
            }
        });

        JTA.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent event) {
                manager.addEdit(event.getEdit());

            }

        });

        JTA.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                int start = JTA.getSelectionStart();
                int end = JTA.getSelectionEnd();
                String text = JTA.getSelectedText();

                try {
                    if (start > -1 && end > -1 && !(text.isEmpty())) {
                        isSelect = true;
                        //revalidate();
                    } else if (start == 0 && end == 0 && (text.isEmpty())) {
                        isSelect = false;
                    }

                } catch (NullPointerException err) {
                    System.err.flush();
                }

            }

        });

        JTA.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                textselect = JTA.getSelectedText();
                isSelect = textselect != null;

            }
        });

        JTA.addCaretListener(new CaretListener() {
            // Each time the caret is moved, it will trigger the listener and
            // its method caretUpdate.
            // It will then pass the event to the update method including the
            // source of the event (which is our textArea control)

            @Override
            public void caretUpdate(CaretEvent e) {
//                checkEnabledbtn();

                editArea = (JTextArea) e.getSource();

                // Lets start with some default values for the line and column.
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply
                // ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the
                    // number of where the caret is in relation to the start of
                    // the JTextArea
                    // in the upper left corner. We use this position to find
                    // offset values (e.g what line we are on for the given
                    // position as well as
                    // what position that line starts on.
                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the
                    // overall caret position.
                    // So lets say that we are on line 5 and that line starts at
                    // caret position 100, if our caret position is currently
                    // 106
                    // we know that we must be on column 6 of line 5.
                    columnnum = caretpos - editArea.getLineStartOffset(linenum);

                    // We have to add one here because line numbers start at 0
                    // for getLineOfOffset and we want it to start at 1 for
                    // display.
                    linenum += 1;

                } catch (Exception ex) {
                    System.out.println(ex);
                }

                // JTA.setCaretPosition(JTA.getDocument().getLength());
                // Once we know the position of the line and the column, pass it
                // to a helper function for updating the status bar.
                updateStatus(linenum, columnnum);

            }

        }
        );

    }

    public void updateStatus(int linenumber, int columnnumber) {
        lineIndicatorStatus.setText("Line: " + linenumber + " Column: " + columnnumber + "                ");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }

}
