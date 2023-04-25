import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.Objects;

public class Game extends JPanel {
    Spaceship nave;
    boolean k_esquerda;
    boolean k_direita;
    boolean k_space;
    boolean atirando;
    Background bg1;
    Background bg2;
    Aliens[][] listaAlien = new Aliens[6][8];
    Tiro disparo;
    int total_de_naves = 48;
    boolean fim = false;
    int frame_final = 60;

    Animacao animador = new Animacao();

    //construtor
    public Game() {
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

        bg1 = new Background("images/bg1.png"); //instancia do back1
        bg2 = new Background("images/bg2.png"); //instancia do back2
        nave = new Spaceship();
        disparo = new Tiro();

        bg1.posX = 0;
        bg1.posY = 0;
        bg2.posX = 0;
        bg2.posY = -600;// pq é 1200 de altura. quando o que movimentar pra baixa o de cima aparece
        setFocusable(true); // consegue receber foco
        setLayout(null);

        new Thread(() -> {
            gameloop(); // é invocado em uma nova unidade de execução
        }).start();

    }

    // método do game loop
    public void gameloop() {
        while (true) {//loop infinito
            handlerEvents();

            //game over no else
            if (!fim) {
                update();
            }

            render();

            try {
                Thread.sleep(18 ); //pausa a thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void handlerEvents() {
        nave.velX = 0;

        if (k_esquerda) {// tecla esquerda pressionada
            nave.velX = -7;
        } else if (k_direita) {// tecla direita
            nave.velX = 7;
        }
        if (k_space && !atirando) {
            atirando = true;
            disparo.posX = nave.posX + (nave.largura / 2);
            disparo.posY = nave.posY;
            disparo.ativo = true;
            try {
                disparo.shot = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/tiro.png")));
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

        nave.anima_nave(nave); // executa a função na thread secundária, anima a nave

        nave.posX += nave.velX; // atualiza o movimento
        if (atirando) { //se estiver atirando a posição y do tiro é somada com a velocidade y do tiro
            disparo.posY += disparo.velY;
        }
        for (int i = 0; i < 6; i++) { //movimenta os aliens horizontalmente
            for (int j = 0; j < 8; j++) {
                listaAlien[i][j].posX += listaAlien[i][j].velX;
            }
        }

        testeColisao();

    }

    public void render() {
        repaint(); //repinta a tela toda
    }

    public boolean testeColisao() {
        nave.testeColisao(nave);

        for (int i = 0; i < 6; i++) { // teste de colisão por matriz, setando novos atributos pós-colisão
            for (int j = 0; j < 8; j++) {
                Aliens atual = listaAlien[i][j];
                if (!atual.isVisble) {//se o inimigo tive destruído nem testa
                    continue;
                }
                if (disparo.posX <= atual.posX + atual.largura &&
                        disparo.posX >= atual.posX &&
                        disparo.posY <= atual.posY + atual.altura &&
                        disparo.posY >= atual.posY &&
                        disparo.ativo) {
                    atual.isVisble = false;// seta os inimigos atingidos para false
                    atual.inimigo = null;// destrói as imagens inimigas
                    disparo.ativo = false; // tiro deixa de existir
                    disparo.shot = null; // destrói a imagem do tiro
                    atirando = false; // deixa atirar dnv
                    total_de_naves--;
                    if (total_de_naves == 0) {
                        return fim = true;
                    }

                }
            }
        }
        // checar aliens colidindo com a tela
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                Aliens atual = listaAlien[i][j];
                if (atual.posX + atual.largura > Principal.LARGURA_TELA) {// checa colisão com o lado direito
                    atual.posY += atual.altura; // desce uma altura
                    atual.velX *= -1.2; // aumenta em 1,2 a velocidade
                }
                if (atual.posX <= 0) {//checa a colisão com o lado esquerdo
                    atual.posY += listaAlien[i][j].altura;//desce a matriz inteira
                    atual.velX *= -1.2;//aumenta dnv a velocidade
                }

                //colisão com o fim da página
                if (((atual.posY + atual.altura) > Principal.ALTURA_TELA && atual.isVisble)) {
                    return fim = true;
                }

                //colisão com a nave
                if (nave.posX <= atual.posX + atual.largura &&
                    nave.posX >= atual.posX &&
                    nave.posY <= atual.posY + atual.altura &&
                    nave.posY >= atual.posY &&
                    atual.isVisble){
                        atual.velX =0;
                        animador.animarMorteNave(nave);
                        frame_final--;
                        if(frame_final ==0){
                            return fim = true;
                        }

                }
            }
        }

        // checa alien com o tiro
        if (atirando && disparo.posY < 0) {// se o tiro sair da tela pode atirar dnv
            atirando = false;
        }
        return fim = false;
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
        g.drawImage(nave.ship, nave.posX, nave.posY, null); // desenha a nave
        if (atirando) {
            g.drawImage(disparo.shot, disparo.posX, disparo.posY, null);
        }

    }
}