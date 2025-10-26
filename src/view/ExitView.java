package view;

import java.awt.*;
import javax.swing.*;

public class ExitView extends JPanel {
    private JPanel mainPanel;

    public ExitView() {
        initComponents();
        String nomes = "Denis Alves<br>" +
                "Ulisses Aguiar";
        nomesLabel.setText("<html>" + nomes + "</html>");

        String curso = "BSI | Segundo Semestre<br>" +
                "2025";
        cursoLabel.setText("<html>" + curso + "</html>");

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void initComponents() {
	// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    var label1 = new JLabel();
    nomesLabel = new JLabel();
    cursoLabel = new JLabel();

    //======== this ========
    setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing.
    border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER
    ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font
    . BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener(
    new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r"
    .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

    //---- label1 ----
    label1.setText("Obrigado por usar nosso programa :)");

    //---- nomesLabel ----
    nomesLabel.setText("Label");

    //---- cursoLabel ----
    cursoLabel.setText("Label");

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(label1))
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(nomesLabel)
                .addGap(216, 216, 216)
                .addComponent(cursoLabel))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(label1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup()
                    .addComponent(nomesLabel)
                    .addComponent(cursoLabel)))
    );
	// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Denis
    private JLabel nomesLabel;
    private JLabel cursoLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}