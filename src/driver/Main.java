package driver;

import structure.Enemy;
import structure.EnemyPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int KILL_COUNT = 10;
    private static final int MAX_ATTACK_POWER = 100;
    private static final int MAX_FIGHTING = 5;
    private static int currentKillCount = 0;
    private static final float ACTION_DELAY = 1;
    private static EnemyPool enemyPool;

    //List of used enemies
    private static List<Enemy> currentlyFighting;

    public static void main(String[] args) {
        currentlyFighting = new ArrayList<>();
        enemyPool = new EnemyPool();
        while (currentKillCount < KILL_COUNT) {
            System.out.println(System.lineSeparator() +
                    System.lineSeparator());
            updateGame();
            System.out.println("Kill count: " + currentKillCount);
        }
    }

    private static void updateGame() {
        fillFightQueue();
        //waitForSeconds(ACTION_DELAY);
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
        Enemy enemyToFight = currentlyFighting.get(rand.nextInt(currentlyFighting.size() - 1));

        System.out.println("Hero attacks " + enemyToFight.getName() + " for " + damage + "HP");
        enemyToFight.takeDamage(damage);

        if (enemyToFight.isDead()) {
            currentKillCount++;
            enemyPool.resetObject(enemyToFight);
            enemyPool.makeAvailable(enemyToFight);
        }
    }

    public static void waitForSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void waitForSeconds(float seconds) {
        try {
            TimeUnit.SECONDS.sleep((long) seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void waitForSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
