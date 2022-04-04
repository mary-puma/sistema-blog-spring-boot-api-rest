package com.sistema.blog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PublicacionDTO {

    private Long id;

    @NotEmpty(message = "no puede ser vacio el titulo")
    private String titulo;

    @NotEmpty(message = "no puede estar vacia la descripcion")
    private String descripcion;

    @NotEmpty(message = "no puede estar vacio el contenido")
    private String contenido;

    public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

    public PublicacionDTO() {
    }

}
