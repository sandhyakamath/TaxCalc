package com.godel.engine.SLANGScripts;

public enum TYPE {
    TYPE_ILLEGAL(-1),
    TYPE_NUMERIC(0),
    TYPE_BOOL(1),
    TYPE_STRING(2);
    private int info;

    private TYPE(int info) {
        this.info = info;
    }

    public int getType() {
        return this.info;
    }
}
