package com.godel.engine.JNI.command;

import com.godel.common.ComputationContext;

public class BaseComputationCommand implements JNIComputationCommand{
    @Override
    public boolean preExecute(ComputationContext context) {
        return true;
    }

    @Override
    public boolean execute(ComputationContext context) {
        return true;
    }

    @Override
    public boolean postExecute(ComputationContext context) {
        return true;
    }
}
