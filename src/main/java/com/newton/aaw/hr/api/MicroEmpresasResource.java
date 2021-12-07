package com.newton.aaw.hr.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/v1/employees")
public interface MicroEmpresasResource {

	@Operation(summary = "Recupera uma MicroEmpresa a partir de um ID")
	@ApiResponses(value = {
			@ApiResponse(
					description = "MicroEmpresa retornada com sucesso",
					responseCode = "200", 
					content =  {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = MicroEmpresaDTO.class))
					}),
			@ApiResponse(
					description = "MicroEmpresa com ID não encontrado",
					responseCode = "404",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ErrorResponseDto.class))
					})
	})
	@GetMapping("/{id}")
	MicroEmpresaDTO getById(String id);

	@Operation(summary = "Recupera todas as MicroEmpresa")
	@ApiResponses(value = {
			@ApiResponse(
					description = "uma lista de MicroEmpresa retornada com sucesso",
					responseCode = "200", 
					content =  {
							@Content(
			                        mediaType = "application/json",
			                        array = @ArraySchema(
				                            schema = @Schema(implementation = MicroEmpresaDTO.class)
					                        )
			                    )
			                }
					)
	})
	@GetMapping
	List<MicroEmpresaDTO> getAll();

	@Operation(summary = "Permite criar uma nova MicroEmpresa")
	@ApiResponses(value = {
			@ApiResponse(
					description = "uma MicroEmpresa criada com sucesso",
					responseCode = "201", 
					content =  {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = MicroEmpresaDTO.class))
					}),
			@ApiResponse(
					description = "Parâmetros inválidos", 
					responseCode = "400",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ErrorResponseDto.class))
					})			
	})
	@PostMapping
	ResponseEntity<MicroEmpresaDTO> create(MicroEmpresaDTO microEmpresaDTO);

	@Operation(summary = "Atualiza uma MicroEmpresa existente a partir de um ID")
	@ApiResponses(value = {
			@ApiResponse(
					description = "uma MicroEmpresa atualizada com sucesso",
					responseCode = "200", 
					content =  {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = MicroEmpresaDTO.class))
					}),
			@ApiResponse(
					description = "MicroEmpresa com ID não encontrado",
					responseCode = "404",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ErrorResponseDto.class))
					}),
			@ApiResponse(
					description = "Parâmetros inválidos", 
					responseCode = "400",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ErrorResponseDto.class))
					})			
	})
	@PutMapping("/{id}")
	MicroEmpresaDTO update(String id, MicroEmpresaDTO microEmpresaDTO);

	@Operation(summary = "Excluir uma MicroEmpresa a partir de um ID")
	@ApiResponses(value = {
			@ApiResponse(
					description = "uma MicroEmpresa excluida com sucesso",
					responseCode = "204", 
					content =  {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = MicroEmpresaDTO.class))
					}),
			@ApiResponse(
					description = "MicroEmpresa com ID não encontrado",
					responseCode = "404",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = ErrorResponseDto.class))
					})
	})
	@DeleteMapping("/{id}") 
	ResponseEntity<Void> delete(String id);
}