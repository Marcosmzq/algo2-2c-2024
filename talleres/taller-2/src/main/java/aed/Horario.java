package aed;

public class Horario {
    private int h;
    private int min;


    public Horario(int hora, int minutos) {
        h = hora;
        min = minutos;
    }

    public int hora() {
        return h;
    }

    public int minutos() {
        return min;
    }

    @Override
    public String toString() {
        return h + ":" + min;
    }

    @Override
    public boolean equals(Object otro) {
        boolean res = true;

        if(otro == null || this.getClass() != otro.getClass()){res = false;}

        Horario otroHorario = (Horario) otro;

        if(h != otroHorario.h || min != otroHorario.min){res = false;}
        
        return res;
    }

}
