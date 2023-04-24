import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Spaceship {
    public int posX;
    public int posY;
    public int velX;
    public int altura;
    public int largura;
    public BufferedImage ship;

    public Spaceship() {
        largura = 60;
        altura = 60;
        posX = 340;
        posY = 520;
        velX = 0;
        try {
            ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/nave/nave1.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void anima_nave(Spaceship Nave) {

        for (int i = 1; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {

                    try {
                        ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/nave/nave" + finalI + ".png")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }

    }

    public void testeColisao(Spaceship nave) {
        if (nave.posX + (nave.largura) > Principal.LARGURA_TELA || nave.posX < 0) { // testando colisão horizontal
            nave.posX -= nave.velX; // nega a velx impedidnado que ande
        }
    }
}