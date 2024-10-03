package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio ls[];

    public ArregloRedimensionableDeRecordatorios() {

        ls = new Recordatorio[0];
    }

    public int longitud() {

        return ls.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevoLs = new Recordatorio[ls.length + 1];

        for (int j = 0; j < ls.length; j++) {
            nuevoLs[j] = ls[j];
        }

        nuevoLs[nuevoLs.length - 1] = i;

        ls = nuevoLs;
    }

    public Recordatorio obtener(int i) {

        return ls[i];
    }

    public void quitarAtras() {
        Recordatorio[] nuevaLista = new Recordatorio[ls.length - 1];

        for (int j = 0; j < nuevaLista.length; j++) {
            nuevaLista[j] = ls[j];
        }
        ls = nuevaLista;

    }

    public void modificarPosicion(int indice, Recordatorio valor) {

        for (int j = 0; j < ls.length; j++) {
            if (j == indice) {
                ls[j] = valor;
            }
        }

    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {

        ls = new Recordatorio[vector.longitud()];

        for (int j = 0; j < vector.longitud(); j++) {
            String vMsj = vector.obtener(j).mensaje();
            Fecha vF = vector.obtener(j).fecha();
            Horario vH = vector.obtener(j).horario();

            ls[j] = new Recordatorio(vMsj, vF, vH);
        }

    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios();

        for (int j = 0; j < ls.length; j++) {
            copia.agregarAtras(new Recordatorio(ls[j].mensaje(), ls[j].fecha(), ls[j].horario()));
        }

        return copia;
    }
}
