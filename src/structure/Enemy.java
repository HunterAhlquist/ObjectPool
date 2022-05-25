package structure;

import driver.Main;

import java.util.Random;

public class Enemy implements PooledObject<Enemy>, EnemyActions {
    public static final float INSTANTIATE_TIME = 0;
    public static final float REUSE_TIME = 0;
    private String name;
    private int health;
    private boolean dead;

    public Enemy(String name) {
        Random rand = new Random();
        this.name = name;
        health = rand.nextInt(500);
        dead = false;
        System.out.println("Creating new enemy...");
        Main.waitForSeconds(Enemy.INSTANTIATE_TIME);
    }

    public void reset() {
        Random rand = new Random();
        health = rand.nextInt(500);
        System.out.println("Recycling fallen enemy...");
        Main.waitForSeconds(Enemy.REUSE_TIME);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            death();
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void death() {
        dead = true;
        System.out.println(name + " has fallen!");
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

}
