package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    public Bomb(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (BombermanGame.count == 0) {
            img = Sprite.bomb_1.getFxImage();
        }
        if (BombermanGame.count == 1) {
            img = Sprite.bomb_2.getFxImage();
        }
    }
}
