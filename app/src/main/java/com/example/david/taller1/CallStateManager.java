package com.example.david.taller1;

import android.telephony.TelephonyManager;

/**
 * Created by david on 2/5/2016.
 */
public class CallStateManager {
    private static CallStateManager manager = new CallStateManager();
    protected String previousState;
    protected String currentState;

    private CallStateManager(){    }

    public static CallStateManager getInstance(String nextState){
        if(manager.previousState == null && manager.currentState == null){
            manager.currentState = nextState;
        }else{
            manager.previousState = manager.currentState;
            manager.currentState = nextState;
        }
        return manager;
    }

    public Boolean stateChanged(){
        return ! manager.currentState.equals( previousState );
    }

    public Boolean isLostCall(){
        if(manager.previousState != null && manager.currentState != null){
            if( manager.previousState.equals(TelephonyManager.EXTRA_STATE_RINGING)
                    && manager.currentState.equals(TelephonyManager.EXTRA_STATE_IDLE) ){
                return true;
            }
        }
        return false;
    }
}
