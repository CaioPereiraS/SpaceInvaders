package View;

import Model.UsuarioModelo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelaRanking extends JFrame {

    private JTable rankingTable;
    private DefaultTableModel tableModel;

    public TelaRanking() {


        // Configurações da janela
        setTitle("Tela de Rakaing");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new JLabel(new ImageIcon("background.jpg")));


        // Cria o modelo de tabela
        // Cria a tabela de ranking
        rankingTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        rankingTable.getTableHeader().setReorderingAllowed(false);
        rankingTable.getTableHeader().setResizingAllowed(false);
        rankingTable.setRowSelectionAllowed(false);

        // Centraliza o conteúdo na tabela
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        rankingTable.setDefaultRenderer(Object.class, centerRenderer);

        // Personaliza o renderizador para tornar as células transparentes
        rankingTable.setOpaque(false);
        ((DefaultTableCellRenderer) rankingTable.getDefaultRenderer(Object.class)).setOpaque(false);

        // Cria o scroll pane
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        scrollPane.setBounds(50, 50, 700, 500);

        // Adiciona o scroll pane na janela
        getContentPane().add(scrollPane);
        // Cria o modelo de tabela
        tableModel = new DefaultTableModel(new Object[]{"Nick", "Pontuação Máxima"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        UsuarioModelo[] listaUsuario = new UsuarioModelo[]{
                new UsuarioModelo("Alice", 100),
                new UsuarioModelo("Bob", 200),
                new UsuarioModelo("Carol", 150)};

        for (UsuarioModelo usuario : listaUsuario) {
            Object[] rowData = {usuario.getNick(), usuario.getMaximaPontuacao()};
            tableModel.addRow(rowData);
        }

        rankingTable.setModel(tableModel);
        setVisible(true);

    }
}

