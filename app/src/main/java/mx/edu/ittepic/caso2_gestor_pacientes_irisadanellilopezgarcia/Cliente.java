package mx.edu.ittepic.caso2_gestor_pacientes_irisadanellilopezgarcia;

/**
 * Created by carlos on 11/03/18.
 */

public class Cliente {
    int idCliente;
    String nombreCliente;
    String direccionCliente;
    String celularCliente;
    String fechaCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularTrabajador) {
        this.celularCliente = celularCliente;
    }


    public String getfechaCliente() {
        return fechaCliente;
    }

    public void setfechaCliente(String fechaCliente) {
        this.fechaCliente = fechaCliente;
    }
}
