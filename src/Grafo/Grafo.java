/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo;

import EDD.Lista;
import EDD.ListaArista;
import EDD.ListaG;
import EDD.Nodo;
import EDD.NodoG;
import Helpers.Helpers;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class Grafo {
    private static ListaG nodos;
    private static ListaArista aristas;

    public Grafo() {
        ListaG lista=new ListaG();
        ListaArista lista1=new ListaArista();
        this.nodos=lista;
        this.aristas=lista1;
    }

    public static ListaG getNodos() {
        return nodos;
    }

    public void setNodos(ListaG nodos) {
        this.nodos = nodos;
    }

    public static ListaArista getAristas() {
        return aristas;
    }

    public void setAristas(ListaArista aristas) {
        this.aristas = aristas;
    }

    //imprime todos los atributos de los nodos
    public void printNodos() {
        NodoG pointer=nodos.getHead();
        while (pointer!=null){
            pointer.print();
            pointer=pointer.getNext();
        }
    }
    
    //imprime todos los atributos de los nodos
    public void printAristas(){
        Arista pointer=aristas.getHead();
        while (pointer!=null){
            pointer.print();
            pointer=pointer.getNext();
        }
    }
    
    /*
    Crea todos los usuarios a partir de una lista
    @param usuarios
    */
    public void crearCiudades(Lista ciudades){
        int cont=0; //contador que le otorga el numero a cada usuario
        Nodo pointer=ciudades.getHead();
        while (pointer!= null){
            String id=pointer.getElement().toString();
            Ciudad usuario=new Ciudad(id,cont);
            getNodos().insertFinal(usuario);
            cont++;
            pointer=pointer.getNext();
        }
    }
    
    /*
    Crea todas las relaciones a partir de una lista
    @param relaciones
    */
    public void crearComidas(Lista comidas){
        Nodo pointer = comidas.getHead();
        Helpers helpers = new Helpers();
        while (pointer!=null){
            Lista lista = helpers.stringSplit(pointer.getElement().toString()); //separacion del string 
            Object id1=lista.getHead().getElement(); // se almacena id1 (id del usuario)
            Object id2=lista.getHead().getNext().getElement(); // se almacena id2 (id del usuario)
            Object id3=lista.getHead().getNext().getElement();
            NodoG nodo1=searchById(id1.toString()); // se busca el nodoG cuyo nombre de usuario sea correspondiente al id1 y se almacena en una variable
            NodoG nodo2=searchById(id2.toString()); // se busca el nodoG cuyo nombre de usuario sea correspondiente al id2 y se almacena en una variable
            NodoG nodo3=searchById(id3.toString());
            nodo1.getAdyacentes().insertFinal(nodo2.getCiudad()); // se inserta el nodo 2 en la lista de relaciones del nodo 1
            Arista arista=new Arista(nodo1,nodo2,nodo3);
            getAristas().insertFinal(arista);
            pointer=pointer.getNext();
        }
    }
    
    /*busqueda por id
    *@param id
    *@return
    */
    public NodoG searchById(String id){
        if (nodos.isEmpty()){
            return null;
        }else{
            NodoG pointer=nodos.getHead();
            NodoG temp=null;
            while(pointer!=null){
                if (pointer.getCiudad().getId().equals(id)){ //si la entrada id corresponde al id del usuario
                    temp=pointer;
                    break;
                }
                pointer=pointer.getNext();
            }
            if(temp!= null){
                return temp; // retorna el nodoG correspondiente
                
            } else{
                //System.out.println("El usuario no fue encontrado");
                return null;
            }
        }     
    }
    
    /*borrar nodo por nombre de usuario
    *@param id
    *@return
    */
    public NodoG borrarPorId(String id){
        if (nodos.isEmpty()){
            return null;     
        }else{
            NodoG pointer=nodos.getHead();
            int cont=0;
            NodoG temp=null;
            while(pointer!=null){ //recorre la lista que contiene todos los nodos del grafo
                if (pointer.getCiudad().getId().equalsIgnoreCase(id)){ //si el id coincide el nodo correspondiente se almacena en la variable temp
                    temp=pointer;
                    break;
                }
                pointer=pointer.getNext();
                cont++;
            }
            if(temp!= null){
                nodos.deleteAtIndex(cont); //se borra el nodo del grafo
                NodoG pointer1=getNodos().getHead();
                while (pointer1!=null){ //se recorre nuevamente la lista que contiene todos los nodos del grafo
                    NodoG pointer2=pointer1.getAdyacentes().getHead();
                    int contador=0;
                    while(pointer2!=null){ //se recorre la lista de adyacentes de cada nodo del grafo
                        if (pointer2.getCiudad().getId().equalsIgnoreCase(id)){
                            //si se encuentra la lista de adyacencias el nodo que fue borrado, se borra de la lista de adyacencias tambien
                            pointer1.getAdyacentes().deleteAtIndex(contador); 
                        }
                        contador++;
                        pointer2=pointer2.getNext();
                    }
                    pointer1=pointer1.getNext();
                }
                //se ejecuta el metodo borrar por id tantas veces como la longitud de la lista de aristas
                //si encuentra el id del nodo borrado del grafo en alguna de las aristas, borra dicha arista
                for (int i=0; i<getAristas().getLength();i++){  
                    getAristas().deleteById(id);
                }
                JOptionPane.showMessageDialog(null,"Usuario borrado con exito");
                return temp;
                } 
                else{
                JOptionPane.showMessageDialog(null,"El usuario a borrar no ha sido encontrado");
                return null;
            }
        }     
    }
    
    /*
    Agrega un nuevo usuario
    @Param id, numero, adyacentes
    @return
    */
    public Ciudad nuevoCiudad(String id, Lista adyacentes){
        Helpers helpers=new Helpers();
        String ID=helpers.verificarId(id, this);
        Integer NUMERO=helpers.buscarNumero(this);
        boolean ADYACENTES=true;
        
        if (adyacentes.getLength()!=0){
            Nodo pointer=adyacentes.getHead();
            while (pointer!=null){
                boolean verificar=helpers.ciudadValido(pointer.getElement().toString(), this);
                if (verificar==false){
                    ADYACENTES=false;
                }
            pointer=pointer.getNext();
            }
        }  
        
        if (ID==null || NUMERO==null || ADYACENTES==false){
            return null;
        }
        else{
            Ciudad ciudad=new Ciudad(ID,NUMERO);
            getNodos().insertFinal(ciudad);
            NodoG nodo =searchById(ID);
            
            if (adyacentes.getLength()!=0){
                Nodo pointer1=adyacentes.getHead();
                while (pointer1!=null){
                    NodoG nodo2 =searchById(pointer1.getElement().toString());
                    NodoG nodo3 =searchById(pointer1.getElement().toString());
                    Arista arista=new Arista(nodo, nodo2,nodo3);
                    getAristas().insertFinal(arista);
                    nodo.getAdyacentes().insertFinal(nodo2.getCiudad());
                    pointer1=pointer1.getNext();
                }
            }
            JOptionPane.showMessageDialog(null,"Ciudad agregada con exito");
            return ciudad;
        }
    }
    
    /*
    Crea las relaciones del grafo a partir de las aristas
    Este metodo se utiliza para generar las relaciones del grafo transpuesto
    */  
    public void crearRelacionesTraspuesta(){
        Arista pointer = getAristas().getHead();
        ListaArista lista2=new ListaArista();
        while (pointer!=null){
            String id1=pointer.getInicio().getCiudad().getId();
            String id2=pointer.getObjetivo().getCiudad().getId();
            String id3=pointer.getLongitud().getCiudad().getId();
            NodoG nodo1=searchById(id1); // se busca el nodoG cuyo nombre de usuario sea correspondiente al id1 y se almacena en una variable
            NodoG nodo2=searchById(id2);
            NodoG nodo3=searchById(id3);// se busca el nodoG cuyo nombre de usuario sea correspondiente al id2 y se almacena en una variable
            nodo2.getAdyacentes().insertFinal(nodo1.getCiudad()); //inserta el nodo 1 en los adyacentes del 2 (se hace al reves porque es la transpuesta)
            Arista arista=new Arista(nodo2,nodo1,nodo3); //Se crea la nueva arista al reves (se hace al reves porque es la transpuesta)
            lista2.insertFinal(arista); //se insertan las aristas transpuestas en una lista
            pointer=pointer.getNext();
        }
        setAristas(lista2); //se establece la lista de las aristas transpuestas como las aristas del grafo
    }
    
    /*
    Crea el grafo traspuesto (grafo con la direccion de las aristas invertidas)
    @return
    */
    public Grafo grafoTraspuesto(){
        Grafo traspuesto=new Grafo();
        ListaG nodosTranspuestos=new ListaG();
        traspuesto.setAristas(getAristas());
        NodoG pointer=getNodos().getHead();
        while (pointer!=null){
            nodosTranspuestos.insertFinal(pointer.getCiudad());
            pointer=pointer.getNext();
        }
        traspuesto.setNodos(nodosTranspuestos);
        traspuesto.crearRelacionesTraspuesta();
        return traspuesto;
    }
    
}
