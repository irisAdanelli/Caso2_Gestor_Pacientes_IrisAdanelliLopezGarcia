package co.quindio.sena.ejemplosqlite.actividades;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import co.quindio.sena.ejemplosqlite.ConexionSQLiteHelper;
import co.quindio.sena.ejemplosqlite.R;
import co.quindio.sena.ejemplosqlite.entidades.Actividades;
import co.quindio.sena.ejemplosqlite.utilidades.Utilidades;

public class ListaMadicamentos extends AppCompatActivity {

    ListView listViewMascota;
    ArrayList<String> listaInformacion;
    ArrayList<Actividades> listaActividades;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamento);
        //conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        conn=new ConexionSQLiteHelper(getApplicationContext(),"alumnos",null,1);

        listViewMascota= (ListView) findViewById(R.id.listViewMascotas);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewMascota.setAdapter(adaptador);

        listViewMascota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Actividades actividades = listaActividades.get(pos);

                Intent intent=new Intent(ListaMadicamentos.this,DetalleMedicamento.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("actividades", actividades);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }



    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Actividades actividades =null;
        listaActividades =new ArrayList<Actividades>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA,null);


        while (cursor.moveToNext()){
            actividades =new Actividades();
            actividades.setIdMedicamento(cursor.getInt(0));
            actividades.setNombreMedicamento(cursor.getString(1));
            actividades.setPadecimiento(cursor.getString(2));
            actividades.setCreditos(cursor.getString(3));
            actividades.setIdPaciente(cursor.getInt(4));

            listaActividades.add(actividades);
        }
        obtenerLista();
    }


    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i = 0; i< listaActividades.size(); i++){
         //   listaInformacion.add(listaActividades.get(i).getIdMedicamento()+" - "
            //listaInformacion.add( listaActividades.get(i).getNombreMedicamento());
            listaInformacion.add(listaActividades.get(i).getIdPaciente()+" - "
                    + listaActividades.get(i).getNombreMedicamento());

        }

    }
}
