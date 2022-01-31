package com.micu.enums;

public enum PG {
    PAYTM(true);
    private final boolean redirectRequired;
    PG(boolean redirectRequired){
        this.redirectRequired = redirectRequired;
    }

    public boolean isRedirectRequired(){
        return redirectRequired;
    }
}
