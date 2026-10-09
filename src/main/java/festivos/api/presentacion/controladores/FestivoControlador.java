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

import festivos.api.core.servicios.IFestivoServicio;
import festivos.api.dominio.entidades.Festivo;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    @Autowired
    private IFestivoServicio servicio;

    @GetMapping
    public ResponseEntity<List<Festivo>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping(value = "/pais/{idPais}")
    public ResponseEntity<List<Festivo>> listarPorPais(@PathVariable int idPais) {
        return ResponseEntity.ok(servicio.listarPorPais(idPais));
    }

    @GetMapping(value = "/tipo/{idTipo}")
    public ResponseEntity<List<Festivo>> listarPorTipo(@PathVariable int idTipo) {
        return ResponseEntity.ok(servicio.listarPorTipo(idTipo));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Festivo> obtener(@PathVariable int id) {
        var festivoBuscado = servicio.obtener(id);
        if (festivoBuscado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(festivoBuscado);
    }

    @GetMapping(value = "/buscar/{nombre}")
    public ResponseEntity<List<Festivo>> buscar(@PathVariable String nombre) {
        return ResponseEntity.ok(servicio.buscar(nombre));
    }

    @PostMapping
    public ResponseEntity<Festivo> agregar(@RequestBody Festivo festivo) {
        var festivoCreado = servicio.agregar(festivo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(festivoCreado);
    }

    @PutMapping
    public ResponseEntity<Festivo> modificar(@RequestBody Festivo festivo) {
        var festivoModificado = servicio.modificar(festivo);
        if (festivoModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(festivoModificado);
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
