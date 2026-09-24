package festivos.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import festivos.api.infraestructura.persistencia.entidades.TipoFestivoEntidad;

@Repository
public interface ITipoFestivoRepositorioJpa extends JpaRepository<TipoFestivoEntidad, Integer> {

    List<TipoFestivoEntidad> findByTipoContaining(String tipo);

}
