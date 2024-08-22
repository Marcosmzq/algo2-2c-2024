package aed;

class Funciones {
    int cuadrado(int x) {
        return (x * x);
    }

    double distancia(double x, double y) {
        return Math.sqrt((x * x) + (y * y));
    }

    boolean esPar(int n) {
        if ((n % 2) == 0) {
            return true;
        } else {
            return false;
        }

    }

    boolean esBisiesto(int n) {
        // primero me fijo si es multiplo de 4 pero no de 100
        boolean caso1 = (n % 4 == 0) && (n % 100 != 0);

        // ahora me fijo si es multiplo de 400
        boolean caso2 = (n % 400 == 0);

        if (caso1 || caso2) {
            return true;
        } else {
            return false;
        }
    }

    int factorialIterativo(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            int res = 1;
            for (int i = 1; i <= n; i++) {
                res = res * i;
            }
            return res;
        }
    }

    int factorialRecursivo(int n) {
        // caso base
        if (n == 0) {
            return 1;
        }
        // paso recursivo
        return (n * factorialRecursivo(n - 1));
    }

    boolean esPrimo(int n) {
        if (n == 1 || n == 0) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int i = 0; i < numeros.length; i++) {
            res = res + numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res = -1;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == buscado) {
                res = i;
            }
        }
        return res;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res = false;
        for (int i = 0; i < numeros.length; i++) {
            if (esPrimo(numeros[i])) {
                res = true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res = true;

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] % 2 != 0) {
                res = false;
            }
        }

        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean res = true;

        if (s1.length() > s2.length()) {
            return false;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                res = false;
            }
        }

        return res;
    }

    // funcion auxiliar para invertir una palabra
    String invertir(String palabra) {
        String res = "";
        for (int i = palabra.length() - 1; i >= 0; i--) {
            res = res + palabra.charAt(i);
        }
        return res;
    }

    boolean esSufijo(String s1, String s2) {

        if (s1 == s2) {
            return true;
        }
        if (s1.length() > s2.length()) {
            return false;
        } else {
            String p1 = invertir(s1);
            String p2 = invertir(s2);
            return esPrefijo(p1, p2);
        }

    }
}
