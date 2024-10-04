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
            System.out.println(primero.valor);
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
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void modificarPosicion(int indice, T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
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
