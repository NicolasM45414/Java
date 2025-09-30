package org.jcr.entidades;

import lombok.Data;
import lombok.Builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class Sala implements Serializable {
    private final String numero;
    private final String tipo;
    private final Departamento departamento;

    @Builder.Default
    private List<Cita> citas = new ArrayList<>();

    // Constructor principal con lista de citas
    public Sala(String numero, String tipo, Departamento departamento, List<Cita> citas) {
        this.numero = validarString(numero, "El número de sala no puede ser nulo ni vacío");
        this.tipo = validarString(tipo, "El tipo de sala no puede ser nulo ni vacío");
        this.departamento = Objects.requireNonNull(departamento, "El departamento no puede ser nulo");
        this.citas = (citas != null) ? new ArrayList<>(citas) : new ArrayList<>();
    }

    // Constructor de 3 parámetros (para compatibilidad con Departamento)
    public Sala(String numero, String tipo, Departamento departamento) {
        this(numero, tipo, departamento, new ArrayList<>());
    }

    public void addCita(Cita cita) {
        if (cita != null) {
            this.citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(new ArrayList<>(citas));
    }

    private String validarString(String valor, String mensajeError) {
        Objects.requireNonNull(valor, mensajeError);
        if (valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", departamento=" + departamento.getNombre() +
                '}';
    }
}

