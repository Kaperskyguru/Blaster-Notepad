package BlasterNotepad;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import kk.SimpleFileFilter;
import javax.swing.JList;
import javax.swing.text.BadLocationException;

public class BlasterMain extends JFrame implements ActionListener {

    public static JLabel errorStatus;
    public static JLabel fileExtensionStatus;
    public static JLabel lineIndicatorStatus;
    public static JLabel title, close, minimized, maximized;

    /**
     * Declaring all private, public and static variables here
     *
     */
    private final DefaultListModel listModel = new DefaultListModel();
    private ListCellRenderer renderer = new MyListCellRenderer();
    public UndoManager manager = new UndoManager();
    public JFileChooser jFileChooser1 = new JFileChooser();
    private final JTextArea JTA;
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
    JList<String> list;
    // Declaring all
    public JMenuItem undoItem, redoItem, cutItem, copyItem, pasteItem, black, undo, redo, cut, copy, delete, paste, select,
            showUnicode, insertUnicode, openIME, reconversion, aboutItem, helpItem, fontItem, backGround, foreGround, deleteItem,
            findItem, findnextItem, replaceItem, gotoItem, selectAllItem, timeDateItem, openFolder;
    public JCheckBoxMenuItem wordwrapItem, StatusBarItem, right, LineNumber;
    public JTextArea editArea;
    ImageIcon icons1 = new ImageIcon();
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
    JPopupMenu popmenu;

    public BlasterMain() {
        super("KpanshPad");

        icon = new IconsCode();
        saveButton = new JButton(icon.getSaveImageIcon());
        saveButton.setBackground(new Color(18, 30, 49));
        openButton = new JButton(icon.getOpenImageIcon());
        openButton.setBackground(new Color(18, 30, 49));
        closeButton = new JButton(icon.getCloseImageIcon());
        closeButton.setBackground(new Color(18, 30, 49));
        printButton = new JButton(icon.getSaveImageIcon());
        printButton.setBackground(new Color(18, 30, 49));
        saveAllButton = new JButton(icon.getSaveAlImageIcon());
        saveAllButton.setBackground(new Color(18, 30, 49));
        newButton = new JButton(icon.getNewImageIcon());
        newButton.setBackground(new Color(18, 30, 49));
        findButton = new JButton(icon.getFindImageIcon());
        findButton.setBackground(new Color(18, 30, 49));
        closeAllButton = new JButton(icon.getCloseAllImageIcon());
        closeAllButton.setBackground(new Color(18, 30, 49));
        cutButton = new JButton(icon.getCutImageIcon());
        cutButton.setBackground(new Color(18, 30, 49));
        copyButton = new JButton(icon.getCopyImageIcon());
        copyButton.setBackground(new Color(18, 30, 49));
        pasteButton = new JButton(icon.getPasteImageIcon());
        pasteButton.setBackground(new Color(18, 30, 49));
        undoButton = new JButton(icon.getUndoImageIcon());
        undoButton.setBackground(new Color(18, 30, 49));
        redoButton = new JButton(icon.getRedoImageIcon());
        redoButton.setBackground(new Color(18, 30, 49));
        rec = new JButton(icon.getOpenImageIcon());
        replaceButton = new JButton(icon.getEditFindReplaceImageIcon());
        replaceButton.setBackground(new Color(18, 30, 49));
        JTA = new JTextArea();
        Init();
        MouseEVENT mouseEVENT = new MouseEVENT();

    }

    private void Init() {

        //JTabbedPane tab = new JTabbedPane();
        // tab.setLayout(new BorderLayout());
        JPanel dashboard = new JPanel(new BorderLayout());
        north = new JPanel(new BorderLayout(300, 10));
        north.setPreferredSize(new Dimension(10, 40));
        //north.setBackground(new Color(18, 30, 48));
        JPanel east = new JPanel();
        east.setBackground(new Color(18, 30, 48));
        east.setPreferredSize(new Dimension(10, 479));
        west = new JPanel(new BorderLayout());
        west.setPreferredSize(new Dimension(200, 479));
        west.setBackground(new Color(18, 30, 48));
        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(10, 10));
        south.setBackground(new Color(18, 30, 48));
        JPanel closePane = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        closePane.setBackground(new Color(18, 30, 48));
        closePane.setForeground(new Color(0, 0, 0));

