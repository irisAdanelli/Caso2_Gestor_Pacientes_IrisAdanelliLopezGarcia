package co.quindio.sena.ejemplosqlite.alumnos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import co.quindio.sena.ejemplosqlite.R;
import co.quindio.sena.ejemplosqlite.entidades.Alumno;

public class DetallePaciente extends AppCompatActivity {

    TextView campoId, campoNombre, campoTelefono,campoDireccion,campoFecha,campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        campoId = (TextView) findViewById(R.id.campoId);
        campoNombre = (TextView) findViewById(R.id.campoNombre);
        campoTelefono = (TextView) findViewById(R.id.campoTelefono);
        campoDireccion = (TextView) findViewById(R.id.campoDireccion);
        campoFecha = (TextView) findViewById(R.id.campoFecha);
        campoEmail = (TextView) findViewById(R.id.campoEmail);

        Bundle objetoEnviado=getIntent().getExtras();
        Alumno user=null;

        if(objetoEnviado!=null){
            user= (Alumno) objetoEnviado.getSerializable("usuario");
            campoId.setText(user.getId().toString());
            campoNombre.setText(user.getNombre().toString());
            campoTelefono.setText(user.getTelefono().toString());
            campoDireccion.setText(user.getDireccion().toString());
            campoEmail.setText(user.getEmail().toString());
            campoFecha.setText(user.getFecha().toString());


        }

    }
}
