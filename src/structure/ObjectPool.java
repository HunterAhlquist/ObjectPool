package structure;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic object pool class
 * @param <T> The object that will be pooled.
 *
 * @author Hunter Ahlquist and Mark Mendoza
 * @version 1.0
 */
abstract class ObjectPool<T> {
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_OBJECT_COUNT = 20;
    private final List<T> available;
    private final List<T> active;

    ObjectPool()
    {
        available = new ArrayList<>();
        active = new ArrayList<>();

        for (int i = 0; i < INITIAL_POOL_SIZE; i++)
        {
            available.add(create());
        }
    }

    /**
     * @return Return the object instantiated
     */
    abstract T create();


    /**
     * Gets an object from the pool and puts it into an "in-use" state.
     * If no objects are available, it will create one new object, only if it would be under the limit
     * @return The object returned, else null if max reached
     */
    public T makeActive() {
        //Check empty
        if (available.isEmpty()) {
            if (allSize() < MAX_OBJECT_COUNT) {
                available.add(create());
            } else {
                return null;
            }
        }

        T object = available.stream().findFirst().get();
        available.remove(object);
        active.add(object);
        return object;
    }

    /**
     * Make a pooled object available for use
     * @param oldObject the object to be recycled
     */
    public void makeAvailable(T oldObject) {
        active.remove(oldObject);
        available.add(oldObject);
    }

    /**
     * The amount of objects that are available to be used
     * @return number of available objects
     */
    public int availableSize() {
        return available.size();
    }

    /**
     * The amount of objects that are being used
     * @return number of objects actively being used
     */
    public int activeSize() {
        return active.size();
    }

    /**
     * The total amount of objects in the pool
     * @return the total number of objects
     */
    public int allSize() {
        return active.size() + available.size();
    }
}
