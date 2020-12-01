package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Iterator;

public class Bomb extends Entity {
    private double timeToExplode = 10;
    private double timeAfter = 2;
    private boolean _exploded = false; // đã nổ ?
    private boolean _canMove = true; // có thể đi qua
    private int radius = 2;

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
        frameBomb();
        if(timeToExplode > 0)
            timeToExplode--;
        else {
            explode();
            if (timeAfter > 0) {
                timeAfter--;
            } else {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < calculateLengthFlame(i); j++) {
                        switch (i) {
                            case 0:
                                collide(x, y - 1 - j);
                            case 1:
                                collide(x, y + 1 + j);
                            case 2:
                                collide(x - 1 - j, y);
                            case 3:
                                collide(x + 1 + j, y);
                        }
                    }
                }
                BombermanGame.flame.clear();
                BombermanGame.bomb.remove(this);
            }
        }
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
        _exploded = true;
        Flame flame = new Flame(x, y, Sprite.bomb_exploded.getFxImage());
        BombermanGame.flame.add(flame);
        for (int i = 0; i < 4; i++) {
            for (int j = 0 ; j < calculateLengthFlame(i); j++) {
                if (i == 0) {
                    if (j == calculateLengthFlame(i) - 1)
                        flame = new Flame(x, y - j - 1, Sprite.explosion_vertical_top_last.getFxImage());
                    else
                        flame = new Flame(x, y - j - 1, Sprite.explosion_vertical.getFxImage());
                }
                if (i == 1) {
                    if (j == calculateLengthFlame(i) - 1)
                        flame = new Flame(x, y + j + 1, Sprite.explosion_vertical_down_last.getFxImage());
                    else
                        flame = new Flame(x, y + j + 1, Sprite.explosion_vertical.getFxImage());
                }
                if (i == 2) {
                    if (j == calculateLengthFlame(i) - 1)
                        flame = new Flame(x - j - 1, y, Sprite.explosion_horizontal_left_last.getFxImage());
                    else
                    flame = new Flame(x - j - 1, y, Sprite.explosion_horizontal.getFxImage());
                }
                if (i == 3) {
                    if (j == calculateLengthFlame(i) - 1)
                        flame = new Flame(x +j + 1, y, Sprite.explosion_horizontal_right_last.getFxImage());
                    else
                    flame = new Flame(x +j + 1, y, Sprite.explosion_horizontal.getFxImage());
                }
                BombermanGame.flame.add(flame);
            }
        }
    }

    public void collide(double x, double y) {
        Flame flame = new Flame(x, y , null);
       for (int i = 0; i < BombermanGame.stillObjects.size(); i++) {
           Entity object = BombermanGame.stillObjects.get(i);
           if(object instanceof Brick) {
               if (object.intersects(flame)) {
                   Entity grass = new Grass(object.getX(), object.getY(), Sprite.grass.getFxImage());
                   BombermanGame.stillObjects.set(i, grass);
               }
           }
       }
        for (int i = 0; i < BombermanGame.entities.size(); i++) {
            Entity object = BombermanGame.entities.get(i);
                if (object.intersects(flame)) {
                    BombermanGame.entities.remove(i);
                }
        }
    }

    public int calculateLengthFlame(int direction) {
        boolean collide = false;
        double _x = x, _y = y;
        int _radius = 0;
        while (_radius < radius) {
            if (direction == 0) _y--;
            if (direction == 1) _y++;
            if (direction == 2) _x--;
            if (direction == 3) _x++;
           Flame _flame = new Flame(_x, _y, Sprite.explosion_horizontal.getFxImage());
            for(Entity object : BombermanGame.stillObjects) {
                if (object instanceof Wall) {
                    if (object.intersects(_flame)) collide = true;
                }
            }
            if (collide) break;
            _radius++;
        }
        return _radius;
    }
}
