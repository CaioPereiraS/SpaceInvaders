import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class Aliens {

    public int posX;
    public int posY;
    public int velX;
    public int vely;
    public int altura;
    public int largura;
    public BufferedImage inimigo;

    public boolean isVisble;

    public Aliens(){
        largura = 40;
        altura = 40;
        posX = 50;
        posY = 10;
        velX = 4;
        isVisble = true;
        try{
            inimigo = ImageIO.read(getClass().getResource("images/alien.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
