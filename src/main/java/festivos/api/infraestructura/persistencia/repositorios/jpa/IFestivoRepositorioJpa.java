package festivos.api.infraestructura.persistencia.repositorios.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import festivos.api.infraestructura.persistencia.entidades.FestivoEntidad;

@Repository
public interface IFestivoRepositorioJpa extends JpaRepository<FestivoEntidad, Integer> {

    List<FestivoEntidad> findByNombreContaining(String nombre);

    @Query("SELECT f FROM FestivoEntidad f WHERE f.pais.id=:idPais ORDER BY f.mes, f.dia")
    List<FestivoEntidad> listarPorPais(int idPais);

    @Query("SELECT f FROM FestivoEntidad f WHERE f.tipo.id=:idTipo ORDER BY f.mes, f.dia")
    List<FestivoEntidad> listarPorTipo(int idTipo);

}
