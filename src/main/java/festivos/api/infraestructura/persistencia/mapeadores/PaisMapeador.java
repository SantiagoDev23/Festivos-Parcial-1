package festivos.api.infraestructura.persistencia.mapeadores;

import festivos.api.dominio.entidades.Pais;
import festivos.api.infraestructura.persistencia.entidades.PaisEntidad;

public class PaisMapeador {

    public static Pais haciaDominio(PaisEntidad entidad) {
        if (entidad == null)
            return null;

        return new Pais(
                entidad.getId(),
                entidad.getNombre());
    }

    public static PaisEntidad haciaEntidad(Pais pais) {
        if (pais == null)
            return null;

        return new PaisEntidad(
                pais.getId(),
                pais.getNombre());
    }
}
