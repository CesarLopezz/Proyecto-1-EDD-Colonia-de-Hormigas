/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import EDD.Lista;
import EDD.NodoG;
import Grafo.Grafo;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class Helpers {
    
    public Helpers() {
    }
    
   /*
    Divide un string en 2, la primera parte hasta que encuentre ", " y la segunda parte el resto del string
    
    @param string
    @return lista
    */
   public Lista stringSplit(String string){
        String[] partes = string.split(",");
        //System.out.println(partes);
        String parte1 = partes[0]; // parte del string anterior a ", "
        System.out.println(parte1);
        String parte2 = partes[1]; // parte del string posterior a ", "
        System.out.println(parte2);
        String parte3 = partes[2];
        //System.out.println(parte3);
        Lista lista = new Lista();
        lista.insertFinal(parte1);
        lista.insertFinal(parte2);
        lista.insertFinal(parte3);
        //lista.print();
        return lista;
   }
   
   /**
     * Verifica si el parametro es un numero entero y los castea a int
     * 
     * @param numString
     * @return
     */
    public Integer valorNumero(String numString) {
        int numero;
        try {
            numero = Integer.parseInt(numString);
            if (numero > 0) {
                return numero;

            } else {
                JOptionPane.showMessageDialog(null, "Numnero invalido1. ");
                return null;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Numnero invalido1. ");
        }

        return null;
    }
    
    /**
     * verifica si el primer caracter del id es @.
     * 
     * @param id
     * @return
     */
    public String arroba(String id) {


        if (id.matches("\\d+")) {
                return id;
        } else {
            JOptionPane.showMessageDialog(null, "Nombre de usuario invalido. ");
            return "no";
        }
    }   
    
    /**
     * verifica si el id ingresado por parametro es valido (empieza con @ y no esta siendo utilizado por otro usuario).
     * 
     * @param id, grafo
     * @return
     */
    public String verificarId(String id, Grafo grafo){
        String verificacion=arroba(id);
        String verifiacion1="no";
        if(verificacion.equalsIgnoreCase(verifiacion1)){
            return null;
            } 
        else {
        NodoG temp=grafo.searchById(id);
        if (temp!=null){
            JOptionPane.showMessageDialog(null, "Nombre de usuario invalido. ");
            return null;
        }
        else {
            return id;
             }
        }
      }
    
    /**
     * verifica si el nombre de usuario ingresado por parametro existe en el grafo
     * 
     * @param id, grafo
     * @return
     */
    public boolean ciudadValido(String id, Grafo grafo){
        NodoG nodo= grafo.searchById(id);
        if (nodo!= null){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "El usuario '' "+id+" '' no existe");
            return false;
        }
    }
    
    /**
     * verifica si el numero ingresado por parametro es valido (es realmente un numero y no esta siendo utilizado por otro usuario).
     * 
     * @param id, grafo
     * @return
     */
     public Integer verificarNumero(String numString, Grafo grafo){
         
            NodoG pointer=grafo.getNodos().getHead();
            Integer numero=Integer.parseInt(numString);
            int igual=0;
            while(pointer!=null){
                
                if (pointer.getCiudad().getNumero()==numero){
                    igual=1;
                }
                pointer=pointer.getNext();  
            }
                if (igual==0){
                return numero;
            }   
            else{
                return null;
            }
            
        }   
       
    /*
     busca el menor numero disponible(que no este utilizado par algun otro usuario)
     
     @param grafo
     @return
     */
    public Integer buscarNumero(Grafo grafo){
        int contador =0;
        boolean valido=false;
        while(valido!=true){
            String strContador=Integer.toString(contador);
            Integer numero=verificarNumero(strContador,grafo);
            if (numero!=null){
                valido=true;
                return numero;
            } else{
                contador++;
            }
        }
        return null;
    }
    
}
