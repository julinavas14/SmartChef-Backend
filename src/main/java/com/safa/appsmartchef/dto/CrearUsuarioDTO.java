package com.safa.appsmartchef.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrearUsuarioDTO {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;
    @NotBlank(message = "El email debe de ser obligatorio")
    private String email;
    @NotBlank(message = "La contraseña debe de ser obligatoria")
    private String contraseña;
    @NotNull(message = "El Tipo es obligatorio")
    private Integer idTipo;
}
