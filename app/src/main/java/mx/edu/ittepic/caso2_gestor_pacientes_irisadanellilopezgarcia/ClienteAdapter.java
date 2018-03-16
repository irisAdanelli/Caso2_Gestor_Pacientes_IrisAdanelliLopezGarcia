package mx.edu.ittepic.caso2_gestor_pacientes_irisadanellilopezgarcia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by carlos on 11/03/18.
 */

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.MyViewHolder> {
    private Activity activity;
    private ArrayList<Cliente> list;
    private AlertDialog dialog;
    private Clientes nClientes;
    private Detalle_Receta cDetalle_receta;
    private ClienteAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ClienteAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public ClienteAdapter(Activity activity, ArrayList<Cliente> list, Clientes clientes){
        this.activity = activity;
        this.list = list;
        nClientes = clientes;
    }
    public ClienteAdapter(Activity activity, ArrayList<Cliente> list, Detalle_Receta detalle_receta){
        this.activity = activity;
        this.list = list;
        cDetalle_receta = detalle_receta;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context;
        context =parent.getContext();

        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);
        View itemView;
        if(nClientes!=null){
            itemView = inflater.inflate(R.layout.cliente,parent,false);
        }
        else{
            itemView = inflater.inflate(R.layout.cliente_single_item,parent,false);
        }


        return new ClienteAdapter.MyViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Cliente cliente = list.get(position);

        holder.txtNombre.setText("Nombre: "+ cliente.getNombreCliente());
        holder.txtActividad.setText("Direccion: "+ cliente.getDireccionCliente());
        holder.txtCelular.setText("Celular: "+ cliente.getCelularCliente());

        if(nClientes!=null){
            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open edition window
                final int id = cliente.getIdCliente();


                LayoutInflater layoutInflater = activity.getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.edit_cliente,null);

                final EditText input_nombre = (EditText) view1.findViewById(R.id.edtNombre);
                final EditText input_direccion = (EditText) view1.findViewById(R.id.edtDireccion);
                final EditText input_celular = (EditText) view1.findViewById(R.id.edtCelular);
                final EditText input_fecha = (EditText) view1.findViewById(R.id.edtFecha);

                final Button btnSave = (Button) view1.findViewById(R.id.btnGuardar);


                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setView(view1)
                        .setTitle("Edit Records")
                        .setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                final BaseDatos _dbFunctions = new BaseDatos(activity);
                final Cliente _cliente = _dbFunctions.getSingleCliente(id);
                input_nombre.setText(_cliente.getNombreCliente());
                input_direccion.setText(_cliente.getDireccionCliente());
                input_celular.setText(_cliente.getCelularCliente());
                input_fecha.setText(_cliente.getfechaCliente());


                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String nombre = input_nombre.getText().toString();
                        String direccion = input_direccion.getText().toString();
                        String celular = input_celular.getText().toString();


                        _cliente.setNombreCliente(nombre);
                        _cliente.setDireccionCliente(direccion);
                        _cliente.setCelularCliente(celular);


                        _dbFunctions.UpdateCliente(_cliente);

                        Toast.makeText(activity, nombre + " updated.", Toast.LENGTH_SHORT).show();
                        ((Clientes)activity).fetchClientesRecords();
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Delete cliente
                    final BaseDatos _dbFunctions = new BaseDatos(activity);
                    final int id = cliente.getIdCliente();
                    _dbFunctions.DeleteCliente(id);

                    Toast.makeText(activity,  " Eliminado...", Toast.LENGTH_SHORT).show();
                    ((Cliente)activity).fetchWorkersRecords();
                    //dialog.dismiss();

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtActividad, txtCelular;
        TextView btnEdit, btnDelete;

        public MyViewHolder(View itemView, final ClienteAdapter.OnItemClickListener listener) {
            super(itemView);

            txtNombre = this.itemView.findViewById(R.id.txtNombre);
            txtActividad = this.itemView.findViewById(R.id.txtActividad);
            txtCelular = this.itemView.findViewById(R.id.txtCelular);
            if(cWorkers!=null){
                btnEdit = this.itemView.findViewById(R.id.btnEdit);
                btnDelete = this.itemView.findViewById(R.id.btnDelete);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
