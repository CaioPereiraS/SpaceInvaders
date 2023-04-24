import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Spaceship {
    public int posX;
    public int posY;
    public int velX;
    public int altura;
    public int largura;
    public BufferedImage ship;

    public Spaceship(){
        largura = 60;
        altura = 60;
        posX = 340;
        posY = 520;
        velX = 0;
        try{
            ship = ImageIO.read(getClass().getResource("images/nave1.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void anima_nave(Spaceship Nave){

    }

    public void testeColisao(Spaceship nave){
        if (nave.posX + (nave.largura) > Principal.LARGURA_TELA || nave.posX < 0) { // testando colisão horizontal
            nave.posX -= nave.velX; // nega a velx impedidnado que ande
        }
    }
}