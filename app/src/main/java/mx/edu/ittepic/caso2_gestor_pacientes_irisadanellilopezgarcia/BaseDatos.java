package mx.edu.ittepic.caso2_gestor_pacientes_irisadanellilopezgarcia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by OEM on 15/03/2018.
 */

public class BaseDatos extends SQLiteOpenHelper {
    private static final String DB_NAME = "crud";
    private static final int DB_VERSION = 1;

    //private Cliente cliente = new Cliente();
    private Context con;
    String sql = "";


    public BaseDatos(Context con, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(con, DB_NAME, null, DB_VERSION);
        this.con = con;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //execute table cliente
        //sqLiteDatabase.execSQL(cliente.CREATE_TABLE_CLIENTE);

        db.execSQL("CREATE TABLE  Paciente(" +
                "IDPASIENTE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "NOMBRE VARCHAR(100)," +
                "DIRECCION VARCHAR(400)," +
                "CEL VARCHAR(50)," +
                "MAIL VARCHAR(50)," +
                "FECHA DATE," +
                "TRATAMIENTO VARCHAR(1))");
        db.execSQL("CREATE TABLE  CONSULTA(" +
                "IDCONSULTA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "IDPASIENTE INTEGER NOT NULL," +
                "PADECIMIENTO VARCHAR(600)," +
                "FECHACONSULTA DATE," +
                "FOREIGN KEY (IDPASIENTE) REFERENCES PASIENTE(IDPASIENTE))");
        db.execSQL("CREATE TABLE  MEDICAMENTO(" +
                "IDMEDICAMENTO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "NOMBRE VARCHAR(100)," +
                "SINTOMAS VARCHAR(600)," +
                "INSTRUCCIONES VARCHAR(600))");
        db.execSQL("CREATE TABLE  RECETA(" +
                "IDRECETA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "IDMEDICAMENTO INTEGER , " +
                "IDCONSULTA INTEGER NOT NULL, " +
                "INSTRUCCIONES VARCHAR(600)," +
                "FECHAINICIO DATE," +
                "FECHAFIN DATE," +
                "FOREIGN KEY (IDCONSULTA) REFERENCES CONSULTA(IDCONSULTA)," +
                "FOREIGN KEY (IDMEDICAMENTO) REFERENCES MEDICAMENTO(IDMEDICAMENTO))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }


    public ArrayList<Build> getAllBuildings(){

        ArrayList<Build> listBuildings = new ArrayList<Build>();
        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM Paciente order by nombre asc";

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Build build = new Build();
                build.setIdPaciente(Integer.parseInt(cursor.getString(0)));
                build.setNombrePaciente(cursor.getString(1));
                build.setDireccionPaciente(cursor.getString(2));
                build.setCelularPaciente(cursor.getString(3));
                build.setFechaPaciente(cursor.getString(4));
                build.setPadecimientoPaciente(cursor.getString(5));
                build.setinstruccionesPaciente(cursor.getString(6));
                build.setestado(Integer.parseInt(cursor.getString(7)));

                listBuildings.add(build);

            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return listBuildings;
    }

    public ArrayList<Cliente> getAllClientes(){

        ArrayList<Cliente> clienteList = new ArrayList<Cliente>();
        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM Paciente order by nombre asc";

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(Integer.parseInt(cursor.getString(0)));
                cliente.setNombreCliente(cursor.getString(1));
                cliente.setDireccionCliente(cursor.getString(2));
                cliente.setCelularCliente(cursor.getString(3));
                cliente.setfechaCliente(cursor.getString(4));

                clienteList.add(cliente);

            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return clienteList;
    }

    public ArrayList<Detalle> getSingleDetail(int idPaciente, int idCliente){

        ArrayList<Detalle> detalleList = new ArrayList<Detalle>();
        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM receta WHERE idPaciente="+idPaciente+" and idCliente="+idCliente;

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Detalle detalle= new Detalle();
                detalle.setIdPaciente(cursor.getInt(1));
                detalle.setIdCliente(cursor.getInt(0));
                detalle.setFechaInicio(cursor.getString(2));
                detalle.setFechaFin(cursor.getString(3));
                detalle.setPadecimiento(cursor.getString(4));
                detalle.setInstrucciones(cursor.getString(5));

                detalleList.add(detalle);

            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return detalleList;
    }

    public ArrayList<Cliente> getDetaillWorkers(int idPaciente){

        ArrayList<Cliente> clientesList = new ArrayList<Cliente>();
        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT t.idCliente, t.nombreCliente, t.direccionCliente, t.celularCliente " +
                "FROM Cliente as t " +
                "inner join receta as d on d.idCliente=t.idCliente " +
                "where d.idPaciente="+idPaciente;

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(Integer.parseInt(cursor.getString(0)));
                cliente.setNombreCliente(cursor.getString(1));
                cliente.setDireccionCliente(cursor.getString(2));
                cliente.setCelularCliente(cursor.getString(3));

                clientesList.add(cliente);

            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return clientesList;
    }

    public void InsertCliente(Cliente worker){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Cliente VALUES(null," +
                "'"+worker.getNombreCliente()+"'," +
                "'"+worker.getDireccionCliente()+"'" +
                ",'"+worker.getCelularCliente()+"')");
    }

    public void InsertBuilding(Build build){
        try {
            SQLiteDatabase db = getWritableDatabase();

            sql="INSERT INTO cliente VALUES(null," +
                    "'"+build.getNombrePaciente()+"'," +
                    "'"+build.getDireccionPaciente()+"'," +
                    "'"+build.getCelularPaciente()+"'," +
                    "'"+build.getfechaPaciente()+"'," +
                    "'"+build.getPadecimiento()+"'," +
                    "'"+build.getInstrucciones()+"'," +
                    ""+build.getEstado()+")";
            db.execSQL(sql);
        }catch (Exception e){
            Toast.makeText(con, e.getMessage().toString()+""+sql, Toast.LENGTH_LONG).show();
        }

    }

    public void InsertDetalle(int id1, int id2, String fe1, String fe2, String cant, String pag){
        try {
            SQLiteDatabase db = getWritableDatabase();

            sql="INSERT INTO receta VALUES(" +
                    ""+id1+"," +
                    ""+id2+"," +
                    "'"+fe1+"'," +
                    "'"+fe2+"'," +
                    "'"+cant+"'," +
                    "'"+pag+"')";
            db.execSQL(sql);
        }catch (Exception e){
            Toast.makeText(con, e.getMessage().toString()+""+sql, Toast.LENGTH_LONG).show();
        }
    }

    public Cliente getSingleWorker(int id){

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM cliente WHERE idCliente="+id;
        Cursor cursor = db.rawQuery(sql,null);

        if (cursor != null)
            cursor.moveToNext();

        Cliente cliente = new Cliente();
        cliente.setIdCliente(cursor.getShort(0));
        cliente.setNombreCliente(cursor.getString(1));
        cliente.setDireccionCliente(cursor.getString(2));
        cliente.setCelularCliente(cursor.getString(3));

        cursor.close();
        db.close();
        return cliente;
    }
    public Build getSingleBuilding(int id){

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM paciente WHERE idPaciente="+id;
        Cursor cursor = db.rawQuery(sql,null);

        if (cursor != null)
            cursor.moveToNext();

        Build build= new Build();
        build.setIdPaciente(cursor.getShort(0));
        build.setNombrePaciente(cursor.getString(1));
        build.setDireccionPaciente(cursor.getString(2));
        build.setCelularPaciente(cursor.getString(3));
        build.setFechaPaciente(cursor.getString(4));
        build.setPadecimientoPaciente(cursor.getString(5));
        build.setInstruccionesPaciente(cursor.getString(6));
        build.setEstado(cursor.getInt(7));

        cursor.close();
        db.close();
        return build;
    }

    public void DeleteCliente(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Cliente WHERE idCliente="+id);
        db.close();
    }
    public void DeleteBuilding(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM receta WHERE idPaciente="+id);
        db.execSQL("DELETE FROM paciente WHERE idPaciente="+id);
        db.close();
    }

    public void UpdateCliente(Cliente cliente){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        db.execSQL("UPDATE cliente SET " +
                "nombreCliente= '"+cliente.getNombreCliente()+"'," +
                "direccionCliente = '"+cliente.getDireccionCliente()+"'," +
                "celularCliente = '"+cliente.getCelularCliente()+"' " +
                "WHERE idCliente="+cliente.getIdCliente());
        db.close();
    }
    public void UpdateDetail(Detalle det){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        db.execSQL("UPDATE receta SET " +
                "fechaInicioCliente= '"+det.getFechaInicio()+"'," +
                "fechaFinCliente = '"+det.getFechaFin()+"'," +
                "padecimientoCliente = '"+det.getPadecimiento()+"'," +
                "instruccionesCliente= '"+det.getInstrucciones()+"' " +
                "WHERE idCliente="+det.getIdCliente()+" and idPaciente="+det.getIdPaciente());
        db.close();
    }
    public void UpdateBuilding(Build build){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues(); //Paciente
        db.execSQL("UPDATE paciente SET " +
                "nombrePaciente= '"+build.getNombrePaciente()+"'," +
                "direccionPaciente = '"+build.getDireccionPaciente()+"'," +
                "celularPaciente = '"+build.getCelularPaciente()+"'," +
                "fechaPaciente = '"+build.getFechaPaciente()+"'," +
                "padecimientoPaciente = '"+build.getPadecimientoPaciente()+"'," +
                "instruccionesPaciente = '"+build.getInstruccionesPaciente()+"'," +
                "estadoCliente= '"+build.getEstado()+"' " +
                "WHERE idPaciente="+build.getIdPaciente());
        db.close();
    }
    public void DeletDetail(int idPaciente,int idCliente){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM receta WHERE idPaciente="+idPaciente+" and idCliente="+idCliente);
        db.close();
    }
    public void UpdateEstado(int id, int estado){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        db.execSQL("UPDATE paciente SET " +
                "estdoPaciente= "+estado+"" +
                "idPaciente="+id);
        db.close();
    }

}
