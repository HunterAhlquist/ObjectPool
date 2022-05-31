package interfaces;

/**
 * Interface for common behaviors of enemies
 *
 * @author Hunter Ahlquist and Mark Mendoza
 * @version 1.0
 */
public interface EnemyActions {

    /**
     * Applies damage to an enemy
     * @param damage amount of damage taken
     */
    void takeDamage(int damage);

    /**
     * For when the enemy dies
     */
    void death();
}
