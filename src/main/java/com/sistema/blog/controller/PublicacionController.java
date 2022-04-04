package com.sistema.blog.controller;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionDetallesDTO;
import com.sistema.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @PostMapping
    public PublicacionDTO guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO) {
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

    @GetMapping("/detalles")
    public List<PublicacionDetallesDTO> obtenerPublicacionYSusComentarios(){
        return publicacionService.listaDePublicacionesYSusComentarios();
    }

    @DeleteMapping("/{titulo}")
    public ResponseEntity<String> eliminarPublicacionPorTitulo(@PathVariable(name = "titulo") String name) {
        publicacionService.eliminarPublicacion(name);
        return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@PathVariable(name = "id") long id,@Valid @RequestBody PublicacionDTO publicacionDTO){
        PublicacionDTO publicacion = publicacionService.actualizarPublicacionPorID(publicacionDTO, id);
        return ResponseEntity.ok(publicacion);
    }

}
