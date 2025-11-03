
# Proyecto EVA2 Actividad 2 — Integración Continua con BDD y Performance

## Objetivo
Implementar un flujo de Integración Continua (CI) con pruebas automatizadas (unitarias, BDD y de rendimiento) aplicadas a una funcionalidad simple de login de usuario.

El proyecto demuestra cómo integrar Cucumber (BDD), JUnit 5, JMeter y JaCoCo dentro de un pipeline en GitHub Actions.

---

## Estructura principal del proyecto

```
eva2actividad2/
├─ .github/workflows/ci.yml         → Pipeline CI/CD
├─ pom.xml                          → Configuración Maven y plugins
├─ src/
│  ├─ main/java/com/empresa/auth/Autenticacion.java
│  └─ test/
│     ├─ java/com/empresa/bdd/steps/LoginSteps.java
│     ├─ java/com/empresa/bdd/steps/RunCucumberTest.java
│     ├─ resources/features/login.feature
│     └─ jmeter/login-smoke.jmx
└─ target/
   ├─ cucumber-report.html          → Reporte navegable BDD
   ├─ site/jacoco/index.html        → Reporte de cobertura
   └─ jmeter/reports/.../index.html → Reporte de performance
```

---

## Sesión “Three Amigos”
- Negocio: define que el login debe devolver “OK”, “Credenciales inválidas” o “Usuario bloqueado”.
- QA: crea los criterios de aceptación y escenarios BDD.
- Desarrollador: implementa el servicio Autenticacion y los step definitions en Java.

**Usuarios de prueba:**
| Usuario | Contraseña | Estado     |
|----------|-------------|-----------|
| ana      | 1234        | Activo    |
| carlos   | abcd        | Bloqueado |

---

## Escenarios BDD (Gherkin)

Archivo: `src/test/resources/features/login.feature`

```gherkin
#language: es
Característica: Login de usuarios
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
```

---

## Pipeline CI/CD

Archivo: `.github/workflows/ci.yml`

### Etapas del flujo
1. Compilación y ejecución de pruebas BDD (Cucumber/JUnit).
2. Ejecución de pruebas de performance (JMeter).
3. Generación de reportes HTML (Cucumber, JaCoCo, JMeter).
4. Publicación de artefactos en el job de Actions.

---

## Reportes navegables generados

| Tipo de reporte | Ubicación | Descripción |
|-----------------|------------|--------------|
| BDD (Cucumber) | target/cucumber-report.html | Resultados de escenarios y pasos. |
| Cobertura (JaCoCo) | target/site/jacoco/index.html | Porcentaje de líneas cubiertas. |
| Performance (JMeter) | target/jmeter/reports/.../index.html | Métricas de throughput, latencia y errores. |

---

## Prueba de Performance (JMeter)
Archivo: `src/test/jmeter/login-smoke.jmx`

- Usuarios simulados: 5  
- Ramp-up: 5 segundos  
- Duración: 20 segundos  
- Sampler: Debug Sampler (simulación sin conexión externa)

**Indicadores monitoreados:**
- TPS (Throughput, transacciones por segundo)
- Latencia promedio y percentiles (p95)
- Porcentaje de errores

---

## Métricas en el dashboard del pipeline

En el archivo `ci.yml` se agregaron pasos para publicar las métricas en el Job Summary:

```yaml
- name: Publicar métricas en dashboard
  if: always()
  run: |
    echo "## Dashboard de pruebas" >> $GITHUB_STEP_SUMMARY
    echo "- Reporte funcional: target/cucumber-report.html" >> $GITHUB_STEP_SUMMARY
    echo "- Reporte performance: target/jmeter/reports/.../index.html" >> $GITHUB_STEP_SUMMARY
    echo "- Cobertura: target/site/jacoco/index.html" >> $GITHUB_STEP_SUMMARY
```

---

## Capturas sugeridas
1. Ejecución de los escenarios BDD (consola o reporte HTML).  
2. Reporte HTML de Cucumber (target/cucumber-report.html).  
3. Reporte de cobertura JaCoCo (target/site/jacoco/index.html).  
4. Reporte de performance JMeter (target/jmeter/reports/.../index.html).  
5. Dashboard de Actions con los artefactos subidos.

---

## Conclusión
El proyecto eva2actividad2 demuestra un flujo completo de Integración Continua con:
- Automatización de pruebas funcionales (BDD).
- Métricas de cobertura (JaCoCo).
- Pruebas de rendimiento (JMeter).
- Reportes navegables.
- Publicación automática en el pipeline CI/CD.

Esto garantiza trazabilidad, visibilidad de calidad y control de rendimiento en cada build.
