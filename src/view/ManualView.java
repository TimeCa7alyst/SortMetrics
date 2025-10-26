package view;

import java.awt.*;
import javax.swing.*;

public class ManualView extends JPanel {
    private JPanel mainPanel;

    public ManualView() {
        initComponents();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JButton getAnalisarButton2() {
        return analisarButton2;
    }

    public JButton getSairButton3() {
        return sairButton3;
    }

    public JLabel getLabelTitulo() {
        return labelTitulo;
    }

    private void initComponents() {
	// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    labelTitulo = new JLabel();
    textField1 = new JTextField();
    analisarButton2 = new JButton();
    sairButton3 = new JButton();

    //======== this ========
    setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
    0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
    . BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
    red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
    beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

    //---- analisarButton2 ----
    analisarButton2.setAutoscrolls(false);
    analisarButton2.setBackground(Color.white);
    analisarButton2.setBorderPainted(true);
    analisarButton2.setContentAreaFilled(true);
    analisarButton2.setDoubleBuffered(true);
    analisarButton2.setName("");
    analisarButton2.setSelected(false);
    analisarButton2.setText("Analisar");
    analisarButton2.setVerticalAlignment(SwingConstants.CENTER);

    //---- sairButton3 ----
    sairButton3.setAutoscrolls(false);
    sairButton3.setBackground(Color.white);
    sairButton3.setBorderPainted(true);
    sairButton3.setContentAreaFilled(true);
    sairButton3.setDoubleBuffered(true);
    sairButton3.setName("");
    sairButton3.setSelected(false);
    sairButton3.setText("Sair");
    sairButton3.setVerticalAlignment(SwingConstants.CENTER);

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE))
            .addComponent(labelTitulo, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(analisarButton2)
                .addGap(166, 166, 166)
                .addComponent(sairButton3))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(labelTitulo, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup()
                    .addComponent(analisarButton2)
                    .addComponent(sairButton3)))
    );
	// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    private JLabel labelTitulo;
    private JTextField textField1;
    private JButton analisarButton2;
    private JButton sairButton3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
