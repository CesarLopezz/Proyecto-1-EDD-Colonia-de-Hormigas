/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Programa;

import Grafo.Grafo;
import Interfaces.Menu;
import java.io.File;

/**
 *
 * @author cesar
 */
public class App {
    
    public App() {
    }

    public void inicio(){
        Grafo grafo= new Grafo();
        File archivo= null;
        Menu ventana = new Menu(grafo, archivo);
        ventana.setVisible(true);
    
    }
}
