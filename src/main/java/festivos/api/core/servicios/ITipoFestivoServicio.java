package festivos.api.core.servicios;

import java.util.List;

import festivos.api.dominio.entidades.TipoFestivo;

public interface ITipoFestivoServicio {

    List<TipoFestivo> listar();

    TipoFestivo obtener(int id);

    List<TipoFestivo> buscar(String tipo);

    TipoFestivo agregar(TipoFestivo tipo);

    TipoFestivo modificar(TipoFestivo tipo);

    boolean eliminar(int id);
}