package com.godel.plugin;

import com.godel.engine.javaClasses.ObjectFactory;
import com.godel.engine.JNI.JNIObjectFactory;
import com.godel.engine.JNI.command.JNIComputationCommand;
import com.godel.engine.SLANGScripts.SLANGObjectFactory;
import com.godel.engine.SLANGScripts.command.SLANGComputationCommand;
import com.godel.engine.javaClasses.command.IComputationCommand;
import com.godel.engine.pythonScripts.PythonObjectFactory;
import com.godel.engine.pythonScripts.ScriptHandler;
import com.godel.engine.pythonScripts.command.PythonComputationCommand;

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

    public JNIComputationCommand getJNIInstance(String archetype) {
        return JNIObjectFactory.getInstance(archetype, plugins);
    }

    public PythonComputationCommand getPythonScriptInstance(String archetype) {
        return PythonObjectFactory.getInstance(archetype, plugins);
    }


    public SLANGComputationCommand getSLANGScriptInstance(String archetype) {
        return SLANGObjectFactory.getInstance(archetype, plugins);
    }
}
