package com.godel.engine.pythonScripts;

import java.util.ArrayList;
import java.util.Dictionary;

public class ScriptHandler {
    public static String getScript(String archetype, Dictionary<String, ArrayList<String>> plugins) {
        ArrayList<String> list = plugins.get(archetype);
        if (list.isEmpty()) return null;
        return list.get(0);
    }
}
