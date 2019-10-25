package com.example.tp1_lab3.Views.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp1_lab3.R;
import com.example.tp1_lab3.Views.login.MainActivity;
import com.example.tp1_lab3.models.Usuario;

public class DetailsActivity extends AppCompatActivity {

    private EditText etDni, etApellido, etNombre, etMail, etClave;
    private Button btnUsuario, btnCancelar;
    private ViewModelDetails viewModelDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        inicializar();

        String modo = getIntent().getStringExtra("modo");
        if (modo.equals("registro")){
            modoRegistro();
        } else {
            modoPerfil();
        }

    }

    private void inicializar() {
        etDni = findViewById(R.id.etDni);
        etApellido = findViewById(R.id.etApellido);
        etNombre = findViewById(R.id.etNombre);
        etMail = findViewById(R.id.etMail);
        etClave = findViewById(R.id.etClave);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this/*?*/, MainActivity.class);
                startActivity(intent);
            }
        });
        viewModelDetails = ViewModelProviders.of(this).get(ViewModelDetails.class);
    }

    public void modoRegistro() {
        btnUsuario = findViewById(R.id.btnUsuario);
        btnUsuario.setText("Crear cuenta");
        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getUsuario() != null) {
                    viewModelDetails.guardarUsuario(getApplicationContext()/*?*/, getUsuario());
                    Toast.makeText(DetailsActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailsActivity.this/*?*/, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailsActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void modoPerfil() {
        btnUsuario = findViewById(R.id.btnUsuario);
        btnUsuario.setText("Guardar cambios");
        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelDetails.guardarUsuario(getApplicationContext()/*?*/, getUsuario());
                Toast.makeText(DetailsActivity.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
            }
        });
        viewModelDetails.getMutableLiveDataUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario != null) {
                    etDni.setText(usuario.getDni());
                    etApellido.setText(usuario.getApellido());
                    etNombre.setText(usuario.getNombre());
                    etMail.setText(usuario.getMail());
                    etClave.setText(usuario.getClave());
                }
            }
        });
        viewModelDetails.mostrarUsuario(this);
    }

    private Usuario getUsuario() {
        Usuario usuario = null;

        String dni = etDni.getText().toString();
        String apellido = etApellido.getText().toString();
        String nombre = etNombre.getText().toString();
        String mail = etMail.getText().toString();
        String clave = etClave.getText().toString();

        if (!mail.equals("") && !clave.equals("")) {
            usuario = new Usuario(dni, apellido, nombre, mail, clave);
        }

        return usuario;
    }
}
