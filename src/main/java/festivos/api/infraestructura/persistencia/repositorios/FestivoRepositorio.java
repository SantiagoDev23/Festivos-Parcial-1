package festivos.api.infraestructura.persistencia.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import festivos.api.core.repositorios.IFestivoRepositorio;
import festivos.api.dominio.entidades.Festivo;
import festivos.api.infraestructura.persistencia.entidades.FestivoEntidad;
import festivos.api.infraestructura.persistencia.mapeadores.FestivoMapeador;
import festivos.api.infraestructura.persistencia.repositorios.jpa.IFestivoRepositorioJpa;

@Component
public class FestivoRepositorio implements IFestivoRepositorio {

    @Autowired
    private IFestivoRepositorioJpa repositorio;

    @Override
    public List<Festivo> listar() {
        return repositorio.findAll()
                .stream()
                .map(FestivoMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Festivo> obtenerPorId(int id) {
        return repositorio.findById(id)
                .map(FestivoMapeador::haciaDominio);
    }

    @Override
    public List<Festivo> buscarPorNombre(String nombre) {
        return repositorio.findByNombreContaining(nombre)
                .stream()
                .map(FestivoMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Festivo> listarPorPais(int idPais) {
        return repositorio.listarPorPais(idPais)
                .stream()
                .map(FestivoMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Festivo> listarPorTipo(int idTipo) {
        return repositorio.listarPorTipo(idTipo)
                .stream()
                .map(FestivoMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Festivo guardar(Festivo festivo) {
        FestivoEntidad entidad = FestivoMapeador.haciaEntidad(festivo);
        FestivoEntidad entidadGuardada = repositorio.save(entidad);
        return FestivoMapeador.haciaDominio(entidadGuardada);
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
