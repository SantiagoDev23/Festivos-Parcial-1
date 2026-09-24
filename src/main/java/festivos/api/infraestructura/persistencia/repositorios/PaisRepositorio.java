package festivos.api.infraestructura.persistencia.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import festivos.api.core.repositorios.IPaisRepositorio;
import festivos.api.dominio.entidades.Pais;
import festivos.api.infraestructura.persistencia.entidades.PaisEntidad;
import festivos.api.infraestructura.persistencia.mapeadores.PaisMapeador;
import festivos.api.infraestructura.persistencia.repositorios.jpa.IPaisRepositorioJpa;

@Component
public class PaisRepositorio implements IPaisRepositorio {

    @Autowired
    private IPaisRepositorioJpa repositorio;

    @Override
    public List<Pais> listar() {
        return repositorio.findAll()
                .stream()
                .map(PaisMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pais> obtenerPorId(int id) {
        return repositorio.findById(id)
                .map(PaisMapeador::haciaDominio);
    }

    @Override
    public List<Pais> buscarPorNombre(String nombre) {
        return repositorio.findByNombreContaining(nombre)
                .stream()
                .map(PaisMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Pais guardar(Pais pais) {
        PaisEntidad entidad = PaisMapeador.haciaEntidad(pais);
        PaisEntidad entidadGuardada = repositorio.save(entidad);
        return PaisMapeador.haciaDominio(entidadGuardada);
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
