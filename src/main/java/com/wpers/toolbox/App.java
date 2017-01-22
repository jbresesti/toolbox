package com.wpers.toolbox;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Procesando archivos..." );
        
        File directory = new File(".");
        //get all the files from a directory
        File[] fList = directory.listFiles();
    
        for (File file : fList){
            if (file.isFile()){
                System.out.println("Procesar " + file.getName());
                procesar(file.getName());
            }
        }
    }

	private static void procesar(String name) {
		// TODO recorrer el archivo.
		// TODO procesar cada linea acumulando en estructuras
		// TODO grabar reporte y archivos en out
		// TODO implementar observer para procesar cada linea
	}
}
