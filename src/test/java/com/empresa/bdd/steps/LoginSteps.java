package com.empresa.bdd.steps;

import com.empresa.auth.Autenticacion;
import io.cucumber.java.es.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private Autenticacion servicio;
    private String resultado;

    @Dado("existe el servicio de autenticación con usuarios preconfigurados")
    public void existeServicio() {
        servicio = new Autenticacion();
    }

    @Cuando("intento iniciar sesión con usuario {string} y contraseña {string}")
    public void intentoLogin(String usuario, String contrasena) {
        resultado = servicio.login(usuario, contrasena);
    }

    @Entonces("el resultado del login debe ser {string}")
    public void validarResultado(String esperado) {
        assertEquals(esperado, resultado);
    }
}
