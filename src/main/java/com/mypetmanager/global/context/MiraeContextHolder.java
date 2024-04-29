package com.mypetmanager.global.context;

public class MiraeContextHolder {
    private static final ThreadLocal<MiraeContext> contextHolder = new ThreadLocal<>();

    public static MiraeContext get() {
        if (contextHolder.get() == null) {
            contextHolder.set(new MiraeContext());
        }
        return contextHolder.get();
    }
    
    public static void clear(){
        contextHolder.remove();
    }

}
