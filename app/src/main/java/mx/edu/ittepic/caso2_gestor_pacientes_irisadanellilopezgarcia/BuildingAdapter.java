package mx.edu.ittepic.caso2_gestor_pacientes_irisadanellilopezgarcia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
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
 * Created by OEM on 15/03/2018.
 */

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.MyViewHolder>{
    Activity activity;
    ArrayList<Build> list;
    AlertDialog dialog;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public BuildingAdapter(Activity activity, ArrayList<Build> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context;
        context = parent.getContext();

        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.build,parent,false);

        return new BuildingAdapter.MyViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Build build = list.get(position);

        holder.txtNombre.setText("Nombre: "+build.getNombrePaciente());
        holder.txtDireccion.setText("Direccion: "+build.getDireccionPaciente());
        holder.txtCelular.setText("Celular: "+build.getCelularPaciente());
        holder.txtMail.setText("Fecha: "+build.getFechaPaciente());
        holder.txtDescipcionObra.setText("Descripcion de padecimiento: "+build.getPadecimientoPaciente());
        holder.txtMonto.setText("Instrucciones: "+build.getInstruccionesPaciente());
        if(build.getEstado()==1){
            holder.txtEstado.setText("Finalizada");
            holder.txtEstado.setTextColor(Color.GREEN);
        }
        else {
            holder.txtEstado.setText("Pendiente");
            holder.txtEstado.setTextColor(Color.RED);
        }


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open edition window
                final int id = build.getIdPaciente();


                LayoutInflater inflater = activity.getLayoutInflater();
                View mView1 = inflater.inflate(R.layout.add_building,null);

                final Button btnGuardar;
                final EditText edtNombre, edtDireccion, edtCelular, edtFecha,edtPadecimiento, edtInstrucciones;
                final BaseDatos dbfunction = new BaseDatos(activity);
                Build _build = new Build();

                _build = dbfunction.getSingleBuilding(id);

                edtNombre = mView1.findViewById(R.id.edtNombre);
                edtDireccion = mView1.findViewById(R.id.edtDireccion);
                edtCelular = mView1.findViewById(R.id.edtCelular);
                edtFecha = mView1.findViewById(R.id.edtFecha);
                edtPadecimiento = mView1.findViewById(R.id.edtDescripcionObra);
                edtInstrucciones = mView1.findViewById(R.id.edtMonto);

                btnGuardar = mView1.findViewById(R.id.btnGuardar);


                edtNombre.setText(_build.getNombrePaciente());
                edtDireccion.setText(_build.getDireccionPaciente());
                edtCelular.setText(_build.getCelularPaciente());
                edtFecha.setText(_build.getFechaPaciente());
                edtPadecimiento.setText(_build.getPadecimientoPaciente());
                edtInstrucciones.setText(_build.getInstruccionesPaciente());

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setView(mView1).setTitle("Editar Paciente")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.dismiss();
                            }
                        });

                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Se normalizan datos y se mandan a la funcion de guardar
                        String nombre, direccion, celular, fecha, padecimiento;
                        String instrucciones;
                        int estado = 0;

                        nombre = edtNombre.getText().toString();
                        direccion = edtDireccion.getText().toString();
                        celular = edtCelular.getText().toString();
                        fecha = edtFecha.getText().toString();
                        padecimiento = edtPadecimiento.getText().toString();
                        instrucciones = edtInstrucciones.getText().toString();

                        if (nombre.equals("") && direccion.equals("") && celular.equals("") && fecha.equals("") && padecimiento.equals("") && instrucciones.equals("") ){
                            Snackbar.make(view,"Espacios incompletos",Snackbar.LENGTH_SHORT).show();

                        }else {
                            //Save(nombre,direccion,celular,mail,descripcio_obra,monto,estado);
                            Build b = new Build();
                            b.setIdPaciente(id);
                            b.setNombrePaciente(nombre);
                            b.setDireccionPaciente(direccion);
                            b.setCelularPaciente(celular);
                            b.setFechaPaciente(fecha);
                            b.setPadecimientoPaciente(padecimiento);
                            b.setInstruccionesPaciente(instrucciones);
                            dbfunction.UpdateBuilding(b);
                            Snackbar.make(view,"Saving",Snackbar.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete worker
                final BaseDatos _dbFunctions = new BaseDatos(activity);
                final int id = build.getIdPaciente();
                _dbFunctions.DeleteBuilding(id);

                Toast.makeText(activity,  " Eliminado...", Toast.LENGTH_SHORT).show();
                ((Buildings)activity).fetchBuildingsRecords();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtNombre,txtDireccion, txtCelular, txtFecha,txtPadecimiento, txtInstrucciones,txtEstado;
        TextView btnEdit, btnDelete;
        TextView ejemplo;
        public MyViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            txtNombre = this.itemView.findViewById(R.id.txtNombre);
            txtDireccion = itemView.findViewById(R.id.txtDireccion);
            txtCelular = itemView.findViewById(R.id.txtCelular);
            txtFecha = itemView.findViewById(R.id.txtFeca);
            txtPadecimiento = itemView.findViewById(R.id.txtPadecimiento);
            txtInstrucciones = itemView.findViewById(R.id.txtInstrucciones);
            txtEstado = itemView.findViewById(R.id.txtEstado);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

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
