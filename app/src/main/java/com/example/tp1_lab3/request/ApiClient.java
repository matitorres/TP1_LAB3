package com.example.tp1_lab3.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tp1_lab3.models.Usuario;

public class ApiClient {

    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if (sp == null) {
            sp = context.getSharedPreferences("usuario", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario) {
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("dni", usuario.getDni());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("nombre", usuario.getNombre());
        editor.putString("mail", usuario.getMail());
        editor.putString("clave", usuario.getClave());
        editor.commit();
    }

    public static Usuario leer(Context context) {
        SharedPreferences sp = conectar(context);
        String dni = sp.getString("dni","-1");
        String apellido = sp.getString("apellido","-1");
        String nombre = sp.getString("nombre","-1");
        String mail= sp.getString("mail","-1");
        String clave = sp.getString("clave","-1");

        Usuario usuario = new Usuario(dni, apellido, nombre, mail, clave);

        return usuario;
    }

    public static Usuario login(Context context, String mailIngresado, String claveIngresada) {
        Usuario usuario = null;

        SharedPreferences sp = conectar(context);
        String mail= sp.getString("mail","-1");
        String clave = sp.getString("clave","-1");

        if (mail.equals(mailIngresado) && clave.equals(claveIngresada)) {
            String dni = sp.getString("dni","-1");
            String apellido = sp.getString("apellido","-1");
            String nombre = sp.getString("nombre","-1");
            usuario = new Usuario(dni, apellido, nombre, mail, clave);
        }

        return usuario;
    }

}
