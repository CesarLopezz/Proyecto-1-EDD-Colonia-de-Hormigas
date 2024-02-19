/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import Grafo.Ciudad;

/**
 *
 * @author cesar
 */
////// CAMBIAR  POR CIUDADES /////

public class NodoG {
    private Ciudad ciudad;
    private NodoG next;
    private ListaG adyacentes;
    private Integer numeroComponente;

    public NodoG(Ciudad usuario) {
        this.ciudad = usuario;
        this.next = null;
        this.adyacentes = new ListaG();
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public NodoG getNext() {
        return next;
    }

    public void setNext(NodoG next) {
        this.next = next;
    }

    public ListaG getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(ListaG adyacentes) {
        this.adyacentes = adyacentes;
    }

    public Integer getNumeroComponente() {
        return numeroComponente;
    }

    public void setNumeroComponente(Integer numeroComponente) {
        this.numeroComponente = numeroComponente;
    }
    
    
    
    //imprime los atributos del nodo
    public void print(){
        System.out.println("-----------------------------------");
        System.out.println("Id: "+getCiudad().getId());
        System.out.println("Numero: "+getCiudad().getNumero());
        System.out.print("Relaciones: ");
        NodoG pointer=getAdyacentes().getHead();
        while (pointer!=null){
            System.out.print("["+pointer.getCiudad().getId()+"]");
            pointer=pointer.getNext(); 
        }
        System.out.println(" ");
        System.out.println("-----------------------------------");
    }
    
}
