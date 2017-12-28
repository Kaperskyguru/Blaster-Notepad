package BlasterNotepad;

import java.net.URL;
import javax.swing.ImageIcon;

public class IconsCode {

   private final ImageIcon openImageIcon, newImageIcon, cutImageIcon, exitImageIcon, formatImageIcon, fontImageIcon, copyImageIcon, saveImageIcon;
    private final ImageIcon redoImageIcon, saveAllImageIcon, editFindReplaceImageIcon, word_wrapImageIcon, printImageIcon, editFindImageIcon, closeAllImageIcon, deleteImageIcon, helpImageIcon, undoImageIcon, findImageIcon, pasteImageIcon, closeImageIcon;

    public IconsCode() {

 // imgURL = getClass().getResource("icons/i.png");
        saveImageIcon = new ImageIcon(getClass().getResource("icons/document-save.png"));
        saveAllImageIcon = new ImageIcon(getClass().getResource("icons/document-save-all.png"));
        openImageIcon = new ImageIcon(getClass().getResource("icons/document-open.png"));
        newImageIcon = new ImageIcon(getClass().getResource("icons/document-new.png"));
        cutImageIcon = new ImageIcon(getClass().getResource("icons/edit-cut.png"));
        exitImageIcon = new ImageIcon(getClass().getResource("icons/exit.png"));
        formatImageIcon = new ImageIcon(getClass().getResource("icons/format.png"));
        fontImageIcon = new ImageIcon(getClass().getResource("icons/font1.png"));
        copyImageIcon = new ImageIcon(getClass().getResource("icons/edit-copy.png"));
        redoImageIcon = new ImageIcon(getClass().getResource("icons/edit-redo.png"));
        deleteImageIcon = new ImageIcon(getClass().getResource("icons/delete.png"));
        helpImageIcon = new ImageIcon(getClass().getResource("icons/help.png"));
        undoImageIcon = new ImageIcon(getClass().getResource("icons/edit-undo.png"));
        findImageIcon = new ImageIcon(getClass().getResource("icons/find.png"));
        pasteImageIcon = new ImageIcon(getClass().getResource("icons/edit-paste.png"));
        closeImageIcon = new ImageIcon(getClass().getResource("icons/document-close.png"));
        closeAllImageIcon = new ImageIcon(getClass().getResource("icons/document-close-all.png"));
        printImageIcon = new ImageIcon(getClass().getResource("icons/document-print.png"));
        word_wrapImageIcon = new ImageIcon(getClass().getResource("icons/word-wrap.png"));
        editFindReplaceImageIcon = new ImageIcon(getClass().getResource("icons/edit-find-replace.png"));
        editFindImageIcon = new ImageIcon(getClass().getResource("icons/edit-find.png"));
        // word_wrapImageIcon = new ImageIcon(getClass().getResource("icons/word-wrap.png"));

//    public URL getAppLogo() {
//        return imgURL;
//
//    }
    }

    public ImageIcon getCloseAllImageIcon() {
        return closeAllImageIcon;

    }

    public ImageIcon getEditFindReplaceImageIcon() {
        return editFindReplaceImageIcon;

    }

    public ImageIcon getEditFindImageIcon() {
        return editFindImageIcon;

    }

    public ImageIcon getCloseImageIcon() {
        return closeImageIcon;

    }

    public ImageIcon getWord_wrapImageIcon() {
        return word_wrapImageIcon;

    }

    public ImageIcon getSaveAlImageIcon() {
        return saveAllImageIcon;

    }

    public ImageIcon getOpenImageIcon() {
        return openImageIcon;

    }

    public ImageIcon getprintImageIcon() {
        return printImageIcon;

    }

    public ImageIcon getCutImageIcon() {
        return cutImageIcon;

    }

    public ImageIcon getSaveImageIcon() {
        return saveImageIcon;

    }

    public ImageIcon getExitImageIcon() {
        return exitImageIcon;

    }

    public ImageIcon getCloseImageIcon1() {
        return closeImageIcon;

    }

    public ImageIcon getFindImageIcon() {
        return editFindImageIcon;

    }

    public ImageIcon getPasteImageIcon() {
        return pasteImageIcon;

    }

    public ImageIcon getDeleteImageIcon() {
        return deleteImageIcon;

    }

    public ImageIcon getHelpImageIcon() {
        return helpImageIcon;

    }

    public ImageIcon getUndoImageIcon() {
        return undoImageIcon;

    }

    public ImageIcon getRedoImageIcon() {
        return redoImageIcon;

    }

    public ImageIcon getCopyImageIcon() {
        return copyImageIcon;

    }

    public ImageIcon getFontImageIcon() {
        return fontImageIcon;

    }

    public ImageIcon getFormatImageIcon() {
        return formatImageIcon;

    }

    public ImageIcon getNewImageIcon() {
        return newImageIcon;

    }
}
