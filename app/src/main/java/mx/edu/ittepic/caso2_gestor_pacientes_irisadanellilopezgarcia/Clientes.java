package mx.edu.ittepic.caso2_gestor_pacientes_irisadanellilopezgarcia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Clientes extends AppCompatActivity {

    private ArrayList<Cliente> clienteList = new ArrayList<Cliente>();
    private RecyclerView rcvClientes;
    private ClienteAdapter adapter;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fetchClientesRecords();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View mView1 = inflater.inflate(R.layout.add_cliente,null);

                final EditText input_nombre = (EditText) mView1.findViewById(R.id.edtNombre);
                final EditText input_direccion = (EditText) mView1.findViewById(R.id.edtDireccion);
                final EditText input_celular = (EditText) mView1.findViewById(R.id.edtCelular);
                final EditText input_fecha = (EditText) mView1.findViewById(R.id.edtFecha);

                final Button btnSave = (Button) mView1.findViewById(R.id.btnGuardar);

                AlertDialog.Builder builder = new AlertDialog.Builder(Clientes.this);
                builder.setView(mView1).setTitle("Agregar un nuevo CLIENTE xD")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.dismiss();
                            }
                        });

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String nombre = input_nombre.getText().toString();
                        String direccion = input_direccion.getText().toString();
                        String celular = input_celular.getText().toString();
                        String fecha = input_fecha.getText().toString();


                        if (nombre.equals("") && direccion.equals("") && celular.equals("") ){
                            Snackbar.make(view,"datos vacios",Snackbar.LENGTH_SHORT).show();

                        }else {
                            Save(nombre,direccion,celular,fecha);
                            dialog.dismiss();
                            Snackbar.make(view,"Saving",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog = builder.create();
                dialog.show();
            }


        });
    }

    public void fetchClientesRecords() {
        try{
            rcvClientes= (RecyclerView) findViewById(R.id.rcvClientes);
            adapter = new ClienteAdapter(this,clienteList,this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(Clientes.this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rcvClientes.setLayoutManager(layoutManager);
            rcvClientes.setAdapter(adapter);

            clienteList.clear();

            BaseDatos dbFunctions = new BaseDatos(this);
            //SQLiteDatabase db = dbFunctions.getWritableDatabase();
            //db.execSQL("INSERT INTO trabajador VALUES(null,'Calors','pintor','3111667745')");

            ArrayList<Cliente> data = dbFunctions.getAllClientes();

            if (data.size()>0){
                for (int i = 0; i<data.size(); i++){

                    //ALmacenar temporalmente los datos en variables
                    int id = data.get(i).getIdCliente();
                    String nombre = data.get(i).getNombreCliente();
                    String direccion = data.get(i).getDireccionCliente();
                    String celular = data.get(i).getCelularCliente();
                    String fecha = data.get(i).getfechaCliente();

                    //Crear un nuevo trabajador con los datos obtenidos de la DB
                    Cliente cliente = new Cliente();

                    cliente.setIdCliente(id);
                    cliente.setNombreCliente(nombre);
                    cliente.setDireccionCliente(direccion);
                    cliente.setCelularCliente(celular);
                    cliente.setfechaCliente(fecha);

                    //Agregar trabajadores a la lista
                    clienteList.add(cliente);

                    //NOtificar cambios a la lista
                }adapter.notifyDataSetChanged();

            }else {
                Toast.makeText(Clientes.this, "No Records found.", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void Save(String nombre, String actividad, String celular, String fecha) {


        BaseDatos dbfunctions = new BaseDatos(Clientes.this);
        Cliente cliente = new Cliente();

        cliente.setNombreCliente(nombre);
        cliente.setDireccionCliente(actividad);
        cliente.setCelularCliente(celular);
        cliente.setfechaCliente(fecha);


        dbfunctions.InsertCliente(cliente);
        Toast.makeText(this, "Guardado...", Toast.LENGTH_SHORT).show();
        fetchClientesRecords();
    }
}
