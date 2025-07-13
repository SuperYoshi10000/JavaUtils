package local.ytk.util.awt;

import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowEventTarget {
    private final Window window;
    private final KeyListener keyTracker = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            pressedKeys.put(e.getKeyCode(), true);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            pressedKeys.put(e.getKeyCode(), false);
        }
    };
    
    public WindowEventTarget(Window window) {
        this.window = window;
    }
    public void startKeyTracker() {
        window.addKeyListener(keyTracker);
    }
    public void stopKeyTracker() {
        window.removeKeyListener(keyTracker);
        pressedKeys.clear();
    }
    
    
    private final Int2BooleanMap pressedKeys = new Int2BooleanOpenHashMap();
    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.getOrDefault(keyCode, false);
    }
    
    public void onKeyTyped(KeyTypedListener listener) {
        window.addKeyListener(listener);
    }
    public void onKeyPressed(KeyPressedListener listener) {
        window.addKeyListener(listener);
    }
    public void onKeyReleased(KeyReleasedListener listener) {
        window.addKeyListener(listener);
    }
    
    public void onKeyTyped(int id, KeyTypedListener listener) {
        onKeyTyped(e -> {
            if (e.getKeyCode() == id) listener.keyTyped(e);
        });
    }
    public void onKeyPressed(int id, KeyPressedListener listener) {
        onKeyPressed(e -> {
            if (e.getKeyCode() == id) listener.keyPressed(e);
        });
    }
    public void onKeyReleased(int id, KeyReleasedListener listener) {
        onKeyReleased(e -> {
            if (e.getKeyCode() == id) listener.keyReleased(e);
        });
    }
    
    @FunctionalInterface
    public interface KeyTypedListener extends java.awt.event.KeyListener {
        void keyTyped(java.awt.event.KeyEvent e);
        @Override
        default void keyPressed(java.awt.event.KeyEvent e) {}
        @Override
        default void keyReleased(java.awt.event.KeyEvent e) {}
    }
    
    @FunctionalInterface
    public interface KeyPressedListener extends java.awt.event.KeyListener {
        void keyPressed(java.awt.event.KeyEvent e);
        @Override
        default void keyTyped(java.awt.event.KeyEvent e) {}
        @Override
        default void keyReleased(java.awt.event.KeyEvent e) {}
    }
    
    @FunctionalInterface
    public interface KeyReleasedListener extends java.awt.event.KeyListener {
        void keyReleased(java.awt.event.KeyEvent e);
        @Override
        default void keyTyped(java.awt.event.KeyEvent e) {}
        @Override
        default void keyPressed(java.awt.event.KeyEvent e) {}
    }
}
