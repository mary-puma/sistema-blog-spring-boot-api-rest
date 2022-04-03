package com.sistema.blog.excepciones;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus estado;
    private String mensaje;

    public BlogAppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

}
