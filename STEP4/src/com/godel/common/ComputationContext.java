package com.godel.common;

import java.util.Dictionary;
import java.util.Hashtable;

public class ComputationContext {
    private Dictionary<String, Object> dictionary = new Hashtable<>();
    public void put(String key, Object value) {
        dictionary.put(key, value);
    }

    public Object get(String key) {
        return dictionary.get(key);
    }

}
