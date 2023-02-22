package app.ui.gui.utils;

/**
 * Class to create a pair of key and value.
 *
 * @param <K> The type of the first attribute.
 * @param <V> The type of the second attribute.
 */
public class Pair<K, V> {

    /**
     * This attribute represents the key of the value.
     */
    private K key;

    /**
     * This attribute represents the value.
     */
    private V value;

    /**
     * Builds a Pair instance receiving the key and respective value.
     *
     * @param key   The key value.
     * @param value The value.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Method to return the key.
     *
     * @return The key.
     */
    public K getKey() {
        return key;
    }

    /**
     * Method to return the value.
     *
     * @return The value.
     */
    public V getValue() {
        return value;
    }

    /**
     * Method that represents the object in a String format.
     *
     * @return The String representing the object.
     */
    @Override
    public String toString() {
        return (String) this.value;
    }
}
