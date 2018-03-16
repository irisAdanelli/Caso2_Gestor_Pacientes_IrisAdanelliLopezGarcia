package mx.edu.ittepic.caso2_gestor_pacientes_irisadanellilopezgarcia;

/**
 * Created by OEM on 15/03/2018.
 */

public class Build {
    int idPaciente;
    String nombrePaciente = "";
    String direccionPaciente = "";
    String celularPaciente = "";
    String fechaPaciente = "";
    String padecimientoPaciente = "";
    String instruccionesPaciente = "";
    int estado;

    public Build() {}

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getDireccionPaciente() {
        return direccionPaciente;
    }

    public void setDireccionPaciente(String direccionPaciente) {
        this.direccionPaciente = direccionPaciente;
    }

    public String getCelularPaciente() {
        return celularPaciente;
    }

    public void setCelularPaciente(String celularPaciente) {
        this.celularPaciente = celularPaciente;
    }

    public String getFechaPaciente() {
        return fechaPaciente;
    }

    public void setFechaPaciente(String fechaPaciente) {
        this.fechaPaciente = fechaPaciente;
    }

    public String getPadecimientoPaciente() {
        return padecimientoPaciente;
    }

    public void setPadecimientoPaciente(String padecimientoPaciente) {
        this.padecimientoPaciente = padecimientoPaciente;
    }

    public String getInstruccionesPaciente() {
        return instruccionesPaciente;
    }

    public void setInstruccionesPaciente(String instruccionesPaciente) {
        this.instruccionesPaciente = instruccionesPaciente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
