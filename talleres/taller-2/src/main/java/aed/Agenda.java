package aed;

public class Agenda {

    private Fecha f;
    private Recordatorio[] r = new Recordatorio[0];

    public Agenda(Fecha fechaActual) {
        f = new Fecha(fechaActual.dia(), fechaActual.mes());
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        Recordatorio[] nuevoR = new Recordatorio[r.length + 1];

        for (int j = 0; j < r.length; j++) {
            nuevoR[j] = r[j];
        }

        nuevoR[nuevoR.length - 1] = new Recordatorio(recordatorio.mensaje(), recordatorio.fecha(),
                recordatorio.horario());
        r = nuevoR;
    }

    @Override
    public String toString() {
        String recos = "";

        for (int j = 0; j < r.length; j++) {
            if (f.dia() == r[j].fecha().dia() && f.mes() == r[j].fecha().mes()) {
                recos = recos + r[j].toString().concat("\n");
            }

        }

        return f
                .toString()
                .concat("\n=====\n")
                .concat(recos);
    }

    public void incrementarDia() {
        f.incrementarDia();
    }

    public Fecha fechaActual() {

        return f;
    }

}
