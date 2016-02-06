package com.example.david.taller1;

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 2/5/2016.
 */
public class ManejadorArchivos {
    public final static String NOMBRE_ARCHIVO="archivo.txt";
    private static ManejadorArchivos instancia = new ManejadorArchivos();
    protected Context context;
    private ManejadorArchivos(){}

    public static ManejadorArchivos getInstance(Context contexto){
        if(instancia == null){
            instancia = new ManejadorArchivos();
        }
        instancia.context = contexto;
        return instancia;
    }

    public List<String> leerArchivo(){
        File directorio = instancia.context.getFilesDir();
        File archivoTareas = new File(directorio, NOMBRE_ARCHIVO);
        try{
            return FileUtils.readLines(archivoTareas);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void escribirArchivo(List<String> tareas){
        File directorio = instancia.context.getFilesDir();
        File archivoTareas = new File(directorio, NOMBRE_ARCHIVO);
        try{
            FileUtils.writeLines(archivoTareas, tareas);
        } catch (IOException e) {
            Log.wtf("Error", "No se pudo escribir el archivo");
        }
    }
}
