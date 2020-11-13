package uet.oop.bomberman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

public class Game extends Canvas implements ActionListener {
    public static BitSet traceKey = new BitSet();

    public Game() {
        addKeyListener(keyAdapter);
    }

    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            traceKey.set(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            traceKey.clear(e.getKeyCode());
        }
    };
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
