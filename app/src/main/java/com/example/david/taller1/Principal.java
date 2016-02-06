package com.example.david.taller1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;

public class Principal extends AppCompatActivity {

    ListView vistaLista;
    List<String> tareas;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        // Log.wtf("APP", " On create ");
        // Ojo!
        tareas = ManejadorArchivos.getInstance(this).leerArchivo();
        vistaLista = (ListView) findViewById(R.id.list_view_calls);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tareas);
        vistaLista.setAdapter(adapter);
        vistaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tareas.remove(position);
                adapter.notifyDataSetChanged();
                ManejadorArchivos.getInstance(getBaseContext()).escribirArchivo(tareas);
                return false;
            }
        });
    }



    public void agregarTarea(View v){
        EditText text =(EditText) findViewById(R.id.txt_tarea);
        String tarea = text.getText().toString();
        if(text != null && !tarea.isEmpty()) {
            adapter.add(tarea);
        }
        text.setText("");
        ManejadorArchivos.getInstance(this).escribirArchivo(tareas);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.wtf("App", "On pause");
    }
    @Override
    protected void onStart(){
        super.onStart();
        //Log.v("App", "On start");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.v("App", "On destroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.wtf("App", " ON stop");
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Log.wtf("App", " on resume ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.wtf("App", "On restart");
        //tareas =  ManejadorArchivos.getInstance(this).leerArchivo();
        //adapter.notifyDataSetChanged();
    }

}
