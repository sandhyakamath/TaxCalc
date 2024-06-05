package com.godel.engine.pythonScripts.command;

import com.godel.common.ComputationContext;

public interface PythonComputationCommand {
    boolean preExecute(ComputationContext context);
    boolean execute(ComputationContext context);
    boolean postExecute(ComputationContext context);
}
