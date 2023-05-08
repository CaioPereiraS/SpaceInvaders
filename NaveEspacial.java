import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;


public class NaveEspacial extends Thread{
    public int posX;
    public int posY;
    public int velX;
    public int altura;
    public int largura;
    Tiro disparo;
    boolean atirando;
    public BufferedImage ship;
    public boolean Viva = true;

    public NaveEspacial() {
        largura = 60;
        altura = 60;
        posX = 340;
        posY = 520;
        velX = 1;
        try {
            ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/nave/nave1.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void anima_nave(NaveEspacial Nave) {

        for (int i = 1; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                    try {
                        ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/nave/nave" + finalI + ".png")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }).start();
        }
    }

    public void testeColisao(NaveEspacial nave) {
        if (nave.posX + (nave.largura) > Principal.LARGURA_TELA || nave.posX < 0) { // testando colisão horizontal
            nave.posX -= nave.velX; // nega a velx impedidnado que ande
        }
    }

    public void podeDisparar(NaveEspacial nave){
        if (nave.atirando && nave.disparo.posY < 0) {// se o tiro sair da tela pode atirar dnv
            nave.atirando = false;
        }
    }


}