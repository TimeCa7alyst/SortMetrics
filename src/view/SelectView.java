package view;

import javax.swing.*;

public class SelectView {
    private JPanel mainPanel;
    private JButton continuarButton;
    private JComboBox dropDown1;
    private JLabel label1;
    private JButton sairButton;

    public SelectView() {
        dropDown1.addItem("Bubble Sort");
        dropDown1.addItem("Selection Sort");
        dropDown1.addItem("Insertion Sort");
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getContinuarButton() {
        return continuarButton;
    }

    public JComboBox getDropDown1() {
        return dropDown1;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public JButton getSairButton() {
        return sairButton;
    }
}
