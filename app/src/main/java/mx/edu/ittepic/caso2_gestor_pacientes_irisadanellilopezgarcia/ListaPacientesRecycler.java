package co.quindio.sena.ejemplosqlite.alumnos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import co.quindio.sena.ejemplosqlite.ConexionSQLiteHelper;
import co.quindio.sena.ejemplosqlite.R;
import co.quindio.sena.ejemplosqlite.adaptadores.ListaPersonasAdapter;
import co.quindio.sena.ejemplosqlite.entidades.Alumno;
import co.quindio.sena.ejemplosqlite.utilidades.Utilidades;

public class ListaPacientesRecycler extends AppCompatActivity {

    ArrayList<Alumno> listaAlumno;
    RecyclerView recyclerViewUsuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personas_recycler);

        //conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        conn=new ConexionSQLiteHelper(getApplicationContext(),"alumnos",null,1);

        listaAlumno =new ArrayList<>();

        recyclerViewUsuarios= (RecyclerView) findViewById(R.id.recyclerPersonas);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaPersonasAdapter adapter=new ListaPersonasAdapter(listaAlumno);
        recyclerViewUsuarios.setAdapter(adapter);

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Alumno alumno =null;
       // listaUsuarios=new ArrayList<Alumno>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            alumno =new Alumno();
            alumno.setId(cursor.getInt(0));
            alumno.setNombre(cursor.getString(1));
            alumno.setTelefono(cursor.getString(2));
            alumno.setDireccion(cursor.getString(3));
            alumno.setEmail(cursor.getString(4));
            alumno.setFecha(cursor.getString(5));

            listaAlumno.add(alumno);
        }
    }

    private void llenarListaUsuarios() {
      //  listaAlumno.add(new Alumno(1,"Carlos","Reforma 187","3111362665","2018-02-25"));

    }
}
