package generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        storage.replace(id, model);
        T rsl = storage.get(id);
        return rsl != null && rsl.equals(model);
    }

    @Override
    public boolean delete(String id) {
        storage.remove(id);
        return storage.get(id) == null;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
