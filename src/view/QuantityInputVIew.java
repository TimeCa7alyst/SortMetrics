package view;

import java.awt.*;
import javax.swing.*;

public class QuantityInputVIew extends JPanel {
    private JPanel mainPanel;

    public QuantityInputVIew() {
        initComponents();
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

    private void initComponents() {
	// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    digiteQuantidadeLabel = new JLabel();
    textField1 = new JTextField();
    analisarButton = new JButton();
    sairButton2 = new JButton();

    //======== this ========
    setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
    swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border
    .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067"
    ,java.awt.Font.BOLD,12),java.awt.Color.red), getBorder
    ())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
    .beans.PropertyChangeEvent e){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException
    ();}});

    //---- digiteQuantidadeLabel ----
    digiteQuantidadeLabel.setOpaque(false);

    //---- analisarButton ----
    analisarButton.setAutoscrolls(false);
    analisarButton.setBackground(Color.white);
    analisarButton.setBorderPainted(true);
    analisarButton.setContentAreaFilled(true);
    analisarButton.setDoubleBuffered(true);
    analisarButton.setName("");
    analisarButton.setSelected(false);
    analisarButton.setText("Analisar");
    analisarButton.setVerticalAlignment(SwingConstants.CENTER);

    //---- sairButton2 ----
    sairButton2.setAutoscrolls(false);
    sairButton2.setBackground(Color.white);
    sairButton2.setBorderPainted(true);
    sairButton2.setContentAreaFilled(true);
    sairButton2.setDoubleBuffered(true);
    sairButton2.setName("");
    sairButton2.setSelected(false);
    sairButton2.setText("Sair");
    sairButton2.setVerticalAlignment(SwingConstants.CENTER);

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE))
            .addComponent(digiteQuantidadeLabel, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(analisarButton)
                .addGap(180, 180, 180)
                .addComponent(sairButton2))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(digiteQuantidadeLabel, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup()
                    .addComponent(analisarButton)
                    .addComponent(sairButton2)))
    );
	// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    private JLabel digiteQuantidadeLabel;
    private JTextField textField1;
    private JButton analisarButton;
    private JButton sairButton2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
