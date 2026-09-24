package festivos.api.infraestructura.persistencia.mapeadores;

import festivos.api.dominio.entidades.Festivo;
import festivos.api.infraestructura.persistencia.entidades.FestivoEntidad;

public class FestivoMapeador {

    public static Festivo haciaDominio(FestivoEntidad entidad) {
        if (entidad == null)
            return null;

        return new Festivo(
                entidad.getId(),
                PaisMapeador.haciaDominio(entidad.getPais()),
                entidad.getNombre(),
                entidad.getDia(),
                entidad.getMes(),
                entidad.getDiasPascua(),
                TipoFestivoMapeador.haciaDominio(entidad.getTipo()));
    }

    public static FestivoEntidad haciaEntidad(Festivo festivo) {
        if (festivo == null)
            return null;

        return new FestivoEntidad(
                festivo.getId(),
                PaisMapeador.haciaEntidad(festivo.getPais()),
                festivo.getNombre(),
                festivo.getDia(),
                festivo.getMes(),
                festivo.getDiasPascua(),
                TipoFestivoMapeador.haciaEntidad(festivo.getTipo()));
    }
}
