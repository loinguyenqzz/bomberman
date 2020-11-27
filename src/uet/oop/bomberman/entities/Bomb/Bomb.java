package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private static double timeToExplode = 3;
    private double timeAfter = 20;
    protected Flame[] _flames;
    private boolean _exploded = false; // đã nổ ?
    private boolean _canMove = true; // có thể đi qua

    public Bomb(double x, double y, Image img) {
        super(x, y, img);
    }

    public boolean isCanMove() {
        return _canMove;
    }

    public boolean isExplode() {
        return _exploded;
    }

    @Override
    public void update() {
        if(timeToExplode > 0)
            timeToExplode--;
        else {
            explode();
            for (int i = 0; i < 4; i++) {
                BombermanGame.flame.add(_flames[i]);
            }
        }
        frameBomb();
    }

    public void frameBomb() {
        if (BombermanGame.count == 0) {
            img = Sprite.bomb_1.getFxImage();
        }
        if (BombermanGame.count == 1) {
            img = Sprite.bomb_2.getFxImage();
        }
        if (BombermanGame.count == 2) {
            img = Sprite.bomb_1.getFxImage();
        }
    }

    public void explode() {
        _canMove = true;
        _exploded = true;

        _flames = new Flame[4];
        for (int i = 0; i < _flames.length; i++) {
            _flames[i] = new Flame((int)x, (int)y, i, 3, img);
        }

    }

    public void updateFlames() {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].update();
            // System.out.println("flame lengt= " + _flames.length);
        }
    }
}
