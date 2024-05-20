package com.godel.common;

import com.godel.engine.classes.command.IComputationCommand;
import com.godel.engine.scripts.PyInvoker;
import com.godel.engine.scripts.ScriptHandler;
import com.godel.plugin.PluginManager;

public class CommandDispatcher {

    private static PluginManager getPluginManager() {
        String currentDirectory = System.getProperty("user.dir");
        PluginManager manager = new PluginManager(currentDirectory + "/STEP2/TaxCalculator/src/pluginScript.xml");
        return manager;
    }
    public static boolean dispatch(ComputationContext context, String archetype) {
        PluginManager manager = getPluginManager();
        String type = manager.getPluginType();
        if (type.equalsIgnoreCase("class"))  {
           IComputationCommand cmd = manager.getInstance(archetype);
            if (cmd.preExecute(context))
                if (cmd.execute(context))
                    return cmd.postExecute(context);
        } else if (type.equalsIgnoreCase("script")) {
            String location = manager.getScript(archetype);
            String currentDirectory = System.getProperty("user.dir");
            String fileName = currentDirectory + "/STEP2/TaxCalculator/src/"+ location;
            return PyInvoker.Invoke(fileName, context);
        }

        return false;
    }
}
