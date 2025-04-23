package src.gestores;

import src.enums.NivelUrgencia;
import src.alertas.AlertaVencimiento;
import src.alertas.AlertaDisponibilidad;

import java.util.ArrayList;
import java.util.List;

public class GestorAlertas {
    private List<String> historial = new ArrayList<>();
    private AlertaDisponibilidad disponibilidad;
    private AlertaVencimiento vencimiento;
    private NivelUrgencia urgenciaDisponibilidad;
    private NivelUrgencia urgenciaVencimiento;

    public GestorAlertas(AlertaDisponibilidad disponibilidad, AlertaVencimiento vencimiento) {
        this.disponibilidad = disponibilidad;
        this.vencimiento = vencimiento;
        this.mostrarAlertaVencimiento();
        this.urgenciaDisponibilidad = NivelUrgencia.BAJA;
        this.urgenciaVencimiento = NivelUrgencia.BAJA;
    }
    public NivelUrgencia getUrgenciaDisponibilidad() {
        return urgenciaDisponibilidad;
    }
    public void setUrgenciaDisponibilidad(NivelUrgencia urgenciaDisponibilidad) {
        this.urgenciaDisponibilidad = urgenciaDisponibilidad;
    }

    public NivelUrgencia getUrgenciaVencimiento() {
        return urgenciaVencimiento;
    }
    public void setUrgenciaVencimiento(NivelUrgencia urgenciaVencimiento) {
        this.urgenciaVencimiento = urgenciaVencimiento;
     }
    public void mostrarAlertasDisponibilidad() {
        List<String> alertaDisp = disponibilidad.mostrarDisponibilidad();
        for (String alerta : alertaDisp) {
            historial.add(alerta);
        }
    }
    public void mostrarAlertaVencimiento() {
        List<String> alertaVen = vencimiento.mostrarVencimiento();
        for (String alerta : alertaVen) {
            historial.add(alerta);
        }
    }

    public void mostrarHistoria() {
        for (String alerta : historial) {
            System.out.println(alerta);
        }
    }
}
