package festivos.api.infraestructura.persistencia.mapeadores;

import festivos.api.dominio.entidades.TipoFestivo;
import festivos.api.infraestructura.persistencia.entidades.TipoFestivoEntidad;

public class TipoFestivoMapeador {

    public static TipoFestivo haciaDominio(TipoFestivoEntidad entidad) {
        if (entidad == null)
            return null;

        return new TipoFestivo(
                entidad.getId(),
                entidad.getTipo());
    }

    public static TipoFestivoEntidad haciaEntidad(TipoFestivo tipo) {
        if (tipo == null)
            return null;

        return new TipoFestivoEntidad(
                tipo.getId(),
                tipo.getTipo());
    }
}
