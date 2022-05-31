package driver;

import Helper.Console;
import structure.Enemy;
import structure.EnemyPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Driver class
 *
 * @author Hunter Ahlquist and Mark Mendoza
 * @version 1.0
 */
public class Main {
    private static final int KILL_COUNT = 10;
    private static final int MAX_ATTACK_POWER = 100;
    private static final int MAX_FIGHTING = 5;
    private static int currentKillCount = 0;

    //List of used enemies
    private static EnemyPool enemyPool;
    private static List<Enemy> currentlyFighting;

    /**
     * Main class
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Object Pool Pattern demo");
        System.out.println("POWERED BY: Josh's Input ENGINE(tm)");
        if (Console.getBoolean("Show debug info?")) {
            Enemy.toggleDebugMode();
        }
        if (Console.getBoolean("Create enemies rather than reuse?")) {
            Enemy.toggleCreateMode();
        }

        long startTime = System.nanoTime();
        System.out.println("Loading...");
        currentlyFighting = new ArrayList<>();
        enemyPool = new EnemyPool();
        while (currentKillCount < KILL_COUNT) {
            updateGame();
            System.out.println("Kill count: " + currentKillCount);
            System.out.println(System.lineSeparator());
        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Elapsed time: " + TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " seconds.");
    }

    private static void updateGame() {
        fillFightQueue();
        if (currentlyFighting.size() > 0)
        {
            attackRandomEnemy();
        }
    }

    private static void fillFightQueue() {
        while (currentlyFighting.size() <= MAX_FIGHTING) {
            Enemy receivedEnemy = enemyPool.makeActive();
            if (receivedEnemy != null)
            {
                currentlyFighting.add(receivedEnemy);
            } else
            {
                break;
            }
        }
    }

    private static void attackRandomEnemy() {
        Random rand = new Random();
        int damage = rand.nextInt(MAX_ATTACK_POWER);
        Enemy enemyToFight = getRandomEnemy(currentlyFighting);

        System.out.println("Hero attacks " + enemyToFight.getName() + " for " + damage + "HP");
        enemyToFight.takeDamage(damage);

        if (enemyToFight.isDead()) {
            currentKillCount++;
            enemyPool.resetObject(enemyToFight);
            enemyPool.makeAvailable(enemyToFight);
        }
    }

    private static Enemy getRandomEnemy(List<Enemy> collection) {
        Random rand = new Random();
        return collection.get(rand.nextInt(collection.size() - 1));
    }

    /**
     * Uses Thread.sleep to stop the program for a specified amount of time
     * @param seconds time to stop in seconds
     */
    public static void waitForSeconds(float seconds) {
        try {
            Thread.sleep((long) seconds * (long) 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
