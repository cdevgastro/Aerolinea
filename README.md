# Aerolinea
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

Trabajar según: https://sergioiramosrodriguez.notion.site/TALLER-DE-GIT-TRABAJAR-CON-VERSIONES-DE-C-DIGO-1d97dc35a83180cbbd5ed4e78dd88bce


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
