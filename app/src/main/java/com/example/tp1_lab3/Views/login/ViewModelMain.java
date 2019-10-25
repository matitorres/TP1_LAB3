package com.example.tp1_lab3.Views.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.tp1_lab3.models.Usuario;
import com.example.tp1_lab3.request.ApiClient;

public class ViewModelMain extends ViewModel {

    private ApiClient ac = new ApiClient();

    public Usuario iniciarSesion(Context contexto, String mail, String clave) {
        return ac.login(contexto, mail, clave);
    }

}
