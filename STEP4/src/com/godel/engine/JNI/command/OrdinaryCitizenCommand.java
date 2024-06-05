package com.godel.engine.JNI.command;

import com.godel.common.ComputationContext;
import com.godel.engine.JNI.OrdinaryCitizenJNICaller;

public class OrdinaryCitizenCommand extends BaseComputationCommand {
    @Override
    public boolean execute(ComputationContext context) {
        OrdinaryCitizenJNICaller caller = new OrdinaryCitizenJNICaller();
        caller.nativeMethodCall(context);
        return true;
    }

}
