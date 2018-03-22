package co.quindio.sena.ejemplosqlite.actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import co.quindio.sena.ejemplosqlite.ConexionSQLiteHelper;
import co.quindio.sena.ejemplosqlite.R;
import co.quindio.sena.ejemplosqlite.entidades.Actividades;
import co.quindio.sena.ejemplosqlite.utilidades.Utilidades;

public class DetalleMedicamento extends AppCompatActivity {

    ConexionSQLiteHelper conn;
    TextView campoIdMascota, campoNombreMascota, campoRaza,campoCreditos;
    TextView campoIdPersona, campoNombrePersona, campoTelefonoPersona,campoDireccionPersona,campoEmailPersona,campoFechaPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_medicamento);

       // conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        conn=new ConexionSQLiteHelper(getApplicationContext(),"alumnos",null,1);

        campoIdPersona = (TextView) findViewById(R.id.campoId);
        campoNombrePersona = (TextView) findViewById(R.id.campoNombre);
        campoTelefonoPersona = (TextView) findViewById(R.id.campoTelefono);
        campoDireccionPersona = (TextView) findViewById(R.id.campoDireccion);
        campoEmailPersona = (TextView) findViewById(R.id.campoEmail);
        campoFechaPersona = (TextView) findViewById(R.id.campoFecha);

        //campoIdMascota = (TextView) findViewById(R.id.campoIdMascota);
        campoNombreMascota = (TextView) findViewById(R.id.campoNombreMascota);
        campoRaza = (TextView) findViewById(R.id.campoRaza);
        campoCreditos = (TextView) findViewById(R.id.campoCreditos);

        Bundle objetoEnviado=getIntent().getExtras();
        Actividades actividades =null;

        if(objetoEnviado!=null){
            actividades = (Actividades) objetoEnviado.getSerializable("actividades");
            campoIdMascota.setText(actividades.getIdMedicamento().toString());
            campoNombreMascota.setText(actividades.getNombreMedicamento().toString());
            campoRaza.setText(actividades.getPadecimiento().toString());
            campoCreditos.setText(actividades.getCreditos().toString());
            consultarPersona(actividades.getIdPaciente());
        }

    }

    private void consultarPersona(Integer idPersona) {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={idPersona.toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO,Utilidades.CAMPO_DIRECCION,Utilidades.CAMPO_EMAL,Utilidades.CAMPO_FECHA};
        try {
            Cursor cursor =db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoIdPersona.setText(idPersona.toString());
            campoNombrePersona.setText(cursor.getString(0));
            campoTelefonoPersona.setText(cursor.getString(1));
            campoDireccionPersona.setText(cursor.getString(2));
            campoEmailPersona.setText(cursor.getString(3));
            campoFechaPersona.setText(cursor.getString(4));

            Toast.makeText(getApplicationContext(),"Receta del Alumno: "+cursor.getString(0),Toast.LENGTH_LONG).show();
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"ocurrió un error",Toast.LENGTH_LONG).show();
            campoNombrePersona.setText("");
            campoTelefonoPersona.setText("");
            campoEmailPersona.setText("");
            campoDireccionPersona.setText("");
            campoFechaPersona.setText("");
        }

    }
}
