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

import festivos.api.core.servicios.ITipoFestivoServicio;
import festivos.api.dominio.entidades.TipoFestivo;

@RestController
@RequestMapping("/api/tipos")
public class TipoFestivoControlador {

    @Autowired
    private ITipoFestivoServicio servicio;

    @GetMapping
    public ResponseEntity<List<TipoFestivo>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoFestivo> obtener(@PathVariable int id) {
        var tipoBuscado = servicio.obtener(id);
        if (tipoBuscado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoBuscado);
    }

    @GetMapping(value = "/buscar/{tipo}")
    public ResponseEntity<List<TipoFestivo>> buscar(@PathVariable String tipo) {
        return ResponseEntity.ok(servicio.buscar(tipo));
    }

    @PostMapping
    public ResponseEntity<TipoFestivo> agregar(@RequestBody TipoFestivo tipo) {
        var tipoCreado = servicio.agregar(tipo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tipoCreado);
    }

    @PutMapping
    public ResponseEntity<TipoFestivo> modificar(@RequestBody TipoFestivo tipo) {
        var tipoModificado = servicio.modificar(tipo);
        if (tipoModificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoModificado);
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
