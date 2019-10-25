package com.example.tp1_lab3.Views.details;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp1_lab3.models.Usuario;
import com.example.tp1_lab3.request.ApiClient;

public class ViewModelDetails extends ViewModel {

    private ApiClient ac = new ApiClient();
    private MutableLiveData<Usuario> mutableLiveDataUsuario;

    public LiveData<Usuario> getMutableLiveDataUsuario() {
        if(mutableLiveDataUsuario == null) {
            mutableLiveDataUsuario = new MutableLiveData<Usuario>();
        }
        return mutableLiveDataUsuario;
    }

    public void mostrarUsuario(Context contexto) {
        Usuario usuario = ac.leer(contexto);
        mutableLiveDataUsuario.setValue(usuario);
    }

    public void guardarUsuario(Context contexto, Usuario usuario) {
        ac.guardar(contexto, usuario);
    }

}
