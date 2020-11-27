package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Entity {
    private int i = 1;
    public Balloon(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (i % 2 == 0) {
            if (BombermanGame.count == 0) {
                img = Sprite.balloom_right1.getFxImage();
            }
            if (BombermanGame.count == 1) {
                img = Sprite.balloom_right2.getFxImage();
            }
            if (BombermanGame.count == 2) {
                img = Sprite.balloom_right3.getFxImage();
            }
        } else {
            if (BombermanGame.count == 0) {
                img = Sprite.balloom_left1.getFxImage();
            }
            if (BombermanGame.count == 1) {
                img = Sprite.balloom_left2.getFxImage();
            }
            if (BombermanGame.count == 2) {
                img = Sprite.balloom_left3.getFxImage();
            }
        }
        double speed = 0.1;
        x += speed * Math.pow(-1, i);
        for(Entity object : BombermanGame.stillObjects) {
            if (!(object instanceof Grass)) {
                if (object.intersects(this)) {
                    i++;
                }
            }
        }
    }
}
