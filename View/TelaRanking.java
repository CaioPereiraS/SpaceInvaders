package View;

import Controlador.UsuarioControlador;
import GameEngine.Principal;
import Modelo.UsuarioModelo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class TelaRanking extends JFrame {

    private JTable rankingTable;
    private DefaultTableModel tableModel;
    private JButton botaoPlay;
    UsuarioControlador $controlador = new UsuarioControlador();
    UsuarioModelo[] listaUsuario = $controlador.obterLista();

    public TelaRanking() throws SQLException {
        // Configurações da janela
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Cria o painel personalizado com imagem de fundo
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carrega a imagem de fundo
                ImageIcon imageIcon = new ImageIcon("images/Views/ranking.png");
                Image image = imageIcon.getImage();
                // Desenha a imagem de fundo no painel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        // Cria a caixa para centralizar a tabela
        JPanel boxPanel = new JPanel();
        boxPanel.setPreferredSize(new Dimension(500, 233));
        boxPanel.setOpaque(false);

        // Centraliza o boxPanel verticalmente e horizontalmente
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(boxPanel, gbc);

        // Cria o painel para o botão "Play"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(new Dimension(500, 50));
        buttonPanel.setOpaque(false);

        // Cria o botão "Play"
        JButton playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(100, 30));
        playButton.addActionListener(e -> {
            // Lógica do botão "Play"

            final Principal principal = new Principal();
            setVisible(false);
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
        scrollPane.setPreferredSize(new Dimension(500, 400));

        // Adiciona o painel de botões e o scroll pane na caixa
        boxPanel.setLayout(new BorderLayout());
        boxPanel.add(buttonPanel, BorderLayout.NORTH);
        boxPanel.add(scrollPane, BorderLayout.CENTER);

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

        // Define o painel personalizado como o conteúdo da janela
        setContentPane(backgroundPanel);
        // Atualiza a janela para que a imagem de fundo seja exibida
        revalidate();
    }

}
