package uet.oop.bomberman.entities.Character;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private double step = 0.5;

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        move();
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
            x -= step;
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
            x += step;
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
            y -= step;
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
            y += step;
        }
    }
}
