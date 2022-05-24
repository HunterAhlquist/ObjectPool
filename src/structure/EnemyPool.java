package structure;

public class EnemyPool extends ObjectPool<Enemy> {

    @Override
    Enemy create() {
        return null;
    }


    public Enemy resetObject(Enemy element)
    {
        element.reset();
        return element;
    }
}
