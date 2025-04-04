package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuários")
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Busca um usuário por ID", description = "retorna os detalhes de um usuário especifico")
    @GetMapping("/{id}")

    public ResponseEntity<UsuarioDTO>buscarPorId(@PathVariable Long id){
        Optional<UsuarioDTO> usuario = usuarioService.buscarPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @Operation(summary = "criar um novo usuario", description = "cadastra um novo usuario de sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>>criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO)
    {
        try{
            UsuarioDTO saveUsuario = usuarioService.salvar(usuarioDTO);

            ApiResponse<UsuarioDTO> response = new ApiResponse<>(saveUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (IllegalArgumentException e){

            ErrorResponse errorResponse = new ErrorResponse("Argumento invalido", null);
        }catch (Exception e){

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<UsuarioDTO>response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}