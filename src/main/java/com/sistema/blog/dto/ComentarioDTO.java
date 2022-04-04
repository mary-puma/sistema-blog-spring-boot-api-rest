package com.sistema.blog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ComentarioDTO {

    private long id;

    @NotEmpty(message = "el nombre no debe ser vacio o null")
    private String nombre;

    @NotEmpty(message = "el email no debe ser vacio o null")
    @Email
    private String email;

    @NotEmpty(message = "el cuerpo no debe ser vacio o null")
    @Size(min = 10,message = "el cuerpo del comentario debe tener como minimo 10 caracteres")
    private String cuerpo;

}
