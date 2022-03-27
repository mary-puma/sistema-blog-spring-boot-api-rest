package com.sistema.blog.controller;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @PostMapping
    public PublicacionDTO guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        return publicacionService.crearPublicacion(publicacionDTO);
    }

    @GetMapping
    public List<PublicacionDTO> publicacionDTOList() {
        return publicacionService.obtenerTodasLasPublicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionDTOPorId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(publicacionService.publicacionPorId(id));
    }

    @DeleteMapping("/{titulo}")
    public ResponseEntity<String> eliminarPublicacionPorTitulo(@PathVariable(name = "titulo") String name) {
        publicacionService.eliminarPublicacion(name);
        return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
    }


}
