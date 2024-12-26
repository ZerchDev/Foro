package com.foroHub.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicoCreateDTO(
        @NotBlank(message = "El título no puede estar vacío")
        @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío")
        @Size(min = 10, max = 500, message = "El mensaje debe tener entre 10 y 500 caracteres")
        String mensaje,

        @NotBlank(message = "El status no puede estar vacío")
        String status,

        @NotBlank(message = "El autor no puede estar vacío")
        String autor,

        @NotBlank(message = "El curso no puede estar vacío")
        String curso
) {}
