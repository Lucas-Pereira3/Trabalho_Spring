package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @Size(min=11,max=11, message = "Cpf deve conter 11 caracteres")
    private String cpf;

    @Email(message = "E-mail invalido")
    private String email;

    @Size(min=11, message = "O telefone deve conter o DDD, EX:(xx) xxxxx-xxxx")

    @Size(min=8, message = "Senha deve conter no minimo 8 caracteres")
    private String senha;

    @NotBlank(message = "Data de nascimento obrigatória")
    private LocalDate data;
}
