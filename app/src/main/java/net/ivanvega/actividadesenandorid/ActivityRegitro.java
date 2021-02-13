package net.ivanvega.actividadesenandorid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.ivanvega.actividadesenandorid.data.DAOUsuarios;
import net.ivanvega.actividadesenandorid.data.Usuario;

public class ActivityRegitro extends AppCompatActivity {

    TextView txtNombre, txtPass, txtEmail, txtTel;
    Button btnAgregar, btnCancelar;
    Spinner spnPaisds ;
    String[] arrPaises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitro);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtPass = (TextView) findViewById(R.id.txtPassword);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtTel = (TextView) findViewById(R.id.txtTel);

        String botonOK = getResources().getString(R.string.registro_modificar);



        btnAgregar = (Button) findViewById(R.id.btnGuardar);

        btnAgregar.setText(botonOK);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String pass = txtPass.getText().toString();
                String email = txtEmail.getText().toString();
                String tel = txtTel.getText().toString();
                Usuario nuevo =
                        new Usuario(nombre,tel,email,pass);


                DAOUsuarios dao =
                        new DAOUsuarios(getApplicationContext());

                if( dao.insert(nuevo)!= -1){
                    Intent intent = new Intent();
                    intent.putExtra("nuevoCliente", nuevo);
                    Toast.makeText(getApplicationContext(), "Objecto guardado dentro de Intent!", Toast.LENGTH_LONG).show();

                    setResult(Activity.RESULT_OK,  intent  );
                    finish();
                }else{
                    setResult(Activity.RESULT_CANCELED  );
                    finish();

                }


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        spnPaisds = findViewById(R.id.spnPaises);

        arrPaises =  getResources().getStringArray(R.array.paises);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item,
                        android.R.id.text1,  arrPaises);

        spnPaisds.setAdapter(adapter);

    }
}
