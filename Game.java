import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    Tiro shot;
    boolean fim = false;

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
        });

        bg1 = new Background("images/bg1.png"); //instancia do back1
        bg2 = new Background("images/bg2.png"); //instancia do back2
        nave = new Spaceship();
        shot = new Tiro();

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

            //game over no else
            if(fim == false){update();}

            render();

            try{
                Thread.sleep(17); //pausa a thread
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
                shot.shot = ImageIO.read(getClass().getResource("images/tiro.png"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if (bg1.posY == 1200){ // reseta o bg1
            bg1.posY =- 0;
        }
        if (bg2.posY == 1200){ //reseta o bg2
            bg2.posY =- 1200;
        }
        bg1.posY +=10; // movimento do bg1
        bg2.posY +=10; // movimento do bg1

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
    public boolean testeColisao(){
        if (nave.posX + (nave.largura) > Principal.LARGURA_TELA || nave.posX < 0) { // testando colisão horizontal
            nave.posX -= nave.velX; // nega a velx impedidnado que ande
        }
        for (int i = 0; i<6; i++){ // teste de colisão por matriz, setando novos atributos pós colisao
            for (int j = 0; j<8; j++){
                Aliens atual = listaAlien[i][j];
                if(atual.isVisble==false){//se o inimigo tive destruido nem testa
                    continue;
                }
                if(shot.posX<=atual.posX + atual.largura&&
                shot.posX>= atual.posX &&
                shot.posY<=atual.posY+ atual.altura &&
                shot.posY>=atual.posY &&
                shot.active == true){

                  
    
                    atual.isVisble=false;// seta inimigos atingidos para false
                    //atual.inimigo = ;// destroi a imagem do inimigo
                    shot.active = false; // tiro deixa de existir
                    shot.shot = null; // destroi a imagem do tiro
                    shooting = false; // deixa atirar dnv
                }
            }
        }
        // checar aliens colidindo com a tela
        for (int i = 0; i <6; i++){
            for (int j = 0; j<8; j++){
                Aliens atual = listaAlien[i][j];
                if (atual.posX + atual.largura > Principal.LARGURA_TELA){// checa colisao com o lado direito
                    atual.posY += atual.altura; // desce uma altura
                    atual.velX*= -1.2; // aumenta em 1,2 a velocidade
                }
                if (atual.posX<=0){//checa a colisao com o lado esquedo
                    atual.posY+= listaAlien[i][j].altura;//desce a matriz inteira
                    atual.velX*= -1.2;//aumenta dnv a velocidade
                }

                //colisão com o fim da página
                if(((atual.posY + atual.altura ) > Principal.ALTURA_TELA && atual.isVisble== true)){
                    return fim = true;
                }

                //colisão com a nave
                if(nave.posX<=atual.posX + atual.largura&&
                 nave.posX>= atual.posX &&
                nave.posY<=atual.posY+ atual.altura &&
                nave.posY>=atual.posY &&
                atual.isVisble== true ){
                    return fim = true;
                }

            }
        }
        if (shooting==true && shot.posY<0){// se o tiro sair da tela pode atirar dnv
            shooting=false;
        }
        return fim = false;
        }

        protected  void paintComponent(Graphics g){
            super.paintComponent(g); // desenha as coisas na tela
            g.drawImage(bg1.bg, bg1.posX, bg1.posY,null);
            g.drawImage(bg2.bg, bg2.posX, bg2.posY,null);
            for(int i = 0; i < 6; i++){ // desenha a matriz de aliens
                for(int j = 0; j<8; j++){
                    g.drawImage(listaAlien[i][j].inimigo,listaAlien[i][j].posX,listaAlien[i][j].posY,null);
                }
            }
            g.drawImage(nave.ship, nave.posX, nave.posY, null); // desenha a nave
            if(shooting){
                g.drawImage(shot.shot, shot.posX, shot.posY,null);
            }

        }
    }

