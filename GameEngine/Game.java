package GameEngine;

import Modelo.UsuarioModelo;
import View.TelaGameOver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.Objects;

public class Game extends JPanel {
    NaveEspacial nave;
    boolean k_esquerda;
    boolean k_direita;
    boolean k_space;
    Background bg1;
    Background bg2;
    Aliens[][] listaAlien = new Aliens[6][8];
    int total_de_naves = 48;
    boolean fim = false;
    Animacao animador = new Animacao();
    int pontuacao = 0;
    UsuarioModelo $usuarioLogado = new UsuarioModelo();

    //construtor
    public Game(UsuarioModelo usuarioModelo) {
        $usuarioLogado = usuarioModelo;
        int X = 165; // posição x inicial na tela do alien
        int Y = 20; //posição y inicial da tela na coluna 1
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                Aliens alien = new Aliens();
                alien.posX = X;
                alien.posY = Y;
                listaAlien[i][j] = alien;
                X += 60;
            }
            X = 165;
            Y += 45; //a cada coluna diferença de 45 px

        }
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: // pega o valor da seta esquerda
                        k_esquerda = true;
                        break;
                    case KeyEvent.VK_RIGHT: // pega o valor da seta direita
                        k_direita = true;
                        break;
                    case KeyEvent.VK_SPACE: // pega o valor do espaço
                        k_space = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: // pega o valor da seta esquerda
                        k_esquerda = false;
                        break;
                    case KeyEvent.VK_RIGHT: // pega o valor da seta direita
                        k_direita = false;
                        break;
                    case KeyEvent.VK_SPACE: // pega o valor do espaço
                        k_space = false;
                        break;
                }
            }
        });

        bg1 = new Background("/images/Level/bg1.png"); //instancia do back1
        bg2 = new Background("/images/Level/bg1.png"); //instancia do back2
        nave = new NaveEspacial();
        nave.disparo = new Tiro();

        bg1.posX = 0;
        bg1.posY = 0;
        bg2.posX = 0;
        bg2.posY = -600;// pq é 1200 de altura. quando o que movimentar pra baixa o de cima aparece
        setFocusable(true); // consegue receber foco
        setLayout(null);

        // é invocado em uma nova unidade de execução
        new Thread(this::gameloop).start();
    }

    // método do game loop
    public void gameloop() {
        while (!fim) {//loop
            handlerEvents();
            // render e configura os 60 fps
            render();
            try {
                Thread.sleep(18); /*pausa a thread*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();
        }
        if (fim) {
            //game over
            TelaGameOver gameOver  = new TelaGameOver($usuarioLogado.getUltimaPontuacao());
        }

    }

    public void handlerEvents() {
        nave.velX = 0;
        if (k_esquerda) {
            nave.velX = -7;
        } // tecla esquerda pressionada
        else if (k_direita) {
            nave.velX = 7;
        }// tecla direita

        if (k_space && !nave.atirando) {
            nave.atirando = true;
            nave.disparo.posX = nave.posX + (nave.largura / 2);
            nave.disparo.posY = nave.posY;
            nave.disparo.ativo = true;
            try {
                nave.disparo.shot = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Nave/tiro.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (bg1.posY == 600) { // reset o bg1
            bg1.posY = -600;
        }
        if (bg2.posY == 600) { //reset o bg2
            bg2.posY = -600;
        }
        bg1.posY += 12; // movimento do bg1
        bg2.posY += 12; // movimento do bg1

        nave.posX += nave.velX; // atualiza o movimento
        //se estiver atirando a posição y do tiro é somada com a velocidade y do tiro
        if (nave.atirando) {
            nave.disparo.posY += nave.disparo.velY;
        }

        testeColisao();
    }

    // repinta a tela toda
    public void render() {
        repaint();
    }

    public boolean testeColisao() {

        if (fim == false) {
            //colisão nave
            nave.testeColisao(nave);

            // checar aliens colidindo
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 8; j++) {
                    listaAlien[i][j].posX += listaAlien[i][j].velX;
                    Aliens atual = listaAlien[i][j];
                    //colidindo com a tela
                    nave.Viva = atual.testeColisaoTela(atual);
                    //colidindo com a nave
                    nave.Viva = atual.testeColisaoNave(atual, nave)

                    ;
                    //colidindo com o tiro
                    if (atual.testeColisaoDisparo(atual, nave)) {
                        total_de_naves--;
                        pontuacao += 1;
                        if (total_de_naves == 0) {
                            fim = true;
                        }
                    }
                }
            }
            // checa alien com o tiro
            nave.podeDisparar(nave);
        }
        return fim;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // desenha as coisas na tela

        g.drawImage(bg1.bg, bg1.posX, bg1.posY, null);
        g.drawImage(bg2.bg, bg2.posX, bg2.posY, null);


        for (int i = 0; i < 6; i++) { // desenha a matriz de aliens
            for (int j = 0; j < 8; j++) {
                g.drawImage(listaAlien[i][j].inimigo, listaAlien[i][j].posX, listaAlien[i][j].posY, null);
            }
        }

        if (!nave.Viva) {
            fim = true;
        }

        g.drawImage(nave.ship, nave.posX, nave.posY, null); // desenha a nave
        if (nave.atirando) {
            g.drawImage(nave.disparo.shot, nave.disparo.posX, nave.disparo.posY, null);
        }

    }

}