# Aerolinea

## 0. Comandos git diarios
```bash
# 1. Asegurarse de que estas en la rama principal
git checkout main

# 2. Actualizar los cambios en el repo remoto
git fetch --prune

# 3. Actualizar la rama local
git pull origin main

# 4. Revisar las ramas remotas (en rojo)
git branch -a
```

### 0.A) Caso práctico: ¿Cómo ver los cambios realizados por un compañero?
1) La rama del compañero debe estar subido al repo
2) Ejecutar los siguientes comandos
```bash
# 1. Asegurarse de que estas en la rama principal
git checkout main

# 2. Actualizar los cambios en el repo remoto
git fetch --prune

# 3. Actualizar la rama local
git pull origin main

# 4. Revisar las ramas remotas (en rojo)
git branch -a

# 5. Buscar la rama del compañero y cambiarse de rama
git checkout <rama_del_compañero>
```

### 0.B) Caso práctico: ¿Qué se debe hacer al final del día?
1) Subir los cambios al repo remoto
```bash
# 1. Confirmar que se está en la rama de desarrollo (bugfix, feat, hotfix, docs...)
git branch

# 2. Cambiarse de rama si es necesario
git checkout <rama_desarrollo>

# 3. Hacer el commit y subir cambios
git add .
git commit -m "Cambios guardados"
git push
```

2) Eliminar ramas no usadas y obsoletas en local
```bash
# 1. Listar las ramas
git branch

# 2. Eliminar las ramas antiguas, en las que ya no se trabaja
git branch -D <rama_antigua_A> <rama_antigua_B>...

# 3. Comprobar que solo se tienen dos ramas en local: main y la rama en la que estamos trabajando
git branch
```

## 1. Contexto
Una aerolínea necesita digitalizar sus operaciones. Se pide desarrollar una API REST con Spring Boot que gestione la flota de aviones, los vuelos y las reservas de billetes de los pasajeros.
La aplicación debe persistir los datos en una base de datos en memoria H2 y exponer endpoints REST bien estructurados siguiendo buenas prácticas.

# Reglas del proyecto
- Usar nombres de clases, propiedades y métodos en español.
- Estructura de carpetas organizada por funcionalidades o features
```bash
src/main/java/com/tuempresa/aerolinea
 ├── pasajero
 │    ├── PasajeroController.java
 │    ├── PasajeroService.java
 │    ├── PasajeroRepository.java
 │    ├── Pasajero.java
 │    ├── PasajeroDTO.java
 │    └── PasajeroMapper.java
 │
 ├── vuelo
 │    ├── VueloController.java
 │    ├── VueloService.java
 │    ├── VueloRepository.java
 │    ├── Vuelo.java
 │    ├── VueloDTO.java
 │    └── VueloMapper.java
 │
 ├── avion
 │    ├── AvionController.java
 │    ├── AvionService.java
 │    ├── AvionRepository.java
 │    ├── Avion.java
 │    ├── AvionDTO.java
 │    └── AvionMapper.java
 │
 ├── reserva
 │    ├── ReservaController.java
 │    ├── ReservaService.java
 │    ├── ReservaRepository.java
 │    ├── Reserva.java
 │    ├── ReservaDTO.java
 │    └── ReservaMapper.java
 │
 ├── config
 ├── exception
 └── Main.java
```

- Trabajar según:
#### Escenario B: Unirte a un proyecto que ya existe

```bash
# 1. Clona el repositorio
git clone https://github.com/cdevgastro/Aerolinea.git

# 2. Entra en la carpeta del proyecto
cd Aerolinea

# 3. Comprueba en qué rama estás
git branch
# La rama marcada con * es en la que estás

# 4. Crea tu propia rama para trabajar (no trabajes directamente en main)
git checkout -b feature/[funcion a implementar]

# 5. Haz tus cambios en el código...

# 6. Comprueba qué has modificado
git status

# 7. Revisa los cambios en detalle antes de confirmarlos
git diff

# 8. Prepara los cambios para el commit
git add .

# 9. Guarda el punto de control con un mensaje claro
git commit -m "feat: añadir formulario de login con validación"

# 10. Sube tu rama a GitHub
git push origin feature/formulario-login
```
más información: https://sergioiramosrodriguez.notion.site/TALLER-DE-GIT-TRABAJAR-CON-VERSIONES-DE-C-DIGO-1d97dc35a83180cbbd5ed4e78dd88bce


## 2. Requisitos funcionales

### 2.1 Gestión de Aviones
- Registrar un nuevo avión con los campos: matrícula (única), modelo, fabricante, capacidad total de pasajeros y año de fabricación.
- Consultar todos los aviones registrados.
- Consultar un avión por su matrícula.
- Actualizar los datos de un avión.
- Eliminar un avión (solo si no tiene vuelos activos asociados).

### 2.2 Gestión de Vuelos
- Crear un vuelo con los campos: número de vuelo (único), origen, destino, fecha y hora de salida, fecha y hora de llegada, precio base y avión asignado.
- Consultar todos los vuelos.
- Consultar vuelos por origen y destino
- Consultar vuelos disponibles (con plazas libres)
- Actualizar el estado de un vuelo (PROGRAMADO, EN_VUELO, ATERRIZADO, CANCELADO)
- Cancelar un vuelo (cambia estado y libera las reservas asociadas).

### 2.3 Sistema de Reservas
- Crear una reserva para un pasajero en un vuelo concreto.
- La reserva debe generar automáticamente un localizador único.
- No se puede reservar si el vuelo está lleno o cancelado.
- Consultar todas las reservas de un pasajero por su DNI/pasaporte.
- Consultar las reservas de un vuelo.
- Cancelar una reserva (libera la plaza en el vuelo).
Aquí tienes el texto convertido a un formato **Markdown** limpio y estructurado, utilizando tablas y listas para mejorar la legibilidad:

---

### 3. Modelo de datos

| Entidad | Campo clave | Relaciones |
| :--- | :--- | :--- |
| **Avion** | `matricula` (String, PK) | `OneToMany` -> Vuelo |
| **Vuelo** | `numeroVuelo` (String, PK) | `ManyToOne` -> Avion \| `OneToMany` -> Reserva |
| **Reserva** | `id` (Long, PK) | `ManyToOne` -> Vuelo |
| **Pasajero** | `id` (Long, PK) | `OneToMany` -> Reserva |

---

### 4. Endpoints requeridos

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/aviones` | Listar todos los aviones |
| `GET` | `/api/aviones/{matricula}` | Obtener avión por matrícula |
| `POST` | `/api/aviones` | Registrar nuevo avión |
| `PUT` | `/api/aviones/{matricula}` | Actualizar avión |
| `DELETE` | `/api/aviones/{matricula}` | Eliminar avión |
| `GET` | `/api/vuelos` | Listar todos los vuelos |
| `GET` | `/api/vuelos/disponibles` | Vuelos con plazas libres |
| `GET` | `/api/vuelos/buscar?origen=X&destino=Y` | Buscar vuelos por ruta |
| `POST` | `/api/vuelos` | Crear vuelo |
| `PATCH` | `/api/vuelos/{id}/estado` | Cambiar estado del vuelo |
| `DELETE` | `/api/vuelos/{id}/cancelar` | Cancelar vuelo |
| `GET` | `/api/reservas/pasajero/{dni}` | Reservas de un pasajero |
| `GET` | `/api/reservas/vuelo/{id}` | Reservas de un vuelo |
| `POST` | `/api/reservas` | Crear nueva reserva |
| `DELETE` | `/api/reservas/{localizador}` | Cancelar reserva |

---

### 5. Requisitos técnicos

* **Proyecto Maven** con Spring Boot 4.x.
* **Base de datos H2** en memoria con consola habilitada en `/h2-console`.
* Uso de **Spring Data JPA** con Hibernate como ORM.
* Uso de **Lombok** para reducir el *boilerplate* (getters, setters, constructores).
* **DTOs** para las peticiones y respuestas (no exponer entidades directamente).
* **Validaciones** con Bean Validation (`@NotBlank`, `@Min`, `@Future`, etc.).
* Manejo global de excepciones con `@ControllerAdvice`.
* Los datos de prueba deben cargarse mediante un `CommandLineRunner` al arrancar.

---

### 6. Estructura del equipo

> Antes de empezar, asigna roles reales a cada alumno. En grupos de 4-5 personas:

| Rol | Responsabilidad |
| :--- | :--- |
| **Tech Lead** | Decisiones de arquitectura, revisión de PRs, merge a main |
| **Backend Dev x2** | Implementación de entidades, servicios y controladores |
| **QA / Tester** | Pruebas con Postman, validar requisitos |
| **DevOps / Scrum Master** | Gestión del tablero, pipeline CI/CD, despliegue |
