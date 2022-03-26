package com.sistema.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacionDTO {

    private Long id;
    private String titulo;
    private String descripcion;
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
