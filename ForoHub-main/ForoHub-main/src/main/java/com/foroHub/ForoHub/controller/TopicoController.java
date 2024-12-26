package com.foroHub.ForoHub.controller;

import com.foroHub.ForoHub.domain.Topico;
import com.foroHub.ForoHub.dto.TopicoCreateDTO;
import com.foroHub.ForoHub.dto.TopicoDTO;
import com.foroHub.ForoHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService topicoService;

    @Autowired
    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> listar() {
        List<TopicoDTO> topicos = topicoService.listarTodos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> obtenerPorId(@PathVariable Long id) {
        TopicoDTO topico = topicoService.obtenerPorId(id);
        return ResponseEntity.ok(topico);
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> crear(@Valid @RequestBody TopicoCreateDTO topicoCreate) {
        TopicoDTO topicoCreado = topicoService.crear(topicoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TopicoCreateDTO topicoCreate
    ) {
        TopicoDTO topicoActualizado = topicoService.actualizar(id, topicoCreate);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
