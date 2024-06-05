package com.godel.engine.JNI.command;

import com.godel.common.ComputationContext;
import com.godel.engine.JNI.SeniorCitizenJNICaller;

public class SeniorCitizenCommand extends BaseComputationCommand {
    @Override
    public boolean execute(ComputationContext context) {
        SeniorCitizenJNICaller caller = new SeniorCitizenJNICaller();
        caller.nativeMethodCall(context);
        return true;
    }

}
