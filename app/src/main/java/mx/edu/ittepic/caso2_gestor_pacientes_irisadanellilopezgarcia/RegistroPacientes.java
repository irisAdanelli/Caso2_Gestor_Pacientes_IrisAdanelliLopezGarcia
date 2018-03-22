package co.quindio.sena.ejemplosqlite.alumnos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.quindio.sena.ejemplosqlite.ConexionSQLiteHelper;
import co.quindio.sena.ejemplosqlite.R;
import co.quindio.sena.ejemplosqlite.utilidades.Utilidades;

public class RegistroPacientes extends AppCompatActivity {

    EditText campoId,campoNombre,campoTelefono,campoDireccion,campoFecha,campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        campoId= (EditText) findViewById(R.id.campoId);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        campoTelefono= (EditText) findViewById(R.id.campoTelefono);
        campoDireccion= (EditText) findViewById(R.id.campoDireccion);
        campoEmail= (EditText) findViewById(R.id.campoEmail);
        campoFecha= (EditText) findViewById(R.id.campoFecha);

    }

    public void onClick(View view) {
        registrarUsuarios();
        //registrarUsuariosSql();
    }

    private void registrarUsuariosSql() {
       // ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"alumnos",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();


        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO
                +" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+")" +
                " VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
                +campoTelefono.getText().toString()+  "','"
                +campoDireccion.getText().toString()+"','"
                +campoEmail.getText().toString()+"','"
                +campoFecha.getText().toString()+"')";

        db.execSQL(insert);
        db.close();
    }


    private void registrarUsuarios() {
        //ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"alumnos",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ID,campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());
        values.put(Utilidades.CAMPO_DIRECCION,campoDireccion.getText().toString());
        values.put(Utilidades.CAMPO_EMAL,campoEmail.getText().toString());
        values.put(Utilidades.CAMPO_FECHA,campoFecha.getText().toString());

        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Registrado Correctamente ",Toast.LENGTH_SHORT).show();

        campoNombre.setText("");
        campoTelefono.setText("");
        campoId.setText("");
        campoEmail.setText("");
        campoFecha.setText("");
        campoDireccion.setText("");

        db.close();
    }
}
