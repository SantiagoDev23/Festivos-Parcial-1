package festivos.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import festivos.api.core.servicios.IPaisServicio;
import festivos.api.dominio.entidades.Pais;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

    @Autowired
    private IPaisServicio servicio;

    @GetMapping
    public ResponseEntity<List<Pais>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pais> obtener(@PathVariable int id) {
        var paisBuscado = servicio.obtener(id);
        if (paisBuscado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paisBuscado);
    }

    @GetMapping(value = "/buscar/{nombre}")
    public ResponseEntity<List<Pais>> buscar(@PathVariable String nombre) {
        return ResponseEntity.ok(servicio.buscar(nombre));
    }

    @PostMapping
    public ResponseEntity<Pais> agregar(@RequestBody Pais pais) {
        var paisCreado = servicio.agregar(pais);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paisCreado);
    }

    @PutMapping
    public ResponseEntity<Pais> modificar(@RequestBody Pais pais) {
        var paisModificado = servicio.modificar(pais);
        if (paisModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paisModificado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean respuesta = servicio.eliminar(id);
        if (!respuesta) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.noContent().build();
    }

}
