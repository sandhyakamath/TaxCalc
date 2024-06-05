package com.godel.engine.JNI.command;

import com.godel.common.ComputationContext;
import com.godel.engine.JNI.SeniorCitizenFemaleJNICaller;

public class SeniorCitizenFemaleCommand extends BaseComputationCommand {
    @Override
    public boolean execute(ComputationContext context) {
        SeniorCitizenFemaleJNICaller caller = new SeniorCitizenFemaleJNICaller();
        caller.nativeMethodCall(context);
        return true;
    }
}
