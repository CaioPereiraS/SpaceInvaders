package GameEngine;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.util.Objects;

public class Animacao extends JPanel {
    NaveEspacial Nave;
    Aliens Alien;

    public void animarMorteNave(NaveEspacial Nave, int frame) {

        try {
            Nave.ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Nave/Morte/morte" + frame + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
