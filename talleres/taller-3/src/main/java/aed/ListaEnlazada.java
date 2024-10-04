package aed;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T elem) { 
            ant = null;
            valor = elem;
            sig = null;
        }
    }

    public ListaEnlazada() {
        primero = null;
    }

    public int longitud() {
        int contador = 1;
        Nodo actual = primero;

        if(primero == null){return 0;}
        else{
            while(actual.sig != null){
                actual = actual.sig;
                contador++;
            }
            return contador;
        }
    }

    public void agregarAdelante(T elem) {
        Nodo nodoNuevo = new Nodo(elem);

        if(primero == null){
            primero = nodoNuevo;
        }else{
            primero.ant = nodoNuevo;
            nodoNuevo.sig = primero;
            primero = nodoNuevo;
        }
    }


    public void agregarAtras(T elem) {
        Nodo nuevoNodo = new Nodo(elem);

        if(primero == null){primero = nuevoNodo;}
        else{
            Nodo actual = primero;

            while(actual.sig != null){
                actual = actual.sig;
            }

            actual.sig = nuevoNodo;
            nuevoNodo.ant = actual;
        }

    }

    public T obtener(int i) {
        int contador = 0;
        Nodo actual = primero;

        while(contador != i){
            contador++;
            actual = actual.sig;
        }

        return actual.valor;
    }


    public void eliminar(int i) {
        int contador = 0;
        Nodo actual = primero;
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<T>();

        while(actual != null){
            if(contador != i){
                nuevaLista.agregarAtras(actual.valor);          
            }
            actual = actual.sig;
            contador++;
        }

        primero = nuevaLista.primero;
    }

    public void modificarPosicion(int indice, T elem) {
        int contador = 0;
        Nodo actual = primero;
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<T>();

        while(actual != null){
            if(contador != indice){
                nuevaLista.agregarAtras(actual.valor);
            }
            if(contador == indice){
                Nodo nuevoNodo = new Nodo(elem);
                nuevaLista.agregarAtras(nuevoNodo.valor);
            }
            actual = actual.sig;
            contador++;
        }

        primero = nuevaLista.primero;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        int contador = 0;
        
        while(contador < lista.longitud()){
            T actual = lista.obtener(contador);
            Nodo nuevoNodo = new Nodo(actual);
            this.agregarAtras(nuevoNodo.valor);
            contador++;
        }
    }
    
    @Override
    public String toString() {
        Nodo actual = primero;
        String res = "[";

        while(actual != null){
            if(actual.sig != null){res = res + actual.valor + ", ";}
            else{res = res + actual.valor;}
            actual = actual.sig;
        
        }
        return res.concat("]");
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados

        public boolean haySiguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        
        public boolean hayAnterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
