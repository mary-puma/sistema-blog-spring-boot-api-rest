package com.sistema.blog.controller;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.dto.ComentarioDetallesDTO;
import com.sistema.blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @PostMapping("/publicaciones/{idPublicacion}/comentarios")
    public ResponseEntity<ComentarioDTO> crearComentario(@PathVariable(name = "idPublicacion") long idPublicacion, @RequestBody ComentarioDTO comentarioDTO) {
        return new ResponseEntity<>(comentarioService.guardarComentario(comentarioDTO, idPublicacion), HttpStatus.CREATED);
    }

    @GetMapping("/comentarios")
    public List<ComentarioDTO> obtenerComentarios() {
        return comentarioService.listarComentarios();
    }

    @GetMapping("/comentarios/detalles")
    public List<ComentarioDetallesDTO> obtenerComentariosDetalles() {
        return comentarioService.listarComentariosDetalles();
    }

    @GetMapping("/publicaciones/{idPublicacion}/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioPorID(@PathVariable(name = "idPublicacion") Long idPublicacion, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(comentarioService.comentarioPorID(idPublicacion, id), HttpStatus.OK);
    }

    @GetMapping("/publicaciones/{idPublicacion}/comentarios")
    public List<ComentarioDTO> listarComentariosPorIdPublicacion(@PathVariable(name = "idPublicacion") Long idPublicacion) {
        return comentarioService.obtenerComentariosPorIDPublicacion(idPublicacion);
    }

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<String> eliminarComentarioPorID(@PathVariable(name = "id") long id) {
        comentarioService.eliminarComentario(id);
        return new ResponseEntity<>("cometario eliminado con exito", HttpStatus.OK);
    }

}
