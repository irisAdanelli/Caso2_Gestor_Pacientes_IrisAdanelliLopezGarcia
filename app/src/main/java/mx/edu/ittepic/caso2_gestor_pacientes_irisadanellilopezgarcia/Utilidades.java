package co.quindio.sena.ejemplosqlite.utilidades;

/**
 * Created by CHENAO on 7/05/2017.
 */

public class Utilidades {

    //Constantes
    public static final String TABLA_USUARIO="alumno";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TELEFONO="telefono";
    public static final String CAMPO_FECHA="matricula";
    public static final String CAMPO_DIRECCION="direccion";
    public static final String CAMPO_EMAL="email";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_TELEFONO+" TEXT,"+CAMPO_DIRECCION+" TEXT,"+CAMPO_EMAL+" TEXT,"+CAMPO_FECHA+" TEXT)";



    //Constantes
    public static final String TABLA_MASCOTA="actividad";
    public static final String CAMPO_ID_MASCOTA="id_actividad";
    public static final String CAMPO_NOMBRE_MASCOTA="nombre_actividad";
    public static final String CAMPO_RAZA_MASCOTA="fecha";
    public static final String CAMPO_ID_DUENIO="id_alumno";
    public static final String CAMPO_CREDITOS_MASCOTA="creditos";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA_MASCOTA+" TEXT,"+CAMPO_CREDITOS_MASCOTA+" TEXT,"+CAMPO_ID_DUENIO+" INTEGER)";

}
