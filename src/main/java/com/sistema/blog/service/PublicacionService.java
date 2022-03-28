package com.sistema.blog.service;

import com.sistema.blog.dto.PublicacionDetallesDTO;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                .map(this::mapearPublicacionDTO)
                .collect(Collectors.toList());
    }

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = mapearPublicacion(publicacionDTO);
        Publicacion respuestaPublicacion = publicacionRepository.save(publicacion);
        return mapearPublicacionDTO(respuestaPublicacion);

    }

    public PublicacionDTO mapearPublicacionDTO(Publicacion publicacion) {
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setContenido(publicacion.getContenido());

        return publicacionDTO;
    }

    public Publicacion mapearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(publicacionDTO.getId());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setTitulo(publicacionDTO.getTitulo());

        return publicacion;
    }

    public PublicacionDTO publicacionPorId(long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapearPublicacionDTO(publicacion);
    }

    public void eliminarPublicacion(String name) {
        Publicacion publicacion = publicacionRepository.findPublicationByTitle(name)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "titulo", name));
        publicacionRepository.delete(publicacion);
    }

    public void eliminarPublicacionPorID(long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
    }

    public PublicacionDTO actualizarPublicacionPorID(PublicacionDTO publicacionDTO, long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        Publicacion publicacion2 = publicacionRepository.save(publicacion);
        return mapearPublicacionDTO(publicacion2);

    }

    public List<PublicacionDetallesDTO> obtenerListaDePublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        List<PublicacionDetallesDTO> publicacionDetallesDTOList = publicaciones.stream()
                .map(this::mapearPublicacionDetalleDTO)
                .collect(Collectors.toList());
        return publicacionDetallesDTOList;
    }

    private PublicacionDetallesDTO mapearPublicacionDetalleDTO(Publicacion publicacion) {
        PublicacionDetallesDTO publicacionDetallesDTO = new PublicacionDetallesDTO();
        publicacionDetallesDTO.setComentarios(publicacion.getComentarios());
        publicacionDetallesDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDetallesDTO.setContenido((publicacion.getContenido()));
        publicacionDetallesDTO.setTitulo(publicacion.getTitulo());
        return publicacionDetallesDTO;
    }
}
