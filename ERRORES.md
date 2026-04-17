# Análisis de Errores - Proyecto Aerolínea

## Estado de Corrección: ⏱️ En progreso

- [ ] **Fecha análisis:** 2026-04-17
- **Rama:** `feature/test-data-initializer`

---

## 1. Errores Críticos

### 1.1 [ ] Import Duplicado
- **Archivo:** `src/main/java/com/gastro/aerolinea/vuelo/Vuelo.java`
- **Línea:** 7 (eliminado)
- **Descripción:** ~~Import duplicado de `Avion`~~ - **CORREGIDO**

### 1.2 [ ] Exposición de Entidad en DTO
- **Archivos:** 
  - `src/main/java/com/gastro/aerolinea/vuelo/VueloMapper.java:26,46`
  - `src/main/java/com/gastro/aerolinea/vuelo/VueloDTO.java`
- **Descripción:** El `VueloDTO` incluye `Avion avion` completo en lugar de solo la matrícula. Esto viola el patrón DTO.
- **Estado:** ❌ Sin corregir

### 1.3 [ ] Sin Manejo Global de Excepciones
- **Descripción:** No existe `@ControllerAdvice`. Los `RuntimeException` devuelven 500 genéricos.
- **Estado:** ❌ Sin corregir

### 1.4 [ ] Sistema de Reservas No Implementado
- **Descripción:** Falta la entidad `Reserva` completamente según requisitos del README.
- **Estado:** ❌ Sin implementar

---

## 2. Errores Funcionales

### 2.1 [ ] Query buscarDisponibles() Incompleta
- **Archivo:** `src/main/java/com/gastro/aerolinea/vuelo/VueloRepository.java:13`
- **Descripción:** Filtra solo por estado "DISPONIBLE" pero no verifica plazas libres (capacidad vs reservas).
- **Estado:** ❌ Sin corregir

### 2.2 [ ] Posible Error al Actualizar Avión
- **Archivo:** `src/main/java/com/gastro/aerolinea/avion/AvionService.java:46`
- **Descripción:** Permite cambiar matrícula (PK única) de avión con vuelos asociados.
- **Estado:** ❌ Sin corregir

---

## 3. Warnings y Mejoras

### 3.1 [ ] Deprecated @MockBean
- **Archivos de test:** 
  - `AvionControllerTest.java:26`
  - `VueloControllerTest.java:28`
- **Descripción:** `@MockBean` deprecated en Spring Boot 3.5+. Usar `@MockitoBean`.
- **Estado:** ❌ Sin corregir

### 3.2 [ ] Falta @Transactional en Services
- **Descripción:** `AvionService` y `VueloServiceImpl` no tienen `@Transactional`.
- **Estado:** ❌ Sin corregir

### 3.3 [ ] Sin Validaciones @Valid en Controllers
- **Descripción:** Endpoints sin `@Valid` para validar DTOs entrantes.
- **Estado:** ❌ Sin corregir

---

## 4. Requisitos Pendientes (según README.md)

| # | Requisito | Endpoint | Estado | Checkbox |
| :---: | :--- | :--- | :---: | :--- |
| 1 | Registrar avión | `POST /api/aviones` | ✅ | [ ] |
| 2 | Consultar todos | `GET /api/aviones` | ✅ | [ ] |
| 3 | Consultar por matrícula | `GET /api/aviones/{matricula}` | ✅ | [ ] |
| 4 | Actualizar avión | `PUT /api/aviones/{matricula}` | ⚠️ | [ ] |
| 5 | Eliminar avión | `DELETE /api/aviones/{matricula}` | ✅ | [ ] |
| 6 | Crear vuelo | `POST /api/vuelos` | ✅ | [ ] |
| 7 | Consultar todos | `GET /api/vuelos` | ✅ | [ ] |
| 8 | Vuelos disponibles | `GET /api/vuelos/disponibles` | ⚠️ | [ ] |
| 9 | Buscar por ruta | `GET /api/vuelos/buscar?origen=X&destino=Y` | ✅ | [ ] |
| 10 | Cambiar estado | `PATCH /api/vuelos/{id}/estado` | ✅ | [ ] |
| 11 | Cancelar vuelo | `DELETE /api/vuelos/{id}/cancelar` | ✅ | [ ] |
| 12 | Reservas pasajero | `GET /api/reservas/pasajero/{dni}` | ❌ | [ ] |
| 13 | Reservas vuelo | `GET /api/reservas/vuelo/{id}` | ❌ | [ ] |
| 14 | Crear reserva | `POST /api/reservas` | ❌ | [ ] |
| 15 | Cancelar reserva | `DELETE /api/reservas/{localizador}` | ❌ | [ ] |

---

## 5. Checklist de Correcciones

### Urgente (Bloqueantes)
- [ ] Crear `@ControllerAdvice` para excepciones globales
- [ ] Corregir `VueloDTO` para no exponer entidad `Avion`
- [ ] Implementar entidad `Reserva` completa (entity, repository, service, controller)

### Alto
- [ ] Corregir query `buscarDisponibles()` para verificar capacidad
- [ ] Evitar cambio de matrícula en `AvionService.actualizar()`
- [ ] Agregar validaciones `@Valid` en controllers

### Medio
- [ ] Reemplazar `@MockBean` por `@MockitoBean`
- [ ] Agregar `@Transactional` a servicios

---

## 6. Resumen

| Severidad | Cantidad |
| :--- | :---: |
| 🔴 Críticos | 4 |
| 🟡 Funcionales | 2 |
| 🟢 Warnings | 3 |
| **Total** | **9** |

| Estado | Cantidad |
| :--- | :---: |
| ❌ Sin corregir | 9 |
| ✅ Corregido | 1 |
| ⏳ En progreso | 0 |