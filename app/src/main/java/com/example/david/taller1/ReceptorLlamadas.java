package com.example.david.taller1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class ReceptorLlamadas extends BroadcastReceiver {

    public ReceptorLlamadas() {    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        Log.wtf("Estado", state);
        CallStateManager manager = CallStateManager.getInstance(state);
        if( manager.stateChanged() && state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            String numeroTelefono = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            List<String> tareas = ManejadorArchivos.getInstance(context).leerArchivo();
            String mensaje = null;
            tareas.add(context.getString(R.string.llamada)+ " " + numeroTelefono);
            ManejadorArchivos.getInstance(context).escribirArchivo(tareas);
            Intent newIntent = new Intent(context, Principal.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newIntent);
        }

    }
}
