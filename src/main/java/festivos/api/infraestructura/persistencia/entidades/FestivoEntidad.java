package festivos.api.infraestructura.persistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "festivo")
public class FestivoEntidad {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_festivo")
    @SequenceGenerator(
        name = "secuencia_festivo",
        sequenceName = "secuencia_festivo",
        allocationSize = 1
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "idpais", referencedColumnName = "id", nullable = false)
    private PaisEntidad pais;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "dia", nullable = false)
    private int dia;

    @Column(name = "mes", nullable = false)
    private int mes;

    @Column(name = "diaspascua", nullable = false)
    private int diasPascua;

    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id", nullable = false)
    private TipoFestivoEntidad tipo;


    public FestivoEntidad() {
    }

    public FestivoEntidad(
            int id,
            PaisEntidad pais,
            String nombre,
            int dia,
            int mes,
            int diasPascua,
            TipoFestivoEntidad tipo) {

        this.id = id;
        this.pais = pais;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaisEntidad getPais() {
        return pais;
    }

    public void setPais(PaisEntidad pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDiasPascua() {
        return diasPascua;
    }

    public void setDiasPascua(int diasPascua) {
        this.diasPascua = diasPascua;
    }

    public TipoFestivoEntidad getTipo() {
        return tipo;
    }

    public void setTipo(TipoFestivoEntidad tipo) {
        this.tipo = tipo;
    }

}