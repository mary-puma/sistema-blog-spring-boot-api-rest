package com.sistema.blog.controller;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @PostMapping("/publicaciones/{idPublicacion}/comentarios")
    public ResponseEntity<ComentarioDTO> crearComentario(@PathVariable (name = "idPublicacion") long idPublicacion, @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(comentarioService.guardarComentario(comentarioDTO,idPublicacion),HttpStatus.CREATED);
    }

}
