package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Flame extends Entity {
    protected int _direction;
    private int _radius;

    public Flame(double x, double y,Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
