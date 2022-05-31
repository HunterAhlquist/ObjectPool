package structure;

/**
 * An object pool based class for the Enemy class
 *
 * @author Hunter Ahlquist and Mark Mendoza
 * @version 1.0
 */
public class EnemyPool extends ObjectPool<Enemy> {

    @Override
    Enemy create() {
        return new Enemy("Test Enemy #" + allSize());
    }

    /**
     * Reset the enemy object as if it was re-instantiated
     * @param enemy the enemy object to reset
     */
    public void resetObject(Enemy enemy) {
        enemy.reset();
    }
}
