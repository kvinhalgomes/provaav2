package com.newton.aaw.hr.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.newton.aaw.hr.api.MicroEmpresaDTO;
import com.newton.aaw.hr.domain.enums.Gender;

class MicroEmpresaTest {

	@Test
	void test_createFromDto_withValidDto_shouldCreateOk() {
		// given
		var dto = new MicroEmpresaDTO();
		dto.setNome("NewtonSA");
		dto.setCnpj("123456789");
		dto.setDataDeCadastro(LocalDate.now());
		dto.setEmail("newtonSA@newton.com.br");

		// test
		var result = new MicroEmpresa(dto);
		
		// assert
		assertEquals(dto.getNome(), result.getNome());
		assertEquals(dto.getCnpj(), result.getCnpj());
		assertEquals(dto.getDataDeCadastro(), result.getDataDeCadastro());
		assertEquals(dto.getEmail(), result.getEmail());
	}

}
