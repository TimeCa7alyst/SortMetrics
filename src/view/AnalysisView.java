package view;

import javax.swing.*;
import java.awt.*;

public class AnalysisView {
    private JPanel mainPanel;
    private JTextArea textArea1;
    private JButton continuarButton2;
    private JButton sairButton4;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) (screenSize.width * 0.4);
    int height = (int) (screenSize.height * 0.9);

    public AnalysisView() {
        textArea1.setEditable(false);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JButton getContinuarButton2() {
        return continuarButton2;
    }

    public JButton getSairButton4() {
        return sairButton4;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
