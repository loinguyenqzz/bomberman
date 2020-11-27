package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Character.Balloon;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Oneal;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    public int LEVEL;
    public int ROWS_MAP;
    public int COLUMNS_MAP;
    public static int count = 0;
    public static ArrayList<String> input = new ArrayList<>();
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> bomb = new ArrayList<>();
    public static List<Entity> flame = new ArrayList<>();

    private GraphicsContext gc;
    private Canvas canvas;
    static double currentGameTime;
    static double oldGameTime;
    static double deltaTime;
    final static long startNanoTime = System.nanoTime();


    public static void main(String[] args) {
        System.setProperty("quantum.multithreaded", "true");

        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        createMap();
                // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * COLUMNS_MAP, Sprite.SCALED_SIZE * ROWS_MAP);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("Bomberman");

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });
        scene.setOnKeyReleased(
        new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                oldGameTime = currentGameTime;
                currentGameTime = (currentNanoTime - startNanoTime) / 1000000000.0;
                deltaTime = currentGameTime - oldGameTime;
                System.out.println(deltaTime * 100);

                render();
                update();
                count = count % 3;
                count++;
            }
        };
        timer.start();
    }

    public void createMap() {
        File text = new File("bomberman\\res\\levels\\Level1.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String s = scanner.nextLine();
        String[] parts = s.split(" ");
        this.LEVEL = Integer.parseInt(parts[0]);
        this.ROWS_MAP = Integer.parseInt(parts[1]);
        this.COLUMNS_MAP = Integer.parseInt(parts[2]);
        String map;
        for (int i = 0; i < ROWS_MAP; i++) {
            map = scanner.nextLine();
            for (int j = 0; j < COLUMNS_MAP; j++) {
                Entity object;
                if (map.charAt(j) == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                }
                else if (map.charAt(j) == '*') {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                }
                else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
                if (map.charAt(j) == 'p') {
                    Entity bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                    entities.add(bomberman);
                }
                if (map.charAt(j) == '1') {
                    Entity balloon = new Balloon(j, i, Sprite.balloom_right2.getFxImage());
                    entities.add(balloon);
                }
                if (map.charAt(j) == '2') {
                    Entity oneal = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                    entities.add(oneal);
                }
            }
        }
    }

    public void update() {
        bomb.forEach(Entity::update);
        entities.forEach(Entity::update);
        flame.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomb.forEach(g -> g.render(gc));
        flame.forEach(g -> g.render(gc));
    }
}
