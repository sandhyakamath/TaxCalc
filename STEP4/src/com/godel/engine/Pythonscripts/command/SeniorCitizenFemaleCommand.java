package com.godel.engine.pythonScripts.command;

import com.godel.common.ComputationContext;
import com.godel.engine.pythonScripts.PyInvoker;

public class SeniorCitizenFemaleCommand extends BaseComputationCommand {
    @Override
    public boolean execute(ComputationContext context) {
        String script = "SeniorCitizenFemale.py";
        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory + "/STEP4/src/com/godel/engine/pythonScripts/scripts/"+ script;
        return PyInvoker.Invoke(fileName, context);
    }
}
