package structure;

import java.util.ArrayList;
import java.util.List;

abstract class ObjectPool<T> {
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_OBJECT_COUNT = 20;
    private final List<T> available;
    private final List<T> active;

    ObjectPool()
    {
        available = new ArrayList<>();
        active = new ArrayList<>();

        //add initial objects, up to initial pool size
        for (int i = 0; i < INITIAL_POOL_SIZE; i++)
        {
            available.add(create());
        }
    }

    abstract T create();


    /**
     * Gets an object from the pool and puts it into an "in-use" state.
     * If no objects are available, it will create one new object, only if it would be under the limit
     * @return The object returned, else null if max reached
     */
    public T makeActive() {
        //Check empty
        if (available.isEmpty()) {
            //System.out.println("available is empty");
            //if there is room to create more objects
            if (allSize() < MAX_OBJECT_COUNT) {
                //System.out.println("Less than max objects, creating new object...");
                available.add(create());
            } else {
                //System.out.println("Max reached, return null");
                return null;
            }
        }

        T object = available.stream().findFirst().get();
        available.remove(object);
        active.add(object);
        return object;
    }
    public void makeAvailable(T oldObject) {
        active.remove(oldObject);
        available.add(oldObject);
    }

    public int availableSize() {
        return available.size();
    }
    public int activeSize() {
        return active.size();
    }
    public int allSize() {
        return active.size() + available.size();
    }
}
