package com.foroHub.ForoHub.service;

import com.foroHub.ForoHub.domain.Topico;
import com.foroHub.ForoHub.dto.TopicoCreateDTO;
import com.foroHub.ForoHub.dto.TopicoDTO;
import com.foroHub.ForoHub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TopicoService {
    private final TopicoRepository topicoRepository;

    @Autowired
    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public List<TopicoDTO> listarTodos() {
        return topicoRepository.findAll().stream()
                .map(topico -> new TopicoDTO(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor(),
                        topico.getCurso()
                ))
                .collect(Collectors.toList());
    }

    public TopicoDTO obtenerPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
        return new TopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }

    public TopicoDTO crear(TopicoCreateDTO topicoCreate) {
        // Verifier si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(topicoCreate.titulo(), topicoCreate.mensaje())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe un tópico con este título y mensaje"
            );
        }

        Topico topico = new Topico();
        topico.setTitulo(topicoCreate.titulo());
        topico.setMensaje(topicoCreate.mensaje());
        topico.setStatus(topicoCreate.status());
        topico.setAutor(topicoCreate.autor());
        topico.setCurso(topicoCreate.curso());
        topico.setFechaCreacion(LocalDateTime.now());

        Topico topicoGuardado = topicoRepository.save(topico);
        return new TopicoDTO(
                topicoGuardado.getId(),
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                topicoGuardado.getFechaCreacion(),
                topicoGuardado.getStatus(),
                topicoGuardado.getAutor(),
                topicoGuardado.getCurso()
        );
    }

    public TopicoDTO actualizar(Long id, TopicoCreateDTO topicoCreate) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        topico.setTitulo(topicoCreate.titulo());
        topico.setMensaje(topicoCreate.mensaje());
        topico.setStatus(topicoCreate.status());
        topico.setAutor(topicoCreate.autor());
        topico.setCurso(topicoCreate.curso());

        Topico topicoActualizado = topicoRepository.save(topico);
        return new TopicoDTO(
                topicoActualizado.getId(),
                topicoActualizado.getTitulo(),
                topicoActualizado.getMensaje(),
                topicoActualizado.getFechaCreacion(),
                topicoActualizado.getStatus(),
                topicoActualizado.getAutor(),
                topicoActualizado.getCurso()
        );
    }

    public void eliminar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }
}

