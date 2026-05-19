package com.amazori.hw13;
import java.util.HashMap;

public class DupCount <T>
{
    private HashMap<T, Integer> map;

    public DupCount() {
        this.map = new HashMap<>();
    }

    public void add(T key) {
        if (map.containsKey(key))
        {
            int currentDuplications = map.get(key);
            map.put(key, currentDuplications + 1);
        }
        else
            map.put(key, 1);
    }

    public int remove(T key) {
        if (!map.containsKey(key))
            return 0;

        int currentDuplications = map.get(key);

        if (currentDuplications == 1) {
            map.remove(key);
            return 0;
        } else {
            currentDuplications -= 1;
            map.put(key, currentDuplications);
            return currentDuplications;
        }
    }

    public T getMaxDup() {
        if (map.isEmpty())
            return null;

        T maxKey = null;
        int maxValue = 0;

        for (T key : map.keySet()) {
            int currentKeyCount = map.get(key);
            if (currentKeyCount > maxValue) {
                maxValue = currentKeyCount;
                maxKey = key;
            }
        }
        return maxKey;
    }

    //Additional function
    public HashMap<T, Integer> getMap()
    {
        return this.map;
    }
}