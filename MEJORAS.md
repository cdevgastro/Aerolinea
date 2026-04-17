# Mejoras Potenciales - Proyecto Aerolínea

## 1. Arquitectura y Diseño

### 1.1 Usar interfaces para Services
**Archivo:** `AvionService.java`, `VueloService.java`
- **Mejora:** `AvionService` debería ser interfaz como `VueloService`

```java
// AvionService actual es una clase concreta
public class AvionService { ... }

// Recomendado: interfaz + implementación
public interface AvionService { ... }

@Service
public class AvionServiceImpl implements AvionService { ... }
```

### 1.2 Inyección por constructor con `@RequiredArgsConstructor`
- **Archivos:** `AvionController.java`, `VueloController.java`
- **Mejora:** Usar Lombok para reducir boilerplate

```java
@RestController
@RequestMapping("/api/aviones")
@RequiredArgsConstructor  // Añadir
public class AvionController {
    private final AvionService avionService;
    // Constructor generado automáticamente
}
```

---

## 2. Validaciones

### 2.1 Agregar `@Valid` en Controllers
- **Archivos:** `AvionController.java`, `VueloController.java`
- **Mejora:** Validar DTOs entrantes

```java
@PostMapping
public ResponseEntity<AvionDTO> crear(@Valid @RequestBody AvionDTO avionDTO) {
//                                                      ^^^^^^^^
```

### 2.2 Anotaciones de validación en DTOs
- **Archivos:** `AvionDTO.java`, `VueloDTO.java`
- **Mejora:** Agregar restricciones

```java
@Data
public class AvionDTO {
    @NotBlank(message = "La matrícula es obligatoria")
    private String matricula;
    
    @NotNull(message = "El modelo es obligatorio")
    private String modelo;
    
    @Min(value = 1900, message = "Año inválido")
    private int anioFabricacion;
}
```

---

## 3. Exception Handling

### 3.1 Crear excepciones personalizadas
- **Mejora:** Usar excepciones específicas en lugar de `RuntimeException`

```java
// Crear en paquete exception/
public class AvionNoEncontradoException extends RuntimeException {
    public AvionNoEncontradoException(String matricula) {
        super("Avión no encontrado con matrícula: " + matricula);
    }
}

public class AvionDuplicadoException extends RuntimeException {
    public AvionDuplicadoException(String matricula) {
        super("Ya existe un avión con matrícula: " + matricula);
    }
}
```

### 3.2 Crear `@ControllerAdvice`
- **Mejora:** Manejo centralizado de errores

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(AvionNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleAvionNoEncontrado(...) {
        // Devolver 404 con mensaje claro
    }
}
```

---

## 4. Documentación

### 4.1 Agregar OpenAPI/Swagger
- **Archivo:** `pom.xml`
- **Mejora:** Documentar endpoints automáticamente

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

---

## 5. Testing

### 5.1 Tests de Services
- **Mejora:** Agregar tests unitarios para servicios

### 5.2 Tests de Integración
- **Mejora:** Usar `@DataJpaTest` para repositories

---

## 6. Seguridad

### 6.1 Validar matrícula en AvionController
- **Mejora:** Verificar que no existe duplicado antes de crear

### 6.2 Proteger endpoint de eliminación
- **Mejora:** Verificar que el avión no tiene vuelos asociados

---

## 7. Código

### 7.1 Lombok en AvionService
- **Archivo:** `AvionService.java`
- **Mejora:** Usar `@RequiredArgsConstructor`

```java
@Service
@RequiredArgsConstructor  // Añadir
public class AvionService {
    private final AvionRepository avionRepository;
}
```

### 7.2 Eliminar import no usado
- **Archivo:** `VueloController.java:3`
- **Mejora:** `import java.util.List` - verificar si se usa

---

## Priorización

| Prioridad | Mejora | Impacto |
| :--- | :--- | :--- |
| Alta | `@ControllerAdvice` | Alta |
| Alta | `@Valid` + validaciones DTO | Alta |
| Media | Excepciones personalizadas | Media |
| Media | OpenAPI/Swagger | Media |
| Baja | Tests adicionales | Baja |
| Baja | Lombok en Services | Baja |