package com.godel.engine.command;

import com.godel.engine.ComputationContext;

public class BaseComputationCommand implements IComputationCommand{

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
