package com.godel.engine.SLANGScripts.command;

import com.godel.common.ComputationContext;

public interface SLANGComputationCommand {
    boolean preExecute(ComputationContext context);
    boolean execute(ComputationContext context);
    boolean postExecute(ComputationContext context);
}
