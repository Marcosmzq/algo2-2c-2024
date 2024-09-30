package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio ls [] = new Recordatorio[0];

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar 
    }

    public int longitud() {
        
        return ls.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio [] nuevoLs = new Recordatorio[ls.length + 1];

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
       Recordatorio [] nuevaLista =  new Recordatorio [ls.length - 1]; 

       for (int j = 0; j < ls.length - 1; j++) {
        nuevaLista[j] = ls[j];

        ls = nuevaLista;
    }

    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        Recordatorio [] nuevaLista =  new Recordatorio [ls.length]; 

        for (int j = 0; j < nuevaLista.length; j++) {

            if(j == indice){ nuevaLista[j] = valor;}
            else{nuevaLista[j] = ls[j];}
            
            ls = nuevaLista;
        }


    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // si modifico el vector ls no deberia cambiar por que los elementos de ls no estan referenciados a los elem de vector!.

        Recordatorio [] lista = new Recordatorio[0];

        
        for(int j = 0; j < vector.longitud(); j++){
            String vMsj = vector.obtener(j).mensaje();
            Fecha vF = vector.obtener(j).fecha();
            Horario vH = vector.obtener(j).horario();

            lista[j] = new Recordatorio(vMsj, vF, vH);
        }

        ls = lista;

    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios vector = new ArregloRedimensionableDeRecordatorios();

        for(int j=0; j < vector.longitud(); j++){
            Fecha f = ls[j].fecha();
            Horario h = ls[j].horario();
            String m = ls[j].mensaje();

            Recordatorio r = new Recordatorio(m,f,h);

            vector.agregarAtras(r);
        }
  
        return vector;
    }
}
