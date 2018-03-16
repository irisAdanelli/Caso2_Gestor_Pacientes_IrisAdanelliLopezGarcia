package mx.edu.ittepic.caso2_gestor_pacientes_irisadanellilopezgarcia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button btnSalir, btnObras, btnTrabajadores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fnStrartingButtons();
        btnTrabajadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goWorkers = new Intent(getApplicationContext(),Clientes.class);
                startActivity(goWorkers);
            }
        });

        btnObras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBuildings = new Intent(getApplicationContext(),Buildings.class);
                startActivity(goBuildings);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void fnStrartingButtons(){
        btnSalir = findViewById(R.id.btnSalir);
        btnObras = findViewById(R.id.btnRecetas);
        btnTrabajadores = findViewById(R.id.btnClientes);
    }
}
