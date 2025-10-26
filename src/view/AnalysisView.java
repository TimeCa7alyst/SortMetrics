package view;

import javax.swing.*;
import java.awt.*;

public class AnalysisView extends JPanel {
    private JPanel mainPanel;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) (screenSize.width * 0.4);
    int height = (int) (screenSize.height * 0.9);

    public AnalysisView() {
        initComponents();
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

    private void initComponents() {
	// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    var scrollPane1 = new JScrollPane();
    textArea1 = new JTextArea();
    var label1 = new JLabel();
    var label2 = new JLabel();
    continuarButton2 = new JButton();
    sairButton4 = new JButton();

    //======== this ========
    setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
    swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border
    . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg"
    ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder
    ( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
    .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException
    ( ); }} );

    //======== scrollPane1 ========
    {

        //---- textArea1 ----
        textArea1.setColumns(0);
        textArea1.setEditable(false);
        textArea1.setEnabled(true);
        textArea1.setFocusable(true);
        textArea1.setLineWrap(true);
        textArea1.putClientProperty("html.disable", true);
        scrollPane1.setViewportView(textArea1);
    }

    //---- label1 ----
    label1.setText("An\u00e1lise do algoritmo");

    //---- label2 ----
    label2.setText("Deseja continuar?");

    //---- continuarButton2 ----
    continuarButton2.setAutoscrolls(false);
    continuarButton2.setBackground(Color.white);
    continuarButton2.setBorderPainted(true);
    continuarButton2.setContentAreaFilled(true);
    continuarButton2.setDoubleBuffered(true);
    continuarButton2.setName("");
    continuarButton2.setSelected(false);
    continuarButton2.setText("Sim");
    continuarButton2.setVerticalAlignment(SwingConstants.CENTER);

    //---- sairButton4 ----
    sairButton4.setAutoscrolls(false);
    sairButton4.setBackground(Color.white);
    sairButton4.setBorderPainted(true);
    sairButton4.setContentAreaFilled(true);
    sairButton4.setDoubleBuffered(true);
    sairButton4.setName("");
    sairButton4.setSelected(false);
    sairButton4.setText("N\u00e3o");
    sairButton4.setVerticalAlignment(SwingConstants.CENTER);

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup()
            .addComponent(label1)
            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(label2))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(continuarButton2)
                .addGap(172, 172, 172)
                .addComponent(sairButton4))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGap(0, 0, 0)
                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(label2)
                .addGroup(layout.createParallelGroup()
                    .addComponent(continuarButton2)
                    .addComponent(sairButton4)))
    );
	// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    private JTextArea textArea1;
    private JButton continuarButton2;
    private JButton sairButton4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
