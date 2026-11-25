package festivos.api.aplicacion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import festivos.api.core.repositorios.IUsuarioRepositorio;
import festivos.api.dominio.entidades.Usuario;

@Service
public class UsuarioDetalleServicio implements UserDetailsService {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuarioObtenido = repositorio.obtener(nombreUsuario).orElse(null);
        if (usuarioObtenido == null) {
            throw new UsernameNotFoundException(nombreUsuario);
        }
        return new UsuarioDetalles(usuarioObtenido);
    }

}
