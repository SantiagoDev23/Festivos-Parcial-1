package festivos.api.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festivos.api.core.repositorios.ITipoFestivoRepositorio;
import festivos.api.core.servicios.ITipoFestivoServicio;
import festivos.api.dominio.entidades.TipoFestivo;

@Service
public class TipoFestivoServicio implements ITipoFestivoServicio {

	@Autowired
	private ITipoFestivoRepositorio repositorio;

	@Override
	public List<TipoFestivo> listar() {
		return repositorio.listar();
	}

	@Override
	public TipoFestivo obtener(int id) {
		var tipoEncontrado = repositorio.obtenerPorId(id);
		return tipoEncontrado.isEmpty() ? null : tipoEncontrado.get();
	}

	@Override
	public List<TipoFestivo> buscar(String tipo) {
		return repositorio.buscarPorTipo(tipo);
	}

	@Override
	public TipoFestivo agregar(TipoFestivo tipo) {
		tipo.setId(0);
		return repositorio.guardar(tipo);
	}

	@Override
	public TipoFestivo modificar(TipoFestivo tipo) {
		var tipoEncontrado = repositorio.obtenerPorId(tipo.getId());
		return tipoEncontrado.isEmpty() ? null : repositorio.guardar(tipo);
	}

	@Override
	public boolean eliminar(int id) {
		return repositorio.eliminar(id);
	}

}
