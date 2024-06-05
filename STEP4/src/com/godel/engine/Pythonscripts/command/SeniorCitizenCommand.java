package com.godel.engine.pythonScripts.command;

import com.godel.common.ComputationContext;
import com.godel.engine.pythonScripts.PyInvoker;

public class SeniorCitizenCommand extends BaseComputationCommand {
    @Override
    public boolean execute(ComputationContext context) {
        String script = "SeniorCitizen.py";
        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory + "/STEP4/src/com/godel/engine/pythonScripts/scripts/"+ script;
        return PyInvoker.Invoke(fileName, context);
    }
}
