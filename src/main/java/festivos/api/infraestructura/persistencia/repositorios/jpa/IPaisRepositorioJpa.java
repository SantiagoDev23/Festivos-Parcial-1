package festivos.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import festivos.api.infraestructura.persistencia.entidades.PaisEntidad;

@Repository
public interface IPaisRepositorioJpa extends JpaRepository<PaisEntidad, Integer> {

    List<PaisEntidad> findByNombreContaining(String nombre);

}
