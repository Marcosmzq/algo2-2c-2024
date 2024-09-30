package aed;

public class Recordatorio {

    private String msj;
    private Fecha f;
    private Horario h;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        msj = mensaje;

        f = new Fecha(fecha.dia(), fecha.mes());
        h = new Horario(horario.hora(), horario.minutos());

    }

    public Horario horario() {
        
        return new Horario(h.hora(), h.minutos());
    }

    public Fecha fecha() {
        
        return new Fecha(f.dia(), f.mes());
    }

    public String mensaje() {
        
        return msj;
    }

    @Override
    public String toString() {
       
        return msj + " @ " + f + " " + h;
    }

    @Override
    public boolean equals(Object otro) {
        boolean res = true;

        if(otro == null || this.getClass() != otro.getClass()){res = false;}

        Recordatorio otroRecordatorio = (Recordatorio) otro;

        if(h != otroRecordatorio.h || f != otroRecordatorio.f || msj != otroRecordatorio.msj){res = false;}
        
        return res;
    }

}
