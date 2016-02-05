package com.example.david.taller1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class ReceptorLlamadas extends BroadcastReceiver {

    protected String previousState = null;
    public ReceptorLlamadas() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String estado = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        Log.wtf("App", "Entra al receiver " + estado);
        if( ! estado.equals(previousState) && estado.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            previousState = estado;
            String numeroTelefono = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            List<String> tareas = ManejadorArchivos.leerArchivo();
            tareas.add("me llamó " + numeroTelefono);
            ManejadorArchivos.getInstance(context).escribirArchivo(tareas);
            Toast.makeText(context, "Llamada! de  " + numeroTelefono, Toast.LENGTH_SHORT).show();
            Intent newIntent = new Intent(context, Principal.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newIntent);
        }else{
            previousState = estado;
        }
    }
}
