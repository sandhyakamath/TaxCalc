package com.godel.engine.command;

import com.godel.engine.ComputationContext;

public interface IComputationCommand {
    boolean preExecute(ComputationContext context);
    boolean execute(ComputationContext context);
    boolean postExecute(ComputationContext context);
}
