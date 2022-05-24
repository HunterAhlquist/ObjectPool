package structure;

import java.util.Random;

public class Enemy implements PooledObject<Enemy>, EnemyActions {
    private static final float INSTANTIATE_TIME = 2;
    private static final float REUSE_TIME = 1;
    private float currentDelay;

    private String name;
    private int health;


    public void reset() {
        Random rand = new Random();
        health = rand.nextInt(500);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            death();
        }
    }

    public void death() {
        System.out.println("VICTORY ACHIEVED - " + name + " has fallen!");
    }
}
