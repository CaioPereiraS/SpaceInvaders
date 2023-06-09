package GameEngine;

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
        largura = 5;
        altura = 15;
        posX = 0;
        posY = 0;
        velY = -17 ;
        ativo = true;
        try{
            shot = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Nave/tiro.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
