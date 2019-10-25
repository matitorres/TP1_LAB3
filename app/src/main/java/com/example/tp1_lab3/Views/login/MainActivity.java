package com.example.tp1_lab3.Views.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp1_lab3.R;
import com.example.tp1_lab3.Views.details.DetailsActivity;
import com.example.tp1_lab3.Views.details.ViewModelDetails;
import com.example.tp1_lab3.models.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText etMail, etClave;
    private Button btnRegistro, btnLogin;
    private ViewModelMain viewModelMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();

    }

    private void inicializar() {
        viewModelMain = ViewModelProviders.of(this).get(ViewModelMain.class);
        etMail = findViewById(R.id.etMailLogin);
        etClave = findViewById(R.id.etClaveLogin);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this/*?*/, DetailsActivity.class);
                intent.putExtra("modo", "registro");
                startActivity(intent);
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = viewModelMain.iniciarSesion(MainActivity.this, etMail.getText().toString(), etClave.getText().toString());
                if (usuario != null) {
                    Intent intent = new Intent(MainActivity.this/*?*/, DetailsActivity.class);
                    intent.putExtra("modo", "perfil");
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
