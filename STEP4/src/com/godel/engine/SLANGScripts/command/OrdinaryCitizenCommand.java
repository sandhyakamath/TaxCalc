package com.godel.engine.SLANGScripts.command;

import com.godel.common.ComputationContext;
import com.godel.engine.SLANGScripts.SLANGAdapter;

public class OrdinaryCitizenCommand extends BaseComputationCommand {
    @Override
    public boolean execute(ComputationContext context) {
        String script = "OrdinaryCitizen.sl";
        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory + "/STEP4/src/com/godel/engine/SLANGScripts/scripts/" + script;
        return SLANGAdapter.executeScript(fileName, context);
    }
}
