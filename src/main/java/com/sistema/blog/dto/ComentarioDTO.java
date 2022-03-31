package com.sistema.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {

    private long id;
    private String nombre;
    private String email;
    private String cuerpo;

}
