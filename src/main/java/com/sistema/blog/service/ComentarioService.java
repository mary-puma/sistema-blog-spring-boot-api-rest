package com.sistema.blog.service;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.entity.Comentario;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repository.ComentarioRepository;
import com.sistema.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    PublicacionRepository publicacionRepository;

    public ComentarioDTO guardarComentario(ComentarioDTO comentarioDTO, long idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(()-> new ResourceNotFoundException("Publicacion","id",idPublicacion));
        Comentario comentario = mapearComentario(comentarioDTO);
        comentario.setPublicacion(publicacion);
        Comentario comentarioNuevo = comentarioRepository.save(comentario);
        return mapearComentarioDTO(comentarioNuevo);

    }

    public Comentario mapearComentario(ComentarioDTO comentarioDTO){
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        comentario.setEmail(comentarioDTO.getEmail());
        return comentario;
    }

    public ComentarioDTO mapearComentarioDTO(Comentario comentario){
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setCuerpo(comentario.getCuerpo());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setNombre(comentario.getNombre());
        return comentarioDTO;
    }
}
