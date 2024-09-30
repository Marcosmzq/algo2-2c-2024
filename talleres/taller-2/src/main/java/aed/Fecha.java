package aed;

public class Fecha {
    private int m;
    private int d;

    public Fecha(int dia, int mes) {
        m = mes;
        d = dia;
    }

    public Fecha(Fecha fecha) {
        new Fecha(fecha.d, fecha.m);
    }

    public Integer dia() {
        return d;
    }

    public Integer mes() {
        return m;
    }

    @Override
    public String toString() {
        String msj = d + "/" + m;
        return msj;
    }

    @Override
    public boolean equals(Object otra) {
        boolean res = true;

        if(otra == null){ res = false;}
        if(this.getClass() != otra.getClass()){ res = false;}
    
        Fecha otraFecha = (Fecha) otra;

        if(d != otraFecha.d){ res = false;}
        if(m != otraFecha.m){ res = false;}

        return res;
    }

    public void incrementarDia() {
        int diasMes = diasEnMes(m);

        if(d+ 1 > diasMes){
            d = 1;

            if(m == 12){
                m = 1;
            }else{
                m = m + 1;
            }
        }else{
            d = d + 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
