import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class BiMap<K, V> {
    private HashMap<K, V> map1;
    private HashMap<V, K> map2;

    //rebuilds the 2nd map based on the first map
    private void parityRebuild() {
        map2.clear();
        for (Map.Entry<K,V> p : map1.entrySet()) {
            map2.put(p.getValue(), p.getKey());
        }
    }

    private BiMap(HashMap<K, V> map1, HashMap<V, K> map2) {
        this.map1 = map1;
        this.map2 = map2;
    }

    public BiMap() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }

    public BiMap(int initialCapacity) {
        map1 = new HashMap<>(initialCapacity);
        map2 = new HashMap<>(initialCapacity);
    }

    public BiMap(int initialCapacity, float loadFactor) {
        map1 = new HashMap<>(initialCapacity, loadFactor);
        map2 = new HashMap<>(initialCapacity, loadFactor);
    }

    public BiMap(Map<? extends K,? extends V> m) {
        map1 = new HashMap<>(m);
        for (Map.Entry<K, V> p : map1.entrySet()) {
            map2.put(p.getValue(), p.getKey());
        }
    }

    public BiMap<V, K> inverse() {
        return new BiMap<V, K>(map2, map1);
    }


    public void clear() {
        map1.clear();
        map2.clear();
    }

    public boolean containsKey(K key) {
        return map1.containsKey(key);
    }

    public boolean containsValue(V value) {
        return map1.containsValue(value);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return map1.entrySet();
    }

    public void forEach(BiConsumer<? super K,? super V> action) {
        map1.forEach(action);
    }

    public V get(K element) {
        return map1.get(element);
    }

    public V getOrDefault(K element, V defaultValue) {
        return map1.getOrDefault(element, defaultValue);
    }

    public boolean isEmpty() {
        return map1.isEmpty();
    }

    public Set<K> keySet() {
        return map1.keySet();
    }

    public V merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction) {
        V element = map1.merge(key, value, remappingFunction);
        parityRebuild();
        return element;
    }

    public V put(K key, V value) {
        map2.put(value, key);
        return map1.put(key, value);
    }

    //wrong
    public void putAll(Map<? extends K,? extends V> m) {
        map1.putAll(m);
        for (Map.Entry<K, V> p : map1.entrySet()) {
            map2.put(p.getValue(), p.getKey());
        }
    }

    //TODO: Dangerous right now. Value can be removed in one map and not the other if there is an error.
    public V remove(K key) {
        V element = map1.remove(key);
        map2.remove(element);
        return element;
    }

    //TODO: Dangerous right now. Value can be removed in one map and not the other if there is an error.
    public boolean remove(K key, V value) {
        return map1.remove(key, value) && map2.remove(value, key);
    }

    //TODO: Dangerous right now. Value can be replaced in one map and not the other if there is an error.
    public V replace(K key, V value) {
        V element = map1.replace(key, value);
        map2.replace(element, key);
        return element;
    }

    //TODO: Dangerous right now. Value can be replaced in one map and not the other if there is an error.
    public boolean replace(K key, V oldValue, V newValue) {
        if (map1.replace(key, oldValue, newValue)) {
            map2.remove(oldValue);
            map2.put(newValue, key);
            return true;
        } else {
            return false;
        }
    }

    public void replaceAll(BiFunction<? super K,? super V,? extends V> function) {
        map1.replaceAll(function);
        parityRebuild();
    }

    public int size() {
        return map1.size();
    }

    public Collection<V> values() {
        return map1.values();
    }
}
