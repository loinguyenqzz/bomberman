package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Flame extends Entity {
    protected int _direction;
    private int _radius;
    protected int xOrigin, yOrigin;
    protected FlameSegment[] _flameSegments = new FlameSegment[0];

    public Flame(int x, int y, int direction, int radius, Image img) {
        super(x, y, img);
        xOrigin = x;
        yOrigin = y;
        _direction = direction;
        _radius = radius;
        createFlameSegments();
    }

    private void createFlameSegments() {
        /**
         * tính toán độ dài Flame, tương ứng với số lượng segment
         */
        _flameSegments = new FlameSegment[calculatePermitedDistance()];

        /**
         * biến last dùng để đánh dấu cho segment cuối cùng
         */
        boolean last;

        //  tạo các cạnh dưới đây
        int _x = (int)x;
        int _y = (int)y;
        for (int i = 0; i < _flameSegments.length; i++) {
            last = i == _flameSegments.length -1 ? true : false;

            switch (_direction) {
                case 0: _y--; break;
                case 1: _x++; break;
                case 2: _y++; break;
                case 3: _x--; break;
            }
            _flameSegments[i] = new FlameSegment(_x, _y, _direction, last);
        }


    }

    /**
     * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn
     * @return
     */
    private int calculatePermitedDistance() {
        //thực hiện tính toán độ dài của Flame khi bom nổ
        int radius = 0;
        int _x = (int)x;
        int _y = (int)y;
        while(radius < _radius) {
            if(_direction == 0) _y--;// lên trên
            if(_direction == 1) _x++;// sang phải
            if(_direction == 2) _y++;// sang phải
            if(_direction == 3) _x--;// sang trái

            ++radius;
        }
        return radius;

    }

    public FlameSegment flameSegmentAt(int x, int y) {
        for (int i = 0; i < _flameSegments.length; i++) {
            if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
                return _flameSegments[i];
        }
        return null;
    }

    @Override
    public void update() {

    }
}