        minimized = new JLabel("-");
        minimized.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimized.setToolTipText("Minimized");
        minimized.setForeground(Color.WHITE);
        minimized.setFont(new Font("lucida console", Font.BOLD, 14));

        maximized = new JLabel("M");
        maximized.setToolTipText("Maximized");
        maximized.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maximized.setFont(new Font("lucida console", Font.BOLD, 14));
        maximized.setForeground(Color.WHITE);

        close = new JLabel("X");
        close.setToolTipText("Close");
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        close.setFont(new Font("lucida console", Font.BOLD, 14));
        close.setForeground(Color.WHITE);

        closePane.add(minimized);
        closePane.add(maximized);
        closePane.add(close);

        setLayout(new BorderLayout());

        list = new JList(listModel);
        icons1 = new ImageIcon(getClass().getResource("icons/document-close.png"));

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {

                open(null, (String)jFileChooser1.getCurrentDirectory().getAbsolutePath() + "\\" + list.getSelectedValue());
            }
        });

        list.setValueIsAdjusting(true);

        list.setCellRenderer(renderer);
        list.setFixedCellWidth(5);
        list.setFont(font);
        list.setFixedCellHeight(20);
        list.setForeground(Color.WHITE);
        list.setBackground(west.getBackground());

        final JScrollPane listScroll = new JScrollPane(list);
        //listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //listScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        west.add(listScroll, BorderLayout.CENTER);

        // add(north, BorderLayout.NORTH);
        add(east, BorderLayout.EAST);
        add(west, BorderLayout.WEST);
        //add(south, BorderLayout.SOUTH);

        // setUndecorated(true);
        // tab.add(new Dashboard(), BorderLayout.CENTER);
        setIconImage(new ImageIcon(imgURL).getImage());
        setSize(getX() + 1200, getY() + 700);

        JTA.setRequestFocusEnabled(true);
        JTA.setCaretColor(Color.BLACK);
        JTA.setMargin(new Insets(3, 5, 0, 5));
        JTA.setWrapStyleWord(true);
        JTA.setFont(font);
        JTA.setBackground(Color.WHITE);
        JTA.setForeground(Color.BLACK);
        add(dashboard, BorderLayout.CENTER);

        final JScrollPane Scroll = new JScrollPane(JTA);
        Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JToolBar ToolBar = new JToolBar();
        ToolBar.setBackground(new Color(18, 30, 49));
        ToolBar.setBorderPainted(true);
        ToolBar.setRollover(true);

        ToolBar.add(newButton);
        ToolBar.add(openButton);
        ToolBar.add(saveButton);
        ToolBar.add(saveAllButton);
        ToolBar.add(closeButton);
        ToolBar.add(closeAllButton);
        ToolBar.addSeparator();
        ToolBar.add(cutButton);
        ToolBar.add(copyButton);
        ToolBar.add(pasteButton);
        ToolBar.addSeparator();
        ToolBar.add(undoButton);
        ToolBar.add(redoButton);
        ToolBar.addSeparator();
        ToolBar.add(findButton);
        ToolBar.add(replaceButton);
        ToolBar.setFloatable(false);

        //cutButton.setEnabled(false);
        //copyButton.setEnabled(false);
        //findButton.setEnabled(false);
//Adding tooltip to toolBar Buttons
        newButton.setToolTipText("New");
        openButton.setToolTipText("Open");
        saveButton.setToolTipText("Save");
        saveAllButton.setToolTipText("Save As");
        closeButton.setToolTipText("Close");
        closeAllButton.setToolTipText("Close All");
        cutButton.setToolTipText("Cut");
        copyButton.setToolTipText("Copy");
        pasteButton.setToolTipText("Paste");
        undoButton.setToolTipText("Undo");
        redoButton.setToolTipText("Redo");
        findButton.setToolTipText("Find");
        replaceButton.setToolTipText("Replace");

// Adding toolbar to Frame
        add(ToolBar, BorderLayout.NORTH);

