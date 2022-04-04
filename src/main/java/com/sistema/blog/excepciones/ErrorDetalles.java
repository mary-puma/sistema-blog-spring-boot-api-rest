package com.sistema.blog.excepciones;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetalles {

    private Date marcaDeTiempo;
    private String mensaje1;
    private String detalles;

    public ErrorDetalles(Date marcaDeTiempo, String mensaje1, String detalles) {
        super();
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje1 = mensaje1;
        this.detalles = detalles;
    }

}
