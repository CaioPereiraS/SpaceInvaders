package View;

import Model.UsuarioModelo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRanking extends JFrame {

    private JTable rankingTable;
    private DefaultTableModel tableModel;
    private JButton botaoPlay;
    UsuarioModelo[] listaUsuario = new UsuarioModelo[]{
            new UsuarioModelo("Alice", 100),
            new UsuarioModelo("Bob", 200),
            new UsuarioModelo("Carol", 150)};

    public TelaRanking() {

        // Configurações da janela
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Cria o painel para o botão "Play"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(new Dimension(getWidth(), 50));

        // Cria o botão "Play"
        JButton playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(100, 30));
        playButton.addActionListener(e -> {
            // Lógica do botão "Play"



            System.out.println("Botão 'Play' pressionado");
        });

        // Adiciona o botão "Play" ao painel
        buttonPanel.add(playButton);

        // Cria a tabela de ranking
        rankingTable = new JTable();
        rankingTable.getTableHeader().setReorderingAllowed(false);
        rankingTable.getTableHeader().setResizingAllowed(false);
        rankingTable.setRowSelectionAllowed(false);

        // Centraliza o conteúdo na tabela
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        rankingTable.setDefaultRenderer(Object.class, centerRenderer);

        // Cria o scroll pane
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        scrollPane.setPreferredSize(new Dimension(800, 520));

        // Adiciona o painel de botões e o scroll pane na janela
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Cria o modelo de tabela
        tableModel = new DefaultTableModel(new Object[]{"Nick", "Pontuação Máxima"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Preenche a tabela com os dados dos usuários
        for (UsuarioModelo usuario : listaUsuario) {
            Object[] rowData = {usuario.getNick(), usuario.getMaximaPontuacao()};
            tableModel.addRow(rowData);
        }

        // Define o modelo de tabela na tabela de ranking
        rankingTable.setModel(tableModel);

        // Exibe a janela


    }

}