package com.sistema.blog.service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        return publicaciones.stream()
                .map(Publicacion::mapearPublicacionDTO)
                .collect(Collectors.toList());
    }

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO){
        Publicacion publicacion = mapearPublicacion(publicacionDTO);
        Publicacion respuestaPublicacion = publicacionRepository.save(publicacion);
        return mapearPublicacionDTO(respuestaPublicacion);

    }

    private PublicacionDTO mapearPublicacionDTO(Publicacion publicacion) {
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setContenido(publicacion.getContenido());

        return publicacionDTO;
    }

    private Publicacion mapearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(publicacionDTO.getId());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setTitulo(publicacionDTO.getTitulo());
        return publicacion;

    }


}
