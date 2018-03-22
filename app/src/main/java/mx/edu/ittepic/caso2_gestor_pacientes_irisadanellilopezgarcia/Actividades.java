package co.quindio.sena.ejemplosqlite.entidades;

import java.io.Serializable;

/**
 * Created by CHENAO on 17/06/2017.
 */

public class Actividades implements Serializable {

    private Integer idPaciente;
    private Integer idMedicamento;
    private String nombreMedicamento;
    private String padecimiento;
    private String creditos;

    public Actividades(){

    }

    public Actividades(Integer idPaciente, Integer idMedicamento, String nombreMedicamento, String padecimiento,String creditos) {
        this.idPaciente = idPaciente;
        this.idMedicamento = idMedicamento;
        this.nombreMedicamento = nombreMedicamento;
        this.padecimiento = padecimiento;
        this.creditos = creditos;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }
    public void setIdMedicamento(Integer idMedicamento) {this.idMedicamento = idMedicamento;}

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }
    public void setNombreMedicamento(String nombreMedicamento) {this.nombreMedicamento = nombreMedicamento;}

    public String getPadecimiento() {
        return padecimiento;
    }
    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public String getCreditos() {
        return creditos;
    }
    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }
}
