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
            return _raiz.valor;
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
            return _raiz.valor;
        } else {
            Nodo actual = _raiz;
            while (actual.der != null) {
                actual = actual.der;
            }
            return actual.valor;
        }
    }

    public void insertar(T elem) {
        throw new UnsupportedOperationException("No implementada aun");
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
        throw new UnsupportedOperationException("No implementada aun");
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
