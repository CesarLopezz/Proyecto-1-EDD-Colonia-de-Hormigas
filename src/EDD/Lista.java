/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import EDD.ILista;

/**
 *
 * @author cesar
 */
public class Lista implements ILista{
    private Nodo head;
    private Integer length;
    
    public Lista() {
        this.head = null;
        this.length = 0;
    }
    
    public Nodo getHead() {
        return head;
    }

    public void setHead(Nodo head) {
        this.head = head;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    
    @Override
    public void insertBegin(Object element) {
        Nodo nodo = new Nodo(element);
        if (isEmpty()){
        setHead(nodo);
        }
        else{
        nodo.setNext(getHead());
        setHead(nodo);
        }
        length++;
    }

    @Override
    public void insertFinal(Object element) {
        Nodo nodo = new Nodo(element);
        if (isEmpty()){
            setHead(nodo);
        }
        else{
            Nodo pointer=getHead();
            while (pointer.getNext()!=null){
                pointer=pointer.getNext();
            }
            pointer.setNext(nodo);
        }
        length++;
    }

    @Override
    public void insertAtIndex(Object element, int index) {
        Nodo nodo = new Nodo(element);
        if (isEmpty() || index==0){
        insertBegin(element);
        }
        else {
            if (index<length){
                Nodo pointer=getHead();
                int cont=0;
                while (cont<index-1){
                    pointer=pointer.getNext();
                    cont++;
                }
                nodo.setNext(pointer.getNext());
                pointer.setNext(nodo);
                length++;
            } 
            else if (index==length){
                insertFinal(element);
            } 
            else {
                System.out.println("Index not valid");
            }
        }
    }

    @Override
    public Object deleteBegin() {
        if (isEmpty()){
            System.out.println("La lista ya esta vacia");
            return null;
        }
        else{
            Nodo temp=getHead();
            setHead(temp.getNext());
            temp.setNext(null);
            length--;
            return temp;
        }
    }

   
    @Override
    public Object deleteFinal() {
        if (isEmpty()){
            System.out.println("La lista ya esta vacia");
            return null;
        }
        else{
            Nodo pointer=getHead();
            while(pointer.getNext().getNext()!=null){
                pointer=pointer.getNext();
            }
            Nodo temp=pointer.getNext();
            pointer.setNext(null);
            length--;
            return temp;
        }
    }

    @Override
    public Object deleteAtIndex(int index) {
        if (isEmpty()){
            System.out.println("La lista ya esta vacia");
            return null;
        }
        else{
            if (index==0){
                return deleteBegin();
            }
            else{
                if (index<getLength()){
                    Nodo pointer=getHead();
                    int cont=0;
                    while(cont<index-1){
                        pointer=pointer.getNext();
                        cont++;
                    }
                    Nodo temp=pointer.getNext();
                    pointer.setNext(temp.getNext());
                    temp.setNext(null);
                    length--;
                    return temp;    
                }
                else{
                    if (index==getLength()-1){
                        return deleteFinal();
                    } 
                    else{
                        System.out.println("Index not valid");
                        return null;
                    }
                    
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
            return getHead()==null;    
    }
    
    public void print() {
        Nodo pointer=getHead();
        while (pointer!=null){
            System.out.println(" ["+pointer.getElement()+"] " );
            pointer=pointer.getNext();
        }
    }
    
    
}
