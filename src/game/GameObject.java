package game;

import game.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    // Quan li doi tuong
    public static ArrayList<GameObject> objects = new ArrayList<>();

    // find
    public static <E extends GameObject> E find(Class<E> cls) {
        // luot qua mang objects
        // kiem tra tung phan tu
        // neu phan tu thoa man >> return
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if(object.getClass().isAssignableFrom(cls)) {
                return (E) object;
            }
        }
        return null;
    }

    // Dinh nghia doi tuong
    public Renderer renderer;
    public Vector2D position;
    public Vector2D velocity;

    public GameObject() {
        objects.add(this);
        this.position = new Vector2D();
        this.velocity = new Vector2D();
    }

    public void render(Graphics g) {
        if (renderer != null){
            renderer.render(g, this);
        }
    }

    public void run() {
        position.add(velocity.x, velocity.y);
    }
}
