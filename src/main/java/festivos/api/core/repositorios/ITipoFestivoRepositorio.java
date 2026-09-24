package festivos.api.core.repositorios;

import java.util.List;
import java.util.Optional;

import festivos.api.dominio.entidades.TipoFestivo;

public interface ITipoFestivoRepositorio {

    List<TipoFestivo> listar();

    Optional<TipoFestivo> obtenerPorId(int id);

    List<TipoFestivo> buscarPorTipo(String tipo);

    TipoFestivo guardar(TipoFestivo tipo);

    boolean eliminar(int id);

}