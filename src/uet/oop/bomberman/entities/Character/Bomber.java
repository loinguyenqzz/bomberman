package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private final double step = 0.5;
    private int index = 0;

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        move();
        putBomb();
        collide();
    }

    public void move() {
        if (BombermanGame.input.contains("LEFT")) {
            if (BombermanGame.count == 0) {
                img = Sprite.player_left.getFxImage();
            }
            if (BombermanGame.count == 1) {
                img = Sprite.player_left_1.getFxImage();
            }
            if (BombermanGame.count == 2) {
                img = Sprite.player_left_2.getFxImage();
            }
            if(canMove(x - step, y)) {
                x -= step;
            }
        }
        if (BombermanGame.input.contains("RIGHT")) {
            if (BombermanGame.count == 0) {
                img = Sprite.player_right.getFxImage();
            }
            if (BombermanGame.count == 1) {
                img = Sprite.player_right_1.getFxImage();
            }
            if (BombermanGame.count == 2) {
                img = Sprite.player_right_2.getFxImage();
            }
            if (canMove(x + step, y)) {
                x += step;
            }
        }
        if (BombermanGame.input.contains("UP")) {
            if (BombermanGame.count == 0) {
                img = Sprite.player_up.getFxImage();
            }
            if (BombermanGame.count == 1) {
                img = Sprite.player_up_1.getFxImage();
            }
            if (BombermanGame.count == 2) {
                img = Sprite.player_up_2.getFxImage();
            }
            if (canMove(x, y - step)) {
                y -= step;
            }
        }
        if (BombermanGame.input.contains("DOWN")) {
            if (BombermanGame.count == 0) {
                img = Sprite.player_down.getFxImage();
            }
            if (BombermanGame.count == 1) {
                img = Sprite.player_down_1.getFxImage();
            }
            if (BombermanGame.count == 2) {
                img = Sprite.player_down_2.getFxImage();
            }
            if (canMove(x, y + step)) {
                y += step;
            }
        }
    }

    public void collide(){
        for(Entity object : BombermanGame.entities) {
            if (!(object instanceof Bomber)) {
                if (object.intersects(this))
                BombermanGame.entities.remove(this);
            }
        }
    }


    public boolean canMove(double _x, double _y) {
        Bomber bomber = new Bomber(_x, _y, img);
        for(Entity object : BombermanGame.stillObjects) {
            if (!(object instanceof Grass)) {
                if (object.intersects(bomber)) return false;
            }
        }
        return true;
    }



    public void putBomb() {
        if (BombermanGame.input.contains("SPACE")) {
            Bomb bomb = new Bomb(this.x, this.y, Sprite.bomb_1.getFxImage());
            BombermanGame.bomb.add(bomb);
        }
    }

}
