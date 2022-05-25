package structure;

import driver.Main;

public class EnemyPool extends ObjectPool<Enemy> {

    @Override
    Enemy create() {
        return new Enemy("Test Enemy #" + allSize());
    }


    public Enemy resetObject(Enemy enemy) {
        enemy.reset();
        return enemy;
    }
}
