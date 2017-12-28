package BlasterNotepad;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.text.Element;

public class LineNumberingTextArea extends JTextArea {

    private JTextArea textArea;

    public LineNumberingTextArea(JTextArea textArea) {
        this.textArea = textArea;
        setBackground(textArea.getBackground());
        setEditable(true);
        setMargin(new Insets(3, 5, 0, 5));
        setWrapStyleWord(true);

    }

    public void updateLineNumbers() {
        String lineNumbersText = getLineNumbersText();
        setText(lineNumbersText);
    }

    private String getLineNumbersText() {
        int caretPosition = textArea.getDocument().getLength();
        Element root = textArea.getDocument().getDefaultRootElement();
        StringBuilder lineNumbersTextBuilder = new StringBuilder();
        lineNumbersTextBuilder.append("1").append(System.lineSeparator());
        for (int elementIndex = 2; elementIndex < root.getElementIndex(caretPosition) + 2; elementIndex++) {
            lineNumbersTextBuilder.append(elementIndex).append(System.lineSeparator());
        }

        return lineNumbersTextBuilder.toString();
    }
}
