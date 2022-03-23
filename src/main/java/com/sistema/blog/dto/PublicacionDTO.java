package com.sistema.blog.dto;

import com.sistema.blog.entity.Publicacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacionDTO {

    private long id;
    private String titulo;
    private String descripcion;
    private String contenido;

    public PublicacionDTO(long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

    public PublicacionDTO() {

    }

    public Publicacion mapearPublicacion(PublicacionDTO publicacionDTO){
        return new Publicacion(id,titulo,descripcion,contenido);
    }


}
