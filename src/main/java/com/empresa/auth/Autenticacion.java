package com.empresa.auth;

import java.util.HashMap;
import java.util.Map;

public class Autenticacion {

    private static class Usuario {
        String contrasena;
        boolean bloqueado;
        Usuario(String contrasena, boolean bloqueado) {
            this.contrasena = contrasena;
            this.bloqueado = bloqueado;
        }
    }

    private final Map<String, Usuario> usuarios = new HashMap<>();

    public Autenticacion() {
        usuarios.put("ana", new Usuario("1234", false));
        usuarios.put("carlos", new Usuario("abcd", true)); // bloqueado
    }

    public String login(String usuario, String contrasena) {
        Usuario u = usuarios.get(usuario);
        if (u == null) return "Credenciales inválidas";
        if (u.bloqueado) return "Usuario bloqueado";
        if (!u.contrasena.equals(contrasena)) return "Credenciales inválidas";
        return "OK";
    }
}
