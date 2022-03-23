package com.sistema.blog.controller;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublicacionController {

    private static final String PUBLICACIONES = "/api/publicaciones";

    @Autowired
    private PublicacionService publicacionService;

    @PostMapping(PUBLICACIONES)
    public PublicacionDTO guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO){
        return publicacionService.crearPublicacion(publicacionDTO);
    }

    @GetMapping(PUBLICACIONES)
    public List<PublicacionDTO> publicacionDTOList(){
        return publicacionService.obtenerTodasLasPublicaciones();
    }


}
