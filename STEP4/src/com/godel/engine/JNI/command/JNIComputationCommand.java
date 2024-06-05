package com.godel.engine.JNI.command;

import com.godel.common.ComputationContext;

public interface JNIComputationCommand {
    boolean preExecute(ComputationContext context);
    boolean execute(ComputationContext context);
    boolean postExecute(ComputationContext context);
}
