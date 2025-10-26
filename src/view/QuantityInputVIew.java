package view;

import javax.swing.*;

public class QuantityInputVIew {
    private JPanel mainPanel;
    private JTextField textField1;
    private JLabel digiteQuantidadeLabel;
    private JButton analisarButton;
    private JButton sairButton2;

    public QuantityInputVIew() {
        String digiteQuantidade = "Digite a quantidade de números<br>" +
                "para serem ordenados";
        digiteQuantidadeLabel.setText("<html>" + digiteQuantidade + "</html>");

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JLabel getDigiteQuantidadeLabel() {
        return digiteQuantidadeLabel;
    }

    public JButton getAnalisarButton() {
        return analisarButton;
    }

    public JButton getSairButton2() {
        return sairButton2;
    }
}
