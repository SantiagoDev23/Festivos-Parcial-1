package festivos.api.aplicacion.servicios;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festivos.api.core.repositorios.IFestivoRepositorio;
import festivos.api.core.servicios.ICalendarioServicio;
import festivos.api.dominio.dtos.FestivoDto;
import festivos.api.dominio.entidades.Festivo;

@Service
public class CalendarioServicio implements ICalendarioServicio {

	private static final int TIPO_FIJO = 1;
	private static final int TIPO_PUENTE_FESTIVO = 2;
	private static final int TIPO_PASCUA = 3;
	private static final int TIPO_PASCUA_PUENTE_FESTIVO = 4;

	@Autowired
	private IFestivoRepositorio repositorio;

	@Override
	public boolean esFechaValida(int anio, int mes, int dia) {
		try {
			LocalDate.of(anio, mes, dia);
			return true;
		} catch (DateTimeException ex) {
			return false;
		}
	}

	@Override
	public boolean esFestivo(int idPais, LocalDate fecha) {
		return listarFestivos(idPais, fecha.getYear())
				.stream()
				.anyMatch(festivo -> festivo.getFecha().equals(fecha));
	}

	@Override
	public List<FestivoDto> listarFestivos(int idPais, int anio) {
		LocalDate domingoPascua = obtenerDomingoPascua(anio);
		return repositorio.listarPorPais(idPais)
				.stream()
				.map(festivo -> new FestivoDto(festivo.getNombre(), calcularFecha(festivo, anio, domingoPascua)))
				.sorted(Comparator.comparing(FestivoDto::getFecha))
				.collect(Collectors.toList());
	}

	private LocalDate calcularFecha(Festivo festivo, int anio, LocalDate domingoPascua) {
		switch (festivo.getTipo().getId()) {
			case TIPO_PUENTE_FESTIVO:
				return siguienteLunes(LocalDate.of(anio, festivo.getMes(), festivo.getDia()));
			case TIPO_PASCUA:
				return domingoPascua.plusDays(festivo.getDiasPascua());
			case TIPO_PASCUA_PUENTE_FESTIVO:
				return siguienteLunes(domingoPascua.plusDays(festivo.getDiasPascua()));
			case TIPO_FIJO:
			default:
				return LocalDate.of(anio, festivo.getMes(), festivo.getDia());
		}
	}

	private LocalDate obtenerDomingoPascua(int anio) {
		int a = anio % 19;
		int b = anio % 4;
		int c = anio % 7;
		int d = (19 * a + 24) % 30;
		int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;
		LocalDate domingoRamos = LocalDate.of(anio, 3, 15).plusDays(dias);
		return domingoRamos.plusDays(7);
	}

	private LocalDate siguienteLunes(LocalDate fecha) {
		return fecha.getDayOfWeek() == DayOfWeek.MONDAY
				? fecha
				: fecha.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
	}

}
