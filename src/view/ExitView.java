package view;

import javax.swing.*;

public class ExitView {
    private JPanel mainPanel;
    private JLabel nomesLabel;
    private JLabel cursoLabel;

    public ExitView() {
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
}
