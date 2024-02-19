/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManejoTxt;

import EDD.Lista;
import EDD.Nodo;
import EDD.NodoG;
import Grafo.Arista;
import Grafo.Grafo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class AdminTxt {
    /*
    Este metodo le permite al usuario seleccionar un archivo txt, posteoriormente lee el archivo y carga la informacion en el grafo
    
    @param usuarios, relaciones
    @ return
    */
    public File lecturaTxt(Lista ciudades, Lista comidas) throws IOException{

            // Creamos el JFileChooser
            JFileChooser jfc = new JFileChooser();

            // Abrimos el cuadro de diálogo de selección de archivos
            int seleccion = jfc.showOpenDialog(null);

            // Si el usuario seleccionó un archivo
            if (seleccion == JFileChooser.APPROVE_OPTION) {

                // Obtenemos el archivo seleccionado
                File archivo = jfc.getSelectedFile();

                // Creamos un flujo de entrada para leer el archivo
                FileReader fr = new FileReader(archivo);
                
                JOptionPane.showMessageDialog(null, "Este programa necesita guardar en el archivo los datos cargados en memoria");
                
                // Leemos los datos del archivo
                BufferedReader br = new BufferedReader(fr);
                String linea;
                int contador =0;
                while ((linea = br.readLine()) != null) {

                    // Si la línea es un usuario
                    if (linea.startsWith("ciudad")) {
                        contador=1;
                        ciudades.insertFinal(linea); 
                        
                    }
                    if (linea.startsWith("aristas")) {
                        contador=2;
                        comidas.insertFinal(linea);
                    }
                    ///if (linea.startsWith("@")) {
                        ///if (contador==1){
                            // Agregamos la línea a la lista de usuarios
                           // ciudades.insertFinal(linea); 
                            
                           // }
                        //if (contador ==2){
                            // Agregamos la línea a la lista de relaciones
                           // comidas.insertFinal(linea);
                        //} 
                        //}
                }

                // Cerramos el flujo de entrada
                br.close();
                return archivo;
            }
            JOptionPane.showMessageDialog(null, "El archivo seleccionado no es compatible");
            return null;
    }
    
    /*
    Este metodo sobrescribe el archivo seleccionado en el metodo de lectura, escribiendo los cambios realizados en el grafo respecto a los usarios y las relaciones
    
    */
    public void escrituraTxt(Grafo grafo, File archivo){
        String textoTxt=""; //string que va a sobreescribir el archivo
        Lista ListaTexto=new Lista();
        ListaTexto.insertFinal("ciudad");
        NodoG pointer =grafo.getNodos().getHead();
        while(pointer!=null){
            ListaTexto.insertFinal(pointer.getCiudad().getId()); //se insertan todos los nombres de usuario en la lista
            pointer=pointer.getNext();
        }
        ListaTexto.insertFinal("aristas");
        Arista pointer1 =grafo.getAristas().getHead();
        while(pointer1!=null){
            String texto1=pointer1.getInicio().getCiudad().getId();
            String texto2=pointer1.getObjetivo().getCiudad().getId();
            String textoArista=texto1+", "+texto2;
            ListaTexto.insertFinal(textoArista); //se insertan todos las relaciones en la lista
            pointer1=pointer1.getNext();
        }
        Nodo nodo=ListaTexto.getHead();
        for(int i=0; i<ListaTexto.getLength();i++){
            textoTxt+=nodo.getElement().toString()+"\n"; //se agrega cada elemento de la lista al string con saltos de linea entre elementos
            nodo=nodo.getNext();       
            }
        
        try{
            PrintWriter pw = new PrintWriter(archivo); 
            pw.print(textoTxt); //se sobreescribe el archivo
            pw.close();
            JOptionPane.showMessageDialog(null, "Repositorio Actualizado");
        }
        catch(Exception err){
            JOptionPane.showMessageDialog(null, err);
        }
    }
    
}
