package co.quindio.sena.ejemplosqlite.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.quindio.sena.ejemplosqlite.R;
import co.quindio.sena.ejemplosqlite.entidades.Alumno;

/**
 * Created by CHENAO on 8/07/2017.
 */

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonasViewHolder> {

    ArrayList<Alumno> listaAlumno;

    public ListaPersonasAdapter(ArrayList<Alumno> listaAlumno) {
        this.listaAlumno = listaAlumno;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_personas,null,false);
        return new PersonasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.documento.setText(listaAlumno.get(position).getId().toString());
        holder.nombre.setText(listaAlumno.get(position).getNombre());
        holder.telefono.setText(listaAlumno.get(position).getTelefono());
        holder.direccion.setText(listaAlumno.get(position).getDireccion());
        holder.email.setText(listaAlumno.get(position).getEmail());
        holder.fecha.setText(listaAlumno.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaAlumno.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        TextView documento,nombre,telefono,direccion,email,fecha;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            documento = (TextView) itemView.findViewById(R.id.textDocumento);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            telefono = (TextView) itemView.findViewById(R.id.textTelefono);

            direccion = (TextView) itemView.findViewById(R.id.textDireccion);
            email = (TextView) itemView.findViewById(R.id.textEmail);
            fecha = (TextView) itemView.findViewById(R.id.textFecha);
        }
    }
}
