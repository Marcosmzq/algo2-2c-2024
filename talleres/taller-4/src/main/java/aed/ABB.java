package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {

    private Nodo _raiz;
    private int _cardinal;
    private int _altura;

    private class Nodo {
        private T valor;
        private Nodo izq;
        private Nodo der;
        private Nodo padre;

        public Nodo(T v) {
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }
    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
        _altura = 0;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo() {

        if (_raiz == null) {
            return null;
        } else {
            Nodo actual = _raiz;
            while (actual.izq != null) {
                actual = actual.izq;
            }
            return actual.valor;
        }
    }

    public T maximo() {

        if (_raiz == null) {
            return null;
        } else {
            Nodo actual = _raiz;
            while (actual.der != null) {
                actual = actual.der;
            }
            return actual.valor;
        }
    }

    public Nodo buscar_minimo(Nodo nodo){
        if(nodo == null){
            return null; 
        } else {
            Nodo actual = nodo;
            while (actual.izq != null) {
                actual = actual.izq;
            }
            return actual;
        }
    }

    public Nodo buscar_nodo(T elem){
        Nodo ultimo = null;
        Nodo actual = _raiz;

        while(actual != null){
            ultimo = actual;
            int resCompare = actual.valor.compareTo(elem);

            if (resCompare > 0) {
                actual = actual.izq;
            } else if (resCompare < 0) {
                actual = actual.der;
            } else {
                return actual;
            }
        }

        return ultimo;
    }

    public void insertar(T elem) {

        Nodo nuevoNodo = new Nodo(elem);
        Nodo actual = _raiz;
        Nodo padre = null;

        if (_raiz == null) {
            _raiz = nuevoNodo;
            _altura = 1;
            _cardinal = 1;
            return;
        }

        Nodo ultimo_buscado = buscar_nodo(elem);
        
        if(ultimo_buscado != null && ultimo_buscado.valor.compareTo(elem) == 0){
            return;
        } 

        if(ultimo_buscado.valor.compareTo(elem) > 0){ 
            ultimo_buscado.izq = nuevoNodo;
        } else {
            ultimo_buscado.der = nuevoNodo;
        }

        nuevoNodo.padre = ultimo_buscado;
        _cardinal++;
    }

    public boolean pertenece(T elem) {

        Nodo actual = _raiz;

        while (actual != null) {

            int resCompare = actual.valor.compareTo(elem);

            if (resCompare > 0) {
                actual = actual.izq;
            } else if (resCompare < 0) {
                actual = actual.der;
            } else {
                return true;
            }
        }

        return false;
    }

    public void eliminar(T elem) {
        
        if(!pertenece(elem)){
            return;
        }

        Nodo nodoABorrar = buscar_nodo(elem);

        // Caso 1: El nodo no tiene hijos
        if(nodoABorrar.izq == null && nodoABorrar.der == null){
    

            if(nodoABorrar.padre == null) {
                _raiz = null; // SI no tiene padre hay que borrar la raiz!
            }

            if(nodoABorrar.valor.compareTo(nodoABorrar.padre.valor) > 0){
                nodoABorrar.padre.der = null;
            } else {
                nodoABorrar.padre.izq = null;
            } 
        }

        // Caso 2: El nodo tiene exactamente 1 hijo

        if(nodoABorrar.izq == null && nodoABorrar.der != null){

            if(nodoABorrar.valor.compareTo(nodoABorrar.padre.valor) > 0){
                nodoABorrar.padre.der = nodoABorrar.izq;
                nodoABorrar.izq.padre = nodoABorrar.padre;
            } else {
                nodoABorrar.padre.izq = nodoABorrar.der;
                nodoABorrar.der.padre = nodoABorrar.padre;
            }
        }

        if(nodoABorrar.izq != null && nodoABorrar.der == null){
            nodoABorrar = nodoABorrar.der;
            nodoABorrar.der.padre = nodoABorrar.padre;
        }

        // Caso 3: El nodo tiene 2 hijos

        if(nodoABorrar.izq != null && nodoABorrar.der != null){

            //Buscamos el nodo minimo de la derecha (derecha del nodo a borrar)
            Nodo sucesor = buscar_minimo(nodoABorrar.der);
            Nodo padreDelSucesor = sucesor.padre;
            Nodo hijoDelSucesor = sucesor.der; //El hijo esta a la derecha pues el padre es el minimo del sub arbol, por lo que si tiene un hijo va a ser mayor al padre!

            // Quitamos el sucesor del arbol para luego agregarlo en la posicion del nodo a borrar!
            padreDelSucesor.izq = hijoDelSucesor;
            hijoDelSucesor.padre = padreDelSucesor;

            // Agregamos al sucesor en el lugar del nodo a borrar

            sucesor.padre = nodoABorrar.padre;
            sucesor.izq = nodoABorrar.izq;
            sucesor.der = nodoABorrar.der;
        }
    }

    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
