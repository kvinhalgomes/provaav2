package com.newton.aaw.hr.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newton.aaw.hr.domain.entity.MicroEmpresa;
import com.newton.aaw.hr.domain.repository.MicroEmpresaRepository;
import com.newton.aaw.hr.exception.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MicroEmpresaService {

	private final MicroEmpresaRepository microEmpresaRepository;

	public MicroEmpresa create(MicroEmpresa e) {
		var now  = LocalDate.now();

		e.setDataDeCadastro(now);

		microEmpresaRepository.save(e);

		log.debug("Employee created: {}", e);

		return e;
	}

	public MicroEmpresa update(String id, MicroEmpresa u) {
		var existing = get(id);

		existing.setNome(u.getNome());
		existing.setEmail(u.getEmail());
		existing.setCnpj(u.getCnpj());
		existing.setDataDeCadastro(u.getDataDeCadastro());

		microEmpresaRepository.save(existing);

		return existing;
	}

	public MicroEmpresa get(String id) {
		log.debug("Getting Employee by ID: {}", id);

		var employee = microEmpresaRepository.findById(id);

		if (employee.isEmpty()) {
			// logging 404 in console
			log.error("ERROR: Employee with ID {} not found.", id);
			// HTTP 404
			throw new NotFoundException("Employee with ID " + id + " not found");
		}

		return employee.get();
	}

	public List<MicroEmpresa> getAll() {
		return microEmpresaRepository.findAll();
	}

	public void delete(String id) {
		var employee = get(id);

		// employee a ser excluido
		log.debug("Employee deleted: {}", employee);

		microEmpresaRepository.deleteById(id);
	}
}