// Creating Status bar
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
        dashboard.add(Scroll, BorderLayout.CENTER);

        // Declaring menu Bar
        JMenuBar JMB = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu formatMenu = new JMenu("Format");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");

        // fileMenu.setOpaque(true);
        fileMenu.setForeground(new Color(18, 30, 48));
        // fileMenu.setBackground(new Color(18, 30, 48));

        // editMenu.setOpaque(true);
        editMenu.setForeground(new Color(18, 30, 48));
        // editMenu.setBackground(new Color(18, 30, 48));

        //  formatMenu.setOpaque(true);
        formatMenu.setForeground(new Color(18, 30, 48));
        // formatMenu.setBackground(new Color(18, 30, 48));

        //  viewMenu.setOpaque(true);
        viewMenu.setForeground(new Color(18, 30, 48));
        //  viewMenu.setBackground(new Color(18, 30, 48));

        //  helpMenu.setOpaque(true);
        helpMenu.setForeground(new Color(18, 30, 48));
        //  helpMenu.setBackground(new Color(18, 30, 48));

// declaring the menu item for file menu
        newItem = new JMenuItem("New", icon.getNewImageIcon());
        saveItem = new JMenuItem("Save", icon.getSaveImageIcon());
        exitItem = new JMenuItem("Exit", icon.getExitImageIcon());
        openItem = new JMenuItem("Open", icon.getOpenImageIcon());
        saveAsItem = new JMenuItem("Save As", icon.getSaveAlImageIcon());
        pageItem = new JMenuItem("Page Setup", icon.getOpenImageIcon());
        printItem = new JMenuItem("Print", icon.getprintImageIcon());
        openFolder = new JMenuItem("Open Folder");
        JMenu ThemeItem = new JMenu("Themes");

// Setting Alphabet for ShortCut Keys
        newItem.setMnemonic('N');
        saveItem.setMnemonic('S');
        openItem.setMnemonic('O');
        openFolder.setMnemonic('O');
        printItem.setMnemonic('P');
        exitItem.setMnemonic('E');

