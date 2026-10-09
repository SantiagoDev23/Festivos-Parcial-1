package festivos.api.presentacion.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import festivos.api.core.servicios.ICalendarioServicio;
import festivos.api.dominio.dtos.FestivoDto;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioControlador {

    @Autowired
    private ICalendarioServicio servicio;

    @GetMapping(value = "/verificar/{idPais}/{anio}/{mes}/{dia}")
    public ResponseEntity<String> verificar(@PathVariable int idPais, @PathVariable int anio,
            @PathVariable int mes, @PathVariable int dia) {
        if (!servicio.esFechaValida(anio, mes, dia)) {
            return ResponseEntity.ok("Fecha No valida");
        }
        var esFestivo = servicio.esFestivo(idPais, LocalDate.of(anio, mes, dia));
        return ResponseEntity.ok(esFestivo ? "Es Festivo" : "No es festivo");
    }

    @GetMapping(value = "/festivos/{idPais}/{anio}")
    public ResponseEntity<List<FestivoDto>> listarFestivos(@PathVariable int idPais, @PathVariable int anio) {
        return ResponseEntity.ok(servicio.listarFestivos(idPais, anio));
    }

}
