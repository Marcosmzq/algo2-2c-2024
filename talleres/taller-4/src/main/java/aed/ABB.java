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
        } else {
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

            if ((nodoABorrar.der != null && nodoABorrar.izq == null)
                    || (nodoABorrar.der == null && nodoABorrar.izq != null)) {

                Nodo hijo = nodoABorrar.der == null ? nodoABorrar.izq : nodoABorrar.der;

                if (nodoABorrar.padre == null) {
                    _raiz = hijo;
                    hijo.padre = null;
                } else {
                    if (nodoABorrar.padre.izq == nodoABorrar) {
                        nodoABorrar.padre.izq = hijo;
                    } else {
                        nodoABorrar.padre.der = hijo;
                    }
                    hijo.padre = nodoABorrar.padre;

                }

            }

            // Caso 3: El nodo a borrar tiene dos hijos.

            if (nodoABorrar.izq != null && nodoABorrar.der != null) {

                Nodo sucesorInmediato = nodoABorrar.sucesor();
                nodoABorrar.valor = sucesorInmediato.valor;

                // Ahora debemos eliminar el sucesor
                if (sucesorInmediato.padre.izq == sucesorInmediato) {
                    sucesorInmediato.padre.izq = sucesorInmediato.der; // Conectamos el hijo del sucesor
                    if (sucesorInmediato.der != null) {
                        sucesorInmediato.der.padre = sucesorInmediato.padre; // Actualizamos el padre del hijo
                    }
                } else {
                    sucesorInmediato.padre.der = sucesorInmediato.der;
                    if (sucesorInmediato.der != null) {
                        sucesorInmediato.der.padre = sucesorInmediato.padre;
                    }

                }
            }
            _cardinal--;
        }
    }

    public String toString() {

        if (_raiz == null) {
            return "{}";
        } else {
            Iterador iterador = this.iterador();
            String res = "{";

            int contador = 0;

            while (contador < _cardinal - 1) {
                res = res + iterador.siguiente() + ",";
                contador++;
            }

            if (contador == _cardinal - 1) {
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
