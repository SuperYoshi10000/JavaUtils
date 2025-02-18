package local.ytk.util.collection;

import com.google.common.collect.BiMap;
import local.ytk.util.annotation.Static;
import org.apache.commons.collections4.BidiMap;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Static
public class Entries {
    private Entries() {}
    
    public static <K1, K2, V1, V2> Map.Entry<K2, V2> map(Map.Entry<K1, V1> entry, Function<K1, K2> keyMap, Function<V1, V2> valueMap) {
        return Map.entry(keyMap.apply(entry.getKey()), valueMap.apply(entry.getValue()));
    }
    public static <K1, K2, V> Map.Entry<K2, V> mapKey(Map.Entry<K1, V> entry, Function<K1, K2> keyMap) {
        return Map.entry(keyMap.apply(entry.getKey()), entry.getValue());
    }
    public static <K, V1, V2> Map.Entry<K, V2> mapValue(Map.Entry<K, V1> entry, Function<V1, V2> valueMap) {
        return Map.entry(entry.getKey(), valueMap.apply(entry.getValue()));
    }
    
    public static <K, V> Collector<Map.Entry<K, V>, ?, Map<K, V>> entriesToMap() {
        return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);
    }
    public static <K1, K2, V1, V2> Collector<Map.Entry<K1, V1>, ?, Map<K2, V2>> entriesToMap(Function<K1, K2> keyMap, Function<V1, V2> valueMap) {
        return Collectors.toMap(e -> keyMap.apply(e.getKey()), e -> valueMap.apply(e.getValue()));
    }
    
    static <K, V> K findKey(Map<K, V> map, V value) {
        if (map instanceof BiMap<K, V> biMap) return biMap.inverse().get(value);
        if (map instanceof BidiMap<K, V> bidiMap) return bidiMap.getKey(value);
        return CollectionUtils.matchFirst(map.entrySet(), e -> e.getValue().equals(value), Map.Entry::getKey);
    }
}
