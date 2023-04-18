import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class Background {
    public BufferedImage bg;
    public BufferedImage bg2;
    public int posX;
    public int posY;
    public int velY;

    public Background(){
        posX = 0;
        posY = 0;
        try{
            bg  = ImageIO.read(getClass().getResource(""));
            bg2 = ImageIO.read(getClass().getResource(""));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
