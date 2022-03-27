package com.sistema.blog.repository;

import com.sistema.blog.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

    @Query("select p from Publicacion p where p.titulo=?1")
    Optional<Publicacion> findPublicationByTitle(String name);
}
