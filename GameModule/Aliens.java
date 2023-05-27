package GameModule;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Aliens {

    public int posX;
    public int posY;
    public float velX;
    public int vely;
    public int altura;
    public int largura;
    public BufferedImage inimigo;
    public boolean isVisble;
    public boolean ativo;
    Animacao animador = new Animacao();

//xxxx
    public Aliens() {
        largura = 40;
        altura = 40;
        posX = 50;
        posY = 10;
        velX = 3f;
        isVisble = true;
        try {
            inimigo = ImageIO.read(getClass().getResource("/images/Aliens/alien.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean testeColisaoTela(Aliens alienAtual) {

        if (alienAtual.posX + alienAtual.largura > Principal.LARGURA_TELA) {// checa colisão com o lado direito
            alienAtual.posY += alienAtual.altura; // desce uma altura
            alienAtual.velX *= -1.1; // aumenta em 1,2 a velocidade
        }
        if (alienAtual.posX <= 0) {//checa a colisão com o lado esquerdo
            alienAtual.posY += alienAtual.altura;//desce a matriz inteira
            alienAtual.velX *= -1.1;//aumenta dnv a velocidade
        }

        //colisão com o fim da página
        if ((alienAtual.posY + alienAtual.altura) > Principal.ALTURA_TELA && alienAtual.isVisble) {
            return false;
        }
        return true;
    }

    public boolean testeColisaoNave(Aliens alienAtual, NaveEspacial nave) {

        if (nave.posX <= alienAtual.posX + alienAtual.largura &&
                nave.posX >= alienAtual.posX &&
                nave.posY <= alienAtual.posY + alienAtual.altura &&
                nave.posY >= alienAtual.posY &&
                alienAtual.isVisble) {
            alienAtual.velX = 0;
            return false;
        }

        return true;
    }

    public boolean testeColisaoDisparo(Aliens alienAtual, NaveEspacial nave) {

        if (!alienAtual.isVisble) {//se o inimigo tive destruído nem testa
            return false;
        }
        if (nave.disparo.posX <= alienAtual.posX + alienAtual.largura &&
                nave.disparo.posX >= alienAtual.posX &&
                nave.disparo.posY <= alienAtual.posY + alienAtual.altura &&
                nave.disparo.posY >= alienAtual.posY &&
                nave.disparo.ativo) {
            alienAtual.isVisble = false;// seta os inimigos atingidos para false
            alienAtual.inimigo = null;// destrói as imagens inimigas
            nave.disparo.ativo = false; // tiro deixa de existir
            nave.disparo.shot = null; // destrói a imagem do tiro
            nave.atirando = false; // deixa atirar dnv
            return true;
        }
        return false;
    }



}
