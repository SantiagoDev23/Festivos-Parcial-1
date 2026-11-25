package festivos.api.infraestructura.persistencia.repositorios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import festivos.api.core.repositorios.IUsuarioRepositorio;
import festivos.api.dominio.entidades.Usuario;
import festivos.api.infraestructura.persistencia.entidades.UsuarioEntidad;
import festivos.api.infraestructura.persistencia.mapeadores.UsuarioMapeador;
import festivos.api.infraestructura.persistencia.repositorios.jpa.IUsuarioRepositorioJpa;

@Component
public class UsuarioRepositorio implements IUsuarioRepositorio {

    @Autowired
    private IUsuarioRepositorioJpa repositorio;

    @Override
    public Usuario login(String nombreUsuario, String clave) {
        return repositorio.login(nombreUsuario, clave)
                .map(UsuarioMapeador::haciaDominio)
                .orElse(null);
    }

    @Override
    public List<Usuario> listar() {
        return repositorio.findAll()
                .stream()
                .map(UsuarioMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> obtenerPorId(int id) {
        return repositorio.findById(id)
                .map(UsuarioMapeador::haciaDominio);
    }

    @Override
    public Optional<Usuario> obtener(String usuario) {
        return repositorio.obtener(usuario)
                .map(UsuarioMapeador::haciaDominio);
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return repositorio.buscar(nombre)
                .stream()
                .map(UsuarioMapeador::haciaDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntidad usuarioEntidad = UsuarioMapeador.haciaEntidad(usuario);
        UsuarioEntidad guardado = repositorio.save(usuarioEntidad);
        return UsuarioMapeador.haciaDominio(guardado);
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
