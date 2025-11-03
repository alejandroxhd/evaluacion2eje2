#language: es
Característica: Login de usuarios
  Como usuario de la plataforma
  Quiero iniciar sesión con mis credenciales
  Para acceder a mis funcionalidades

  Antecedentes:
    Dado existe el servicio de autenticación con usuarios preconfigurados

  Escenario: Login exitoso con credenciales válidas
    Cuando intento iniciar sesión con usuario "ana" y contraseña "1234"
    Entonces el resultado del login debe ser "OK"

  Esquema del escenario: Login inválido o bloqueado
    Cuando intento iniciar sesión con usuario "<usuario>" y contraseña "<contrasena>"
    Entonces el resultado del login debe ser "<mensaje>"

    Ejemplos:
      | usuario | contrasena | mensaje                |
      | ana     | xxxx       | Credenciales inválidas |
      | carlos  | abcd       | Usuario bloqueado      |
