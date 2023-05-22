package GameModule;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Tiro {
    public int posX;
    public int posY;
    public int velY;
    public int altura;
    public int largura;
    public BufferedImage shot;
    boolean ativo;

    public Tiro(){
        largura = 3;
        altura = 10;
        posX = 0;
        posY = 0;
        velY = -17 ;
        ativo = true;
        try{
            shot = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/tiro.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
