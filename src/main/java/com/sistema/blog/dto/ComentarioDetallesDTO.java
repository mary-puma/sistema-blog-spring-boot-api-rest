package com.sistema.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDetallesDTO {

    private long id;
    private String nombre;
    private String email;
    private String cuerpo;
    private String tituloPublicacion;
}
