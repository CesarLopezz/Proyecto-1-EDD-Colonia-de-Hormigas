/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo;

import EDD.NodoG;

/**
 *
 * @author cesar
 */
public class Arista {
    private NodoG inicio;
    private NodoG objetivo;
    private NodoG longitud;
    private Arista next;

    public Arista(NodoG inicio, NodoG objetivo, NodoG longitud){
        this.inicio=inicio;
        this.objetivo=objetivo;
        this.longitud=longitud;
        this.next=null;
    }

    public NodoG getLongitud() {
        return longitud;
    }

    public void setLongitud(NodoG longitud) {
        this.longitud = longitud;
    }

    public NodoG getInicio() {
        return inicio;
    }

    public void setInicio(NodoG inicio) {
        this.inicio = inicio;
    }

    public NodoG getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(NodoG objetivo) {
        this.objetivo = objetivo;
    }

    public Arista getNext() {
        return next;
    }

    public void setNext(Arista next) {
        this.next = next;
    }
    
    public void print(){
        System.out.println(getInicio().getCiudad().getId()+", "+getObjetivo().getCiudad().getId());
    }
    
}
