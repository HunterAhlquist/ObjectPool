package structure;

import java.util.ArrayList;
import java.util.List;

abstract class ObjectPool<T> {
    private static final int INITIAL_POOL_SIZE = 10;
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

    public T makeActive() {
        //if available pool is empty
        if (available.size() <= 0)
        {
            available.add(create());
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

}
