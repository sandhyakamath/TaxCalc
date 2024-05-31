package com.godel.plugin;

import com.godel.engine.classes.ObjectFactory;
import com.godel.engine.classes.command.IComputationCommand;
import com.godel.engine.scripts.ScriptHandler;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class PluginManager {
    private Dictionary<String, ArrayList<String>> plugins = new Hashtable<>();
    String pluginType;

    public PluginManager(String xmlFile) {
        pluginType = XMLParser.parseDocument(xmlFile, plugins);
    }
    public String getPluginType() {
        return pluginType;
    }
    public IComputationCommand getInstance(String archetype) {
        return ObjectFactory.getInstance(archetype, plugins);
    }
    public String getScript(String archetype) {
        return ScriptHandler.getScript(archetype, plugins);
    }

}
