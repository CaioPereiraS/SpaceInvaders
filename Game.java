import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Game  extends JPanel {
    Spaceship nave;
    boolean k_esquerda;
    boolean k_direita;
    boolean k_space;
    boolean shooting;
    Background bg1;
    Background bg2;
    Aliens[][] listaAlien =new Aliens[6][8];
    Shot shot;

    //construtor
    public Game(){
        int X= 165; // posicao x inicial na tela do alien
        int Y= 20; //posicao y inicial da tela na coluna 1
        for (int i= 0;i < 6; i++ ){
            for(int j = 0; j < 8; j++){
                Aliens alien = new Aliens();
                alien.posX = X;
                alien.posY = Y;
                listaAlien[i][j]=alien;
                X += 60;
            }

            X = 165;
            Y+=45; //a cada coluna diferença de 45 px

        }
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
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

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
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
        });

        bg1 = new Background(); //instancia do back1
        bg2 = new Background(); //instancia do back2
        nave = new Spaceship();
        shot = new Shot();

        bg1.posX = 0;
        bg1.posY = 0;
        bg2.posX = 0;
        bg2.posY = -1200;// pq é 1200 de altura. quando o que movimentar pra baixa o de cima aparece
        setFocusable(true); // tem a capacidade de receber foco
        setLayout(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameloop(); // é invocado em uma nova unidade de execução
            }
        }).start();
    }

    // metodo do game loop
    public void gameloop(){
        while(true){//loop infinito
            handlerEvents();
            update();
            render();

            try{
                Thread.sleep(17);//pausa a thread
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }
    public void handlerEvents(){
        nave.velX = 0;

        if(k_esquerda==true){// tecla esquerda pressionada
            nave.velX = -5;
        } else if (k_direita==true) {// tecla direita
            nave.velX = 5;
        }
        if(k_space== true && shooting == false){
            shooting = true;
            shot.posX = nave.posX + (nave.largura/2);
            shot.posY = nave.posY;
            shot.active = true;
            try{
                shot.shot = ImageIO.read(getClass().getResource(""));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if (bg1.posY == 1200){ // reseta o bg1
            bg1.posY =- 1200;
        }
        if (bg2.posY == 1200){ //reseta o bg2
            bg2.posY =- 1200;
        }
        bg1.posY +=8;// movimento do bg1
        bg2.posY +=8;// movimento do bg1

        nave.posX += nave.velX; // atualiza o movimento
        if(shooting == true){ //se estiver atiranfo a posição y do tiro é somada com a velocidade y do tiro
            shot.posY += shot.velY;
        }
        for (int i = 0; i < 6; i++){ //movimenta os aliens horizontalmente
            for (int j = 0; j < 8; j++){
                listaAlien[i][j].posX += listaAlien[i][j].velX;
            }
        }
        testeColisao();

    }

    public void render(){
        repaint(); //repinta a tela toda
    }
    public void testeColisao(){
        if (nave.posX + (nave.largura) > Principal.LARGURA_TELA || nave.posX < 0) { // testando colisão horizontal
            nave.posX -= nave.velX; // nega a velx impedidnado que ande
        }
        }
    }

