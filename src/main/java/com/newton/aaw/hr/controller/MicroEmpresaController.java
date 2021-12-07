package com.newton.aaw.hr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newton.aaw.hr.api.MicroEmpresaDTO;
import com.newton.aaw.hr.api.MicroEmpresasResource;
import com.newton.aaw.hr.domain.entity.MicroEmpresa;
import com.newton.aaw.hr.service.MicroEmpresaService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class MicroEmpresaController implements MicroEmpresasResource {

	private final MicroEmpresaService microEmpresaService;

	@Override
	public MicroEmpresaDTO getById(@PathVariable String id) {
		log.info("GET MicroEmpresa by Id: {}", id);

		var employee = microEmpresaService.get(id);

		var response = new MicroEmpresaDTO(employee);

		log.info("GET MicroEmpresa by ID {} response: {}", id, response);

		return response;
	}

	@Override
	public List<MicroEmpresaDTO> getAll() {
		var microEmpresas = microEmpresaService.getAll();

		var microEmpresasDto = new ArrayList<MicroEmpresaDTO>();

		for (var microEmpresa: microEmpresas) {
			microEmpresasDto.add(new MicroEmpresaDTO(microEmpresa));
		}

		return microEmpresasDto;
	}

	@Override
	public ResponseEntity<MicroEmpresaDTO> create(
			@RequestBody MicroEmpresaDTO microEmpresaDTO
	) {
		log.info("POST create microEmpresa: {}", microEmpresaDTO);

		var microEmpresa = new MicroEmpresa(microEmpresaDTO);

		microEmpresa = microEmpresaService.create(microEmpresa);

		var dto = new MicroEmpresaDTO(microEmpresa);

		log.info("POST create microEmpresa response: {}", dto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(dto);
	}

	@Override
	public MicroEmpresaDTO update(
			@PathVariable String id,
			@RequestBody MicroEmpresaDTO microEmpresaDTO
	) {
		var microEmpresa = new MicroEmpresa(microEmpresaDTO);

		microEmpresa = microEmpresaService.update(id, microEmpresa);

		return new MicroEmpresaDTO(microEmpresa);
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		log.info("DELETE microEmpresa by ID: {}", id);

		microEmpresaService.delete(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
