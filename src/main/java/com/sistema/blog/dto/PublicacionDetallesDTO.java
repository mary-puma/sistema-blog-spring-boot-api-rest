package com.sistema.blog.dto;

import com.sistema.blog.entity.Comentario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PublicacionDetallesDTO {

    private String titulo;
    private String descripcion;
    private String contenido;
    private List<String> comentarios;

}
