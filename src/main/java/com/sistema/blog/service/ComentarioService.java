package com.sistema.blog.service;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.dto.ComentarioDetallesDTO;
import com.sistema.blog.entity.Comentario;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.excepciones.BlogAppException;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repository.ComentarioRepository;
import com.sistema.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    PublicacionRepository publicacionRepository;

    public ComentarioDTO guardarComentario(ComentarioDTO comentarioDTO, long idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", idPublicacion));
        Comentario comentario = mapearComentario(comentarioDTO);
        comentario.setPublicacion(publicacion);
        Comentario comentarioNuevo = comentarioRepository.save(comentario);
        return mapearComentarioDTO(comentarioNuevo);

    }

    public Comentario mapearComentario(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        comentario.setEmail(comentarioDTO.getEmail());
        return comentario;
    }

    public ComentarioDTO mapearComentarioDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setCuerpo(comentario.getCuerpo());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setNombre(comentario.getNombre());
        return comentarioDTO;
    }

    public List<ComentarioDTO> listarComentarios() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return comentarios.stream()
                .map(this::mapearComentarioDTO)
                .collect(Collectors.toList());
    }

    public List<ComentarioDetallesDTO> listarComentariosDetalles() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return comentarios.stream()
                .map(this::mapearComentarioDetalles)
                .collect(Collectors.toList());
    }

    public ComentarioDetallesDTO mapearComentarioDetalles(Comentario comentario) {
        ComentarioDetallesDTO comentarioDetallesDTO = new ComentarioDetallesDTO();
        comentarioDetallesDTO.setCuerpo(comentario.getCuerpo());
        comentarioDetallesDTO.setEmail(comentario.getEmail());
        comentarioDetallesDTO.setId(comentario.getId());
        comentarioDetallesDTO.setNombre(comentario.getNombre());
        comentarioDetallesDTO.setTituloPublicacion(comentario.getPublicacion().getTitulo());
        return comentarioDetallesDTO;
    }

    public void eliminarComentario(long id) {
        Comentario comentario = comentarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", id));
        comentarioRepository.delete(comentario);
    }

    public ComentarioDTO comentarioPorID(Long idPublicacion, Long id) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", idPublicacion));

        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", id));

        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "el comentario no pertenece a la publicacion");
        }

        return mapearComentarioDTO(comentario);
    }

    public List<ComentarioDTO> obtenerComentariosPorIDPublicacion(Long idPublicacion) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(idPublicacion);

        return comentarios.stream()
                .map(this::mapearComentarioDTO)
                .collect(Collectors.toList());
    }
}
