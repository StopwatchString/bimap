import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BiMap<T, S> {
    private HashMap<T, S> map1;
    private HashMap<S, T> map2;

    public BiMap() {
        map1 = new HashMap();
        map2 = new HashMap();
    }

    public BiMap(int initialCapacity) {
        map1 = new HashMap(initialCapacity);
        map2 = new HashMap(initialCapacity);
    }

    public BiMap(int initialCapacity, float loadFactor) {
        map1 = new HashMap(initialCapacity, loadFactor);
        map2 = new HashMap(initialCapacity, loadFactor);
    }

    //public BiMap(Map<? extends K,? extends V> m) {}

    public void clear() {
        map1.clear();
        map2.clear();
    }

    //public Object clone() {}

    public boolean containsKeyType1(T key) {
        return map1.containsKey(key);
    }

    public boolean containsKeyType2(S key) {
        return map2.containsKey(key);
    }

    public boolean containsValueType1(S value) {
        return map1.containsValue(value);
    }

    public boolean containsValueType2(T value) {
        return map2.containsValue(value);
    }

    //public Set<Map.Entry<K,V>> entrySet() {}

    //public void forEach(BiConsumer<? super K,? super V> action) {}

    public S getType1(T element) {
        return map1.get(element);
    }

    public T getType2(S element) {
        return map2.get(element);
    }

    public S getOrDefaultType1(T element, S defaultValue) {
        return map1.getOrDefault(element, defaultValue);
    }

    public T getOrDefaultType2(S element, T defaultValue) {
        return map2.getOrDefault(element, defaultValue);
    }

    public boolean isEmpty() {
        return map1.isEmpty();
    }

    public Set<T> keySetType1() {
        return map1.keySet();
    }

    public Set<S> keySetType2() {
        return map2.keySet();
    }

    //public S mergeType1(T key, S value, BiFunction<? super S,? super S,? extends S> remappingFunction) {}

    //public T mergeType2(S key, T value, BiFunction<? super T,? super T,? extends T> remappingFunction) {}

    public S putType1(T key, S value) {
        map2.put(value, key);
        return map1.put(key, value);

    }

    public T putType2(S key, T value) {
        map1.put(value, key);
        return map2.put(key, value);
    }

    //public void putAllType1(Map<? extends T,? extends S> m) {}

    //public void putAllType2(Map<? extends S,? extends T> m) {}

    public S putIfAbsentType1(T key, S value) {
        map2.putIfAbsent(value, key);
        return map1.putIfAbsent(key, value);
    }

    public T putIfAbsentType2(S key, T value) {
        map1.putIfAbsent(value, key);
        return map2.putIfAbsent(key, value);
    }

    //TODO: Dangerous right now. Value can be removed in one map and not the other if there is an error.
    public S removeType1(T key) {
        S element = map1.remove(key);
        map2.remove(element);
        return element;
    }

    //TODO: Dangerous right now. Value can be removed in one map and not the other if there is an error.
    public T removeType2(S key) {
        T element = map2.remove(key);
        map1.remove(element);
        return element;
    }

    //TODO: Dangerous right now. Value can be removed in one map and not the other if there is an error.
    public boolean removeType1(T key, S value) {
        return map1.remove(key, value) && map2.remove(value, key);
    }

    //TODO: Dangerous right now. Value can be removed in one map and not the other if there is an error.
    public boolean removeType2(S key,T value) {
        return map2.remove(key, value) && map1.remove(value, key);
    }

    //TODO: Dangerous right now. Value can be replaced in one map and not the other if there is an error.
    public S replaceType1(T key, S value) {
        S element = map1.replace(key, value);
        map2.replace(element, key);
        return element;
    }

    //TODO: Dangerous right now. Value can be replaced in one map and not the other if there is an error.
    public T replaceType2(S key, T value) {
        T element = map2.replace(key, value);
        map1.replace(element, key);
        return element;
    }

    //TODO: Dangerous right now. Value can be replaced in one map and not the other if there is an error.
    public boolean replaceType1(T key, S oldValue, S newValue) {
        if (map1.replace(key, oldValue, newValue)) {
            map2.remove(oldValue);
            map2.put(newValue, key);
            return true;
        } else {
            return false;
        }
    }

    //TODO: Dangerous right now. Value can be replaced in one map and not the other if there is an error.
    public boolean replaceType2(S key, T oldValue, T newValue) {
        if (map2.replace(key, oldValue, newValue)) {
            map1.remove(oldValue);
            map1.put(newValue, key);
            return true;
        } else {
            return false;
        }
    }

    //public void replaceAll(BiFunction<? super T,? super S,? extends S> function)

    public int size() {
        return map1.size();
    }

    public Collection<S> valuesType1() {
        return map1.values();
    }

    public Collection<T> valuesType2() {
        return map2.values();
    }
}
