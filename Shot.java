import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

public class Shot {
    public int posX;
    public int posY;
    public int velY;
    public int altura;
    public int largura;
    public BufferedImage shot;
    boolean active;

    public Shot(){
        largura = 1;
        altura = 5;
        posX = 0;
        posY = 0;
        velY = -15;
        active = true;
        try{
            shot = ImageIO.read(getClass().getResource(""));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
