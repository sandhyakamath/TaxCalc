package com.godel.engine.classes.command;

import com.godel.common.ComputationContext;

public interface IComputationCommand {
    boolean preExecute(ComputationContext context);
    boolean execute(ComputationContext context);
    boolean postExecute(ComputationContext context);
}
