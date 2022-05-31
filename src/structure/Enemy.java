package structure;

import driver.Main;
import interfaces.EnemyActions;
import interfaces.ResettablePoolObject;

import java.util.Random;

/**
 * Enemy class that can take damage, die, and be reset
 *
 * @author Hunter Ahlquist and Mark Mendoza
 * @version 1.0
 */
public class Enemy implements ResettablePoolObject<Enemy>, EnemyActions {
    private static boolean debugMode = false;
    private static boolean createMode = false;
    private static final float INSTANTIATE_TIME = 2;
    private static final float REUSE_TIME = 1;
    private final String name;
    private int health;
    private boolean dead;

    /**
     * Instantiates a new enemy with given name
     * @param name Name of the enemy
     */
    public Enemy(String name) {
        Random rand = new Random();
        this.name = name;
        health = rand.nextInt(200);
        dead = false;
        if (debugMode) {
            System.out.println("DEBUG: Creating new enemy...");
        }
        Main.waitForSeconds(Enemy.INSTANTIATE_TIME);
    }

    /**
     * Simulates reusing or instantiation time, to have a fresh object to use again
     */
    public void reset() {
        Random rand = new Random();
        health = rand.nextInt(200);
        dead = false;
        if (debugMode && !createMode) {
            System.out.println("DEBUG: Recycling fallen enemy...");
        } else if (debugMode) {
            System.out.println("DEBUG: Creating new enemy...");
        }
        if (createMode) {
            Main.waitForSeconds(Enemy.INSTANTIATE_TIME);
        } else {
            Main.waitForSeconds(Enemy.REUSE_TIME);
        }
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Enemy health has been reduced to " + getHealth());
        if (health <= 0) {
            death();
        }
    }

    /**
     * @return if dead return true, else return false
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Set enemy to dead and output enemy death to the console
     */
    public void death() {
        dead = true;
        System.out.println(name + " has fallen!");
    }

    /**
     * Return health status of the enemy
     * @return remaining health of the enemy
     */
    public int getHealth() {
        return health;
    }

    /**
     * Return name of the enemy
     * @return Name of the enemy
     */
    public String getName() {
        return name;
    }

    /**
     * Toggles debug mode on/off
     */
    public static void toggleDebugMode()
    {
        debugMode = !debugMode;
    }

    /**
     * Toggles create mode on/off
     */
    public static void toggleCreateMode()
    {
        createMode = !createMode;
    }
}
