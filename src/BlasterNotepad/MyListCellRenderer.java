package BlasterNotepad;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

/**
 *
 * @author kapersky
 */
public class MyListCellRenderer implements ListCellRenderer {
    
    private final JLabel lblCell = new JLabel(" ", JLabel.LEFT);
    private final Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    private final Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
    
    @Override
    public Component getListCellRendererComponent(JList jlist, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        Object[] pair = (Object[]) value;
        lblCell.setOpaque(true);
        lblCell.setIcon((ImageIcon)pair[0]);
        lblCell.setText(pair[1].toString());
        
        if (isSelected) {
            lblCell.setForeground(jlist.getSelectionForeground());
            lblCell.setBackground(jlist.getSelectionBackground());
        } else {
            lblCell.setForeground(jlist.getForeground());
            lblCell.setBackground(jlist.getBackground());
        }
        lblCell.setBorder(cellHasFocus ? lineBorder : emptyBorder);
        
        return jlist;
    }
    
}