// Adding Shortcut Keys to Menu
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        printItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                ActionEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));
        openFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.SHIFT_DOWN_MASK, ActionEvent.SHIFT_MASK));

        // Adding menu items to file menu
        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(openItem);
        fileMenu.add(openFolder);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(pageItem);
        fileMenu.add(printItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        // Declaring menu items for Edit menu

        undoItem = new JMenuItem("Undo", icon.getUndoImageIcon());
        redoItem = new JMenuItem("Redo", icon.getRedoImageIcon());
        cutItem = new JMenuItem("Cut", icon.getCutImageIcon());
        copyItem = new JMenuItem("Copy", icon.getCopyImageIcon());
        pasteItem = new JMenuItem("Paste", icon.getPasteImageIcon());
        deleteItem = new JMenuItem("Delete", icon.getDeleteImageIcon());
        findItem = new JMenuItem("Find", icon.getFindImageIcon());
        findnextItem = new JMenuItem("Find Next", icon.getFindImageIcon());
        replaceItem = new JMenuItem("Replace", icon.getEditFindReplaceImageIcon());
        gotoItem = new JMenuItem("Goto");
        selectAllItem = new JMenuItem("Select All");
        timeDateItem = new JMenuItem("Time/Date");

        undoItem.setMnemonic('Z');
        redoItem.setMnemonic('Y');
        cutItem.setMnemonic('X');
        copyItem.setMnemonic('C');
        pasteItem.setMnemonic('V');
        findItem.setMnemonic('F');
        findnextItem.setMnemonic('F');
        replaceItem.setMnemonic('H');
        gotoItem.setMnemonic('G');
        selectAllItem.setMnemonic('A');

        selectAllItem.setEnabled(false);
        findnextItem.setEnabled(false);
        findItem.setEnabled(false);
        pasteItem.setEnabled(true);
        cutItem.setEnabled(false);
        undoItem.setEnabled(false);
        redoItem.setEnabled(false);

        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                ActionEvent.CTRL_MASK));
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                ActionEvent.CTRL_MASK));
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                ActionEvent.CTRL_MASK));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                ActionEvent.CTRL_MASK));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                ActionEvent.CTRL_MASK));
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
                ActionEvent.SHIFT_MASK));
        findItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                ActionEvent.CTRL_MASK));
        findnextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                ActionEvent.SHIFT_MASK));
        replaceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
                ActionEvent.CTRL_MASK));
        gotoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
                ActionEvent.CTRL_MASK));
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                ActionEvent.CTRL_MASK));

        // Adding menu items to Edit menu
        editMenu.add(undoItem);
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.addSeparator();
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(deleteItem);

        editMenu.addSeparator();

        editMenu.add(findItem);
        editMenu.add(findnextItem);
        editMenu.add(replaceItem);
        editMenu.add(gotoItem);
        editMenu.addSeparator();
        editMenu.add(selectAllItem);
        editMenu.add(timeDateItem);

        // Declaring the format menuItem
        wordwrapItem = new JCheckBoxMenuItem("Word Wrap", icon.getWord_wrapImageIcon());
        fontItem = new JMenuItem("Font...", icon.getFormatImageIcon());
        backGround = new JMenuItem("Background");
        foreGround = new JMenuItem("Foreground");

        formatMenu.add(wordwrapItem);
        formatMenu.add(fontItem);
        formatMenu.addSeparator();
        formatMenu.add(backGround);
        formatMenu.add(foreGround);
        formatMenu.add(ThemeItem);

        // Create different themes menu
        black = new JMenuItem("KPANSH- Black");
        JMenuItem blue = new JMenuItem("KPANSH- Blue");
        JMenuItem green = new JMenuItem("KPANSH- Green");
        JMenuItem white = new JMenuItem("KPANSH- White");
        JMenuItem red = new JMenuItem("KPANSH- Red");
        JMenuItem orange = new JMenuItem("KPANSH- Orange");
        JMenuItem yellow = new JMenuItem("KPANSH- Yellow");

        ThemeItem.add(black);
        ThemeItem.add(blue);
        ThemeItem.add(green);
        ThemeItem.add(white);
        ThemeItem.add(red);
        ThemeItem.add(orange);
        ThemeItem.add(yellow);

        StatusBarItem = new JCheckBoxMenuItem("Status Bar");
        LineNumber = new JCheckBoxMenuItem("Line Number");
        StatusBarItem.setSelected(true);
        LineNumber.setSelected(true);
        viewMenu.add(StatusBarItem);
        viewMenu.addSeparator();
        viewMenu.add(LineNumber);

        aboutItem = new JMenuItem("About KPANSH PAD");
        helpItem = new JMenuItem("View Help", icon.getHelpImageIcon());

        helpMenu.add(helpItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutItem);

        // Adding menu item to man menu position
        JMB.add(fileMenu);
        JMB.add(editMenu);
        JMB.add(formatMenu);
        JMB.add(viewMenu);
        JMB.add(helpMenu);
        helpMenu.setBorderPainted(false);

        // Adding menu item to main menu and setting to frame
        north.setBorder(null);
        setJMenuBar(JMB);//, BorderLayout.WEST);
        title = new JLabel("KpanshPad");
        title.setForeground(Color.WHITE);
        //north.add(title, BorderLayout.CENTER);
        // north.add(closePane, BorderLayout.EAST);

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
        black.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        right.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        delete.addActionListener(this);
        select.addActionListener(this);
        openFolder.addActionListener(this);
        undoButton.addActionListener(this);
        redoButton.addActionListener(this);
        findButton.addActionListener(this);
        saveButton.addActionListener(this);
        openButton.addActionListener(this);
        closeButton.addActionListener(this);
        printButton.addActionListener(this);
        saveAllButton.addActionListener(this);
        newButton.addActionListener(this);
        closeAllButton.addActionListener(this);
        cutButton.addActionListener(this);
        copyButton.addActionListener(this);
        pasteButton.addActionListener(this);
        rec.addActionListener(this);
        replaceButton.addActionListener(this);

        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        deleteItem.addActionListener(this);
        selectAllItem.addActionListener(this);
        gotoItem.addActionListener(this);
        timeDateItem.addActionListener(this);
        replaceItem.addActionListener(this);
        newItem.addActionListener(this);
        openItem.addActionListener(this);
        exitItem.addActionListener(this);
        foreGround.addActionListener(this);
        backGround.addActionListener(this);
        fontItem.addActionListener(this);
        wordwrapItem.addActionListener(this);
        helpItem.addActionListener(this);
        aboutItem.addActionListener(this);
        findnextItem.addActionListener(this);
        findItem.addActionListener(this);
        helpItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        saveItem.addActionListener(this);
        printItem.addActionListener(this);

        final LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(JTA);
        lineNumberingTextArea.setFont(font);
        Scroll.setRowHeaderView(lineNumberingTextArea);

        LineNumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (LineNumber.isSelected()) {
                    Scroll.setRowHeaderView(lineNumberingTextArea);

                } else {
                    Scroll.setRowHeaderView(null);

                }

            }

        });

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {
                JTA.requestFocusInWindow();

            }

            @Override
            public void windowClosing(WindowEvent e) {
                new EventClass(JTA).confirmClosing();
            }

            @Override
            public void windowActivated(WindowEvent e) {
                JTA.requestFocusInWindow();
            }

        });

        JTA.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                lineNumberingTextArea.updateLineNumbers();
                checkEnabledbtn();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                lineNumberingTextArea.updateLineNumbers();
                checkEnabledbtn();
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
                        revalidate();
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
                checkEnabled();
                updatebtn();

            }

            @Override
            public void focusLost(FocusEvent e) {
                textselect = JTA.getSelectedText();
                isSelect = textselect != null;
                checkEnabled();
                updatebtn();
            }
        });

        JTA.addCaretListener(new CaretListener() {
            // Each time the caret is moved, it will trigger the listener and
            // its method caretUpdate.
            // It will then pass the event to the update method including the
            // source of the event (which is our textArea control)

            @Override
            public void caretUpdate(CaretEvent e) {
                checkEnabledbtn();

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

                } catch (BadLocationException ex) {
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

    public void checkEnabledbtn() {
        if (!(JTA.getText().isEmpty())) {
            findButton.setEnabled(true);

        } else {
            findButton.setEnabled(false);
        }

    }

    public void updatebtn() {
        if ((isSelect)) {
            copyButton.setEnabled(true);
            cutButton.setEnabled(true);
        } else {
            copyButton.setEnabled(false);
            cutButton.setEnabled(false);
        }
    }

    public void updateStatus(int linenumber, int columnnumber) {
        lineIndicatorStatus.setText("Line: " + linenumber + " Column: " + columnnumber + "                ");
    }

    public void checkEnabled() {
        try {
            textselect = JTA.getSelectedText();
            if (isSelect && !(textselect.isEmpty())) {
                //pasteItem.setEnabled(isSelect);
                cutItem.setEnabled(isSelect);
                deleteItem.setEnabled(isSelect);
                copyItem.setEnabled(isSelect);
                //copyButton.setEnabled(isSelect);
                //cutButton.setEnabled(isSelect);
                delete.setEnabled(true);
                copy.setEnabled(true);
                cut.setEnabled(true);
            } else {
                // pasteItem.setEnabled(isSelect);
                cutItem.setEnabled(isSelect);
                deleteItem.setEnabled(isSelect);
                copyItem.setEnabled(isSelect);
                //copyButton.setEnabled(isSelect);
                // cutButton.setEnabled(isSelect);
                delete.setEnabled(false);
                copy.setEnabled(false);
                cut.setEnabled(false);
            }

            if (!(JTA.getText().isEmpty())) {
                findButton.setEnabled(true);
                findItem.setEnabled(true);
                findnextItem.setEnabled(true);
                selectAllItem.setEnabled(true);
                //saveItem.setEnabled(true);
                undoItem.setEnabled(true);
                redoItem.setEnabled(true);
            } else {
                findButton.setEnabled(false);
                findItem.setEnabled(false);
                findnextItem.setEnabled(false);
                selectAllItem.setEnabled(false);
                //saveItem.setEnabled(false);
                undoItem.setEnabled(false);
                redoItem.setEnabled(false);
            }
        } catch (NullPointerException e) {
            System.out.flush();
        }
    }

    public void setStatusErr(String error) {
        errorStatus.setText(error);

    }

//    public String getFile() {
//        return lastpath;
//    }
//
//    public void setFile(String file) {
//        lastpath = file;
//
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == undoButton || e.getSource() == undo || e.getSource() == undoItem) {
            try {
                manager.undo();
            } catch (CannotUndoException err) {
                System.err.flush();
            }

        } else if (e.getSource() == black) {
            JTA.setBackground(new Color(18, 30, 49));
            JTA.setForeground(Color.WHITE);
            JTA.setCaretColor(Color.WHITE);
        } else if (e.getSource() == redo || e.getSource() == redoButton || e.getSource() == redoItem) {
            try {
                manager.redo();
            } catch (CannotRedoException err) {
                System.err.flush();

            }

        } else if (e.getSource() == right) {

        } else if (e.getSource() == cut || e.getSource() == cutButton || e.getSource() == cutItem) {
            JTA.cut();
        } else if (e.getSource() == copy || e.getSource() == copyButton || e.getSource() == copyItem) {
            JTA.copy();
        } else if (e.getSource() == saveButton || e.getSource() == saveAllButton || e.getSource() == saveItem) {
            save();
        } else if (e.getSource() == openItem || e.getSource() == openButton) {

            confirm();

        } else if (e.getSource() == closeButton || e.getSource() == closeAllButton) {
            new EventClass(JTA).confirmClosing();

        } else if (e.getSource() == printButton || e.getSource() == printItem) {
            try {
                JTA.print();
            } catch (PrinterException err) {
                String er = "Printer not Found";
                setStatusErr(er);
            }

        } else if (e.getSource() == findItem || e.getSource() == findButton) {
            FindDialog findDialog = new FindDialog(JTA);
            new EventClass(JTA).syntaxHighlighter();

        } else if (e.getSource() == newButton || e.getSource() == newItem) {
            JTA.setText("");
            isNewFile = true;

        } else if (e.getSource() == paste || e.getSource() == pasteItem || e.getSource() == pasteButton) {
            JTA.paste();

        } else if (e.getSource() == rec) {

        } else if (e.getSource() == replaceItem || e.getSource() == replaceButton) {

        } else if (e.getSource() == delete || e.getSource() == deleteItem) {
            JTA.removeAll();
        } else if (e.getSource() == select || e.getSource() == selectAllItem) {
            JTA.selectAll();
            isSelect = true;
        } else if (e.getSource() == StatusBarItem) {

            if (StatusBarItem.isSelected()) {
                statusBar.setEnabled(true);
                statusBar.setVisible(true);
            } else {
                statusBar.setEnabled(false);
                statusBar.setVisible(false);
            }

        } else if (e.getSource() == findnextItem) {
            if (bool && !textfield.equals("")) {
                new FindDialog(JTA).search();
            } else {
                FindDialog findDialog = new FindDialog(JTA);

            }

        } else if (e.getSource() == saveAsItem) {
            saveAs();
        } else if (e.getSource() == exitItem) {
            new EventClass(JTA).confirmClosing();
        } else if (e.getSource() == fontItem) {
            JFontChooser font1 = new JFontChooser();
            font1.setVisible(true);
            JTA.setFont(font1.getFont());
        } else if (e.getSource() == timeDateItem) {
            new EventClass(JTA).getTimeDate();
            new EventClass(JTA).syntaxHighlighter();
        } else if (e.getSource() == aboutItem) {
            new EventClass(JTA).getAboutUs();
        } else if (e.getSource() == backGround) {
            new EventClass(JTA).getBackGroundColor();
        } else if (e.getSource() == foreGround) {
            new EventClass(JTA).getForegroundColor();
        } else if (e.getSource() == gotoItem) {
            new EventClass(JTA).getGotoEvent();
        } else if (e.getSource() == openFolder) {
            openFolder();
        }

    }

    public void open() {
        // jFileChooser1.setMultiSelectionEnabled(true);
        jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            String v = jFileChooser1.getSelectedFile().getName();
            // v = ;
            if (list.getSelectedIndex() == -1) {
                listModel.addElement( v);
            } else {
                listModel.addElement(new Object[]{icons1, v});
            }
            open(jFileChooser1.getSelectedFile(), "");

        }
    }

    private void open(File file, String file2) {
        try {
            if (file != null && file2.equals("")) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    byte[] b = new byte[in.available()];
                    in.read(b, 0, b.length);
                    JTA.setText(new String(b, 0, b.length));
                }
                setTitle(file.getAbsolutePath() + " - Kpashpad");

            } else if (!file2.equals("") && file == null) {

                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file2))) {
                    byte[] b = new byte[in.available()];
                    in.read(b, 0, b.length);
                    JTA.setText(new String(b, 0, b.length));
                }
                setTitle(file2 + " - Kpashpad");
            }

        } catch (IOException e) {
            new BlasterMain().setStatusErr("Error Opening file ");
        }

    }

    public void confirm() {
        Object object[] = {"Save", "Don't Save", "Cancel"};
        int s = JOptionPane.NO_OPTION;
        if (isNewFile && !(JTA.getText().equals("".trim()))) {
            s = JOptionPane.showOptionDialog(null, "Do you want to save this file?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, object, object[0]);
        }
        if (s == JOptionPane.YES_OPTION) {
            new EventClass(JTA).saveAs();
        } else if (s == JOptionPane.NO_OPTION) {
            open();
        }

    }

    public void openFolder() {
        jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            String lis[] = null;

            for (int i = 0; i < list.getModel().getSize(); i++) {
                lis[i] = list.getModel().getElementAt(i);
            }

            list.setListData(jFileChooser1.getCurrentDirectory().list());

        }

    }

    public String saveAs() {
        Object object[] = {"Save", "Don't Save", "Cancel"};
        int s;

        String[] bash = new String[]{"bat", "cmd", "nt"};
        jFileChooser1.addChoosableFileFilter(new SimpleFileFilter(bash, "Bash ( (*.bat, *.cmd, *.nt)"));

        if (jFileChooser1.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (jFileChooser1.getSelectedFile().exists()) {
                s = JOptionPane.showOptionDialog(null, jFileChooser1.getSelectedFile().getName()
                        + " Already Exits \n" + "Do you want to replace this file?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, object, object[0]);
                if (s == JOptionPane.YES_OPTION) {
                    save1(jFileChooser1.getSelectedFile());
                    isNewFile = false;
                    lastpath = jFileChooser1.getSelectedFile();
                    setTitle(jFileChooser1.getSelectedFile().getAbsolutePath() + " - Kpanshpad");
                    return jFileChooser1.getSelectedFile().getPath();
                } else {
                    save();
                }
            } else {
                save1(jFileChooser1.getSelectedFile());
                isNewFile = false;
                lastpath = jFileChooser1.getSelectedFile();
                setTitle(jFileChooser1.getSelectedFile().getAbsolutePath() + " - Kpanshpad");
                return jFileChooser1.getSelectedFile().getPath();
            }
            lastpath = jFileChooser1.getSelectedFile();
        } else {

            String string = "Error Saving your File";
            setStatusErr(string);
            return null;
        }

        return jFileChooser1.getSelectedFile().getPath();

    }

    private void save1(File file1) {
        try {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file1))) {
                byte[] b = (JTA.getText()).getBytes();
                out.write(b, 0, b.length);
            }

            if (hasListener) {
                JTA.getDocument().removeDocumentListener(autocomplete);
                hasListener = false;
            }
            autoComplete(file1);

            setTitle(file1.getAbsolutePath() + " - Kpashpad");
        } catch (IOException e) {
            new BlasterMain().setStatusErr("Error Saving " + file1.getName());
        }
        isNewFile = false;

    }

    public boolean save() {

        if (isNewFile) {
            saveAs();
            return true;
        } else {
            save1(lastpath.getAbsoluteFile());
        }
        return false;
    }

    protected JTextArea getEditor() {
        return JTA;
    }

    public void autoComplete(File file) {

        //With the keywords located in a separate class,
        //we can support multiple languages and not have to do
        //much to add new ones.
        SupportedKeywords kw = new SupportedKeywords();
        ArrayList<String> arrayList;
        ArrayList list = new ArrayList(); //{".java", ".cpp", ".cs"};
        list.add(".java");
        list.add(".cpp");
        list.add(".cs");
        for (Object list1 : list) {
            if (file.getName().endsWith((String) list1)) {
                String[] jk = kw.getAnyKeywords(list1.toString());
                arrayList = kw.setKeywords(jk);
                autocomplete = new AutoComplete(this, arrayList);
                JTA.getDocument().addDocumentListener(autocomplete);
                hasListener = true;
                fileExtensionStatus.setText(list1.toString().substring(1, list1.toString().length()) + " File");

            }
        }

    }

    final class MouseEVENT {

        public MouseEVENT() {
            MouseEVENT();
        }

        public void MouseEVENT() {
            wordwrapItem.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (wordwrapItem.isSelected()) {
                        JTA.setWrapStyleWord(true);
                    } else {
                        JTA.setWrapStyleWord(false);
                    }

                }
            });

            north.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    mousePressed = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    mousePressed = false;
                }
            });

            north.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (mousePressed = true) {
                        int x = e.getXOnScreen();
                        int y = e.getYOnScreen();
                        setLocation(x - mouseX, y - mouseY);
                    }
                }

            });

            right.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (right.isSelected()) {
                        JTA.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    } else {
                        JTA.setComponentOrientation(ComponentOrientation.UNKNOWN);

                    }
                }
            });

            minimized.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    setExtendedState(JFrame.ICONIFIED);
                }
            });

            close.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    new EventClass(JTA).confirmClosing();

                }
            });

            maximized.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                        setExtendedState(JFrame.NORMAL);
                    } else {
                        setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                }
            });

        }
    }
}
