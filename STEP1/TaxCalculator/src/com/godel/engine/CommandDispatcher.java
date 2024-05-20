package com.godel.engine;

import com.godel.engine.command.IComputationCommand;
import com.godel.plugin.ObjectFactory;

public class CommandDispatcher {

    private static ObjectFactory get() {
        String currentDirectory = System.getProperty("user.dir");
        ObjectFactory obj = new ObjectFactory(currentDirectory + "/STEP1/TaxCalculator/src/com/godel/UI/plugins.xml");
        return obj;
    }
    public static boolean dispatch(ComputationContext context, String archetype) {
        IComputationCommand cmd = get().getInstance(archetype);
        if (cmd.preExecute(context))
            if (cmd.execute(context))
                return cmd.postExecute(context);
        return false;
    }
}
