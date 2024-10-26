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

        public Nodo sucesor() {
            Nodo res;

            if (this.der != null) {
                res = buscar_minimo(this.der);
            } else {
                res = this.padre;
                while (res.der != null && res.der.valor.equals(this.valor)) {
                    res = res.padre;

                }

            }
            return res;
        }
    }

    public ABB(Nodo raiz, int cardinal, int altura) {
        this._raiz = raiz;
        this._cardinal = cardinal;
        this._altura = altura;
    }

    public ABB(ABB otro) {
        this._raiz = otro._raiz;
        this._cardinal = otro._cardinal;
        this._altura = otro._altura;
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

    public Nodo buscar_minimo(Nodo nodo) {
        if (nodo == null) {
            return null;
        } else {
            Nodo actual = nodo;
            while (actual.izq != null) {
                actual = actual.izq;
            }
            return actual;
        }
    }

    public Nodo buscar_maximo(Nodo nodo) {
        if (nodo == null) {
            return null;
        } else {
            Nodo actual = nodo;
            while (actual.der != null) {
                actual = actual.der;
            }
            return actual;
        }
    }

    public Nodo buscar_nodo(T elem) {
        Nodo ultimo = null;
        Nodo actual = _raiz;

        while (actual != null) {
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

        if (ultimo_buscado != null && ultimo_buscado.valor.compareTo(elem) == 0) {
            return;
        }

        if (ultimo_buscado.valor.compareTo(elem) > 0) {
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

        // Si el elemento no está en nuestro ABB no hacemos nada.

        if (!pertenece(elem)) {
            return;
        }

        Nodo nodoABorrar = buscar_nodo(elem);

        // Caso 1: El nodo a borrar no tiene ningún hijo.

        if (nodoABorrar.der == null && nodoABorrar.izq == null) {

            // Vemos si el nodo a borrar es la raiz o no.
            if (nodoABorrar.padre == null) {
                _raiz = null;
            } else {
                // Vemos si esta a la derecha o izquierda del padre, luego los desconectamos.

                if (nodoABorrar.padre.izq == nodoABorrar) {
                    nodoABorrar.padre.izq = null;
                } else {
                    nodoABorrar.padre.der = null;
                }
            }
        }

        // Caso 2: El nodo a borrar tiene un hijo.

        // Sub caso 1: El hijo esta a la derecha.
        if (nodoABorrar.der != null && nodoABorrar.izq == null) {

            // Vemos si el nodo a borrar es la raiz
            if (nodoABorrar.padre == null) {
                _raiz = nodoABorrar.der;
                nodoABorrar.der.padre = null;
            } else {
                // Vemos si esta a la derecha o izquierda del padre, luego los conectamos al
                // hijo del nodo a borrar con el padre de este.

                if (nodoABorrar.padre.izq == nodoABorrar) {
                    nodoABorrar.padre.izq = nodoABorrar.der;
                } else {
                    nodoABorrar.padre.der = nodoABorrar.der;
                }

                nodoABorrar.der.padre = nodoABorrar.padre;
            }

        }

        // Sub caso 2: El hijo está a la izquierda.
        if (nodoABorrar.izq != null && nodoABorrar.der == null) {

            // Vemos si el nodo a borrar es la raiz
            if (nodoABorrar.padre == null) {
                _raiz = nodoABorrar.izq;
                nodoABorrar.izq.padre = null;
            } else {
                // Vemos si esta a la derecha o izquierda del padre, luego los conectamos al
                // hijo del nodo a borrar con el padre de este.

                if (nodoABorrar.padre.izq == nodoABorrar) {
                    nodoABorrar.padre.izq = nodoABorrar.izq;
                } else {
                    nodoABorrar.padre.der = nodoABorrar.izq;
                }

                nodoABorrar.izq.padre = nodoABorrar.padre;
            }
        }

        // Caso 3: El nodo a borrar tiene 2 hijos.

        if (nodoABorrar.izq != null && nodoABorrar.der != null) {

            // Buscamos sucesor, este será el nodo minimo a la derecha de el nodo a borrar.
            Nodo sucesor = buscar_minimo(nodoABorrar.der);
            // Nodo sucesor = nodoABorrar.sucesor(); -- No uso este por que programe el
            // ejercicio con el anterior y luego agregue este!
            // Borramos el valor del nodo a borrar y el sucesor toma su lugar.
            nodoABorrar.valor = sucesor.valor;

            // Ahora como el sucesor está en el lugar del nodo a borrar, debemos borrar a
            // este.

            // Si tiene un hijo, este estará a la derecha pues que sucesor sea el nodo
            // minimo implica que si tiene un hijo este será mayor a el
            Nodo hijoDelSucesor = sucesor.der;

            if (hijoDelSucesor == null) {
                sucesor.padre.izq = null;
            } else {
                sucesor.padre.izq = hijoDelSucesor;
                hijoDelSucesor.padre = sucesor.padre.izq;
            }
        }

        _cardinal = _cardinal - 1;
    }

    public String toString() {

        if (_raiz == null) {
            return "{}";
        } else {
            Iterador iterador = this.iterador();
            String res = "{";

            int contador = 0;

            while(contador < _cardinal - 1) {
                res = res + iterador.siguiente() + ",";
                contador++;
            }

            if(contador == _cardinal - 1){
                res = res + iterador.siguiente() + "}";
            }
            
            return res;
        }

    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {
            Nodo sucesor = _actual.sucesor();

            if (_actual == null) {
                return false;
            } else if (sucesor != null) {
                return true;
            } else {
                return false;
            }
        }

        public T siguiente() {

            if (_actual == null) {
                _actual = buscar_minimo(_raiz);
                return _actual.valor;
            } else {
                Nodo sucesor = _actual.sucesor();
                _actual = sucesor;

                return _actual.valor;
            }

        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
