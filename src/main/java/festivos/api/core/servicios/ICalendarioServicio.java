package festivos.api.core.servicios;

import java.time.LocalDate;
import java.util.List;

import festivos.api.dominio.dtos.FestivoDto;

public interface ICalendarioServicio {

    boolean esFechaValida(int anio, int mes, int dia);

    boolean esFestivo(int idPais, LocalDate fecha);

    List<FestivoDto> listarFestivos(int idPais, int anio);

}
