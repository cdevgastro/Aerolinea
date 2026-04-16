import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Reserva.
 * Hereda de JpaRepository para obtener operaciones CRUD estándar.
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {

    /**
     * Busca una reserva por el DNI del usuario.
     * Spring Data JPA genera la consulta automáticamente basándose en el nombre del método.
     *
     * @param dni El DNI que actúa como identificador del usuario en la reserva.
     * @return Un Optional que contiene la reserva si se encuentra.
     */
    Optional<Reserva> findByDni(String dni);

    /**
     * Verifica si ya existe una reserva registrada para un DNI específico.
     *
     * @param dni El DNI a verificar.
     * @return true si existe, false en caso contrario.
     */
    boolean existsByDni(String dni);
}