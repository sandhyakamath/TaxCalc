package com.godel.common;

import com.godel.engine.JNI.command.JNIComputationCommand;
import com.godel.engine.SLANGScripts.command.SLANGComputationCommand;
import com.godel.engine.javaClasses.command.IComputationCommand;
import com.godel.engine.pythonScripts.command.PythonComputationCommand;
import com.godel.plugin.PluginManager;

public class CommandDispatcher {

    private static PluginManager getPluginManager() {
        String currentDirectory = System.getProperty("user.dir");
        PluginManager manager = new PluginManager(currentDirectory + "/STEP4/src/NativePlugin.xml");
        return manager;
    }
    public static boolean dispatch(ComputationContext context, String archetype) {
        PluginManager manager = getPluginManager();
        String type = manager.getPluginType();
        if (type.equalsIgnoreCase("JavaPlugin"))  {
           IComputationCommand cmd = manager.getInstance(archetype);
            if (cmd.preExecute(context))
                if (cmd.execute(context))
                    return cmd.postExecute(context);
        } else if (type.equalsIgnoreCase("PythonPlugin")) {
            PythonComputationCommand cmd = manager.getPythonScriptInstance(archetype);
            if (cmd.preExecute(context))
                if (cmd.execute(context))
                    return cmd.postExecute(context);
        } else if (type.equalsIgnoreCase("SLANGPlugin")) {
            SLANGComputationCommand cmd = manager.getSLANGScriptInstance(archetype);
            if (cmd.preExecute(context))
                if (cmd.execute(context))
                    return cmd.postExecute(context);
        } else if (type.equalsIgnoreCase("NativePlugin")) {
            JNIComputationCommand cmd = manager.getJNIInstance(archetype);
            if (cmd.preExecute(context))
                if (cmd.execute(context))
                    return cmd.postExecute(context);
        }
            return false;
    }
}
