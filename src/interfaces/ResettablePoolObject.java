package interfaces;

/**
 * Optional interface for object pools that need to be reset
 * @param <T> The pooled object type
 *
 * @author Hunter Ahlquist and Mark Mendoza
 * @version 1.0
 */
public interface ResettablePoolObject<T> {
    /**
     * Resets the object to default or reusable state
     */
    void reset();
}
