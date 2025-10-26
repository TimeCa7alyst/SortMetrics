package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SelectView extends JPanel {
    private JPanel mainPanel;


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) (screenSize.width * 0.3);
    int height = (int) (screenSize.height * 0.2);

    public SelectView() {
        initComponents();
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void initComponents() {
	// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    continuarButton = new JButton();
    sairButton = new JButton();
    label1 = new JLabel();
    dropDown1 = new JComboBox();

    //======== this ========
    setVisible(true);
    setBorder(new TitledBorder(new EtchedBorder(), ""));
    setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
    javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax
    . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
    . awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
    . Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .
    PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .
    equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

    //---- continuarButton ----
    continuarButton.setAutoscrolls(false);
    continuarButton.setBackground(Color.white);
    continuarButton.setBorderPainted(true);
    continuarButton.setContentAreaFilled(true);
    continuarButton.setDoubleBuffered(true);
    continuarButton.setName("");
    continuarButton.setSelected(false);
    continuarButton.setText("Continuar");
    continuarButton.setVerticalAlignment(SwingConstants.CENTER);

    //---- sairButton ----
    sairButton.setAutoscrolls(false);
    sairButton.setBackground(Color.white);
    sairButton.setBorderPainted(true);
    sairButton.setContentAreaFilled(true);
    sairButton.setDoubleBuffered(true);
    sairButton.setName("");
    sairButton.setSelected(false);
    sairButton.setText("Sair");
    sairButton.setVerticalAlignment(SwingConstants.CENTER);

    //---- label1 ----
    label1.setText("Escolha um algoritmo de ordena\u00e7\u00e3o");

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(label1)
                .addGap(114, 114, 114)
                .addComponent(dropDown1, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(continuarButton)
                .addGap(268, 268, 268)
                .addComponent(sairButton))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(label1))
                    .addComponent(dropDown1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup()
                    .addComponent(continuarButton)
                    .addComponent(sairButton)))
    );
	// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    private JButton continuarButton;
    private JButton sairButton;
    private JLabel label1;
    private JComboBox dropDown1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
