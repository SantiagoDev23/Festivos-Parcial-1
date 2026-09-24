package festivos.api.infraestructura.persistencia.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import festivos.api.core.repositorios.ITipoFestivoRepositorio;
import festivos.api.dominio.entidades.TipoFestivo;
import festivos.api.infraestructura.persistencia.entidades.TipoFestivoEntidad;
import festivos.api.infraestructura.persistencia.mapeadores.TipoFestivoMapeador;
import festivos.api.infraestructura.persistencia.repositorios.jpa.ITipoFestivoRepositorioJpa;

@Component
public class TipoFestivoRepositorio implements ITipoFestivoRepositorio {

    @Autowired
    private ITipoFestivoRepositorioJpa repositorio;

    @Override
    public List<TipoFestivo> listar() {
        return repositorio.findAll()
                .stream()
                .map(TipoFestivoMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoFestivo> obtenerPorId(int id) {
        return repositorio.findById(id)
                .map(TipoFestivoMapeador::haciaDominio);
    }

    @Override
    public List<TipoFestivo> buscarPorTipo(String tipo) {
        return repositorio.findByTipoContaining(tipo)
                .stream()
                .map(TipoFestivoMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public TipoFestivo guardar(TipoFestivo tipo) {
        TipoFestivoEntidad entidad = TipoFestivoMapeador.haciaEntidad(tipo);
        TipoFestivoEntidad entidadGuardada = repositorio.save(entidad);
        return TipoFestivoMapeador.haciaDominio(entidadGuardada);
    }

    @Override
    public boolean eliminar(int id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

}
