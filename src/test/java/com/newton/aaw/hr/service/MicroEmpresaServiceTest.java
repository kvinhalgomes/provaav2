package com.newton.aaw.hr.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.newton.aaw.hr.domain.entity.MicroEmpresa;
import com.newton.aaw.hr.domain.enums.Gender;
import com.newton.aaw.hr.domain.repository.MicroEmpresaRepository;
import com.newton.aaw.hr.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
class MicroEmpresaServiceTest {
	
	@Mock
	private MicroEmpresaRepository microEmpresaRepository;
	
	private MicroEmpresaService unit;

	@BeforeEach
	public void setup() {
		unit = new MicroEmpresaService(microEmpresaRepository);
	}
	
	@Test
	void test_getById_withEmployee_shouldReturnObj() {
		// given:
		var employee = new MicroEmpresa();
		employee.setId("employee1");
		
		// mock definitions: 
		Mockito.when(microEmpresaRepository.findById("employee1"))
			.thenReturn(Optional.of(employee));
		
		// test:
		var result = unit.get("employee1");
		
		// assert:
		Assertions.assertEquals(result, employee);
		
		// verify: 
		Mockito.verify(microEmpresaRepository).findById("employee1");
	}
	
	@Test
	void test_getById_withNoEmployee_shouldThrowException() {
		// given:
		var id = "employee1";
		
		// mock definitions
		Mockito.when(microEmpresaRepository.findById(id))
		.thenReturn(Optional.empty());

		// test:
		try {
			unit.get(id);
			fail("Expected NotFoundException");
		} catch (NotFoundException ex) {
			// assert:
			Assertions.assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");
		}
		
		// verify: 
		Mockito.verify(microEmpresaRepository).findById(id);
	}
	
	@Test
	void test_delete_withValidId_shouldDeleteOk() {
		// given
		var id = "001";
		var employee = new MicroEmpresa();
		
		// mock definitions
		Mockito.when(microEmpresaRepository.findById(id))
			.thenReturn(Optional.of(employee));
		
		// test
		unit.delete(id);
		
		// assert
		
		// verify
		Mockito.verify(microEmpresaRepository).findById(id);
		Mockito.verify(microEmpresaRepository).deleteById(id);
	}
	
	@Test
	void test_delete_withInvalidId_shouldThrowException() {
		// given
		var id = "001";
		
		// mock definitions
		Mockito.when(microEmpresaRepository.findById(id))
		.thenReturn(Optional.empty());
		
		try {
			// test
			unit.delete(id);
			fail("Expected NotFoundException");
		} catch (NotFoundException ex) {
			// assert
			Assertions.assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");			
		}		
		
		// verify
		Mockito.verify(microEmpresaRepository).findById(id);
		
		Mockito.verifyNoMoreInteractions(microEmpresaRepository);
		//Mockito.verify(employeeRepository, Mockito.times(0)).deleteById(id);
	}

	@Test
	void test_getAll() {
		// given
		var lista = new ArrayList<MicroEmpresa>();
		lista.add(new MicroEmpresa().withId("0001").withNome("inter"));
		
		// mock definitions
		Mockito.when(microEmpresaRepository.findAll()).thenReturn(lista);
		
		// test
		var result = unit.getAll();
		
		// assert
		assertEquals(result, lista);
		
		// verify
		Mockito.verify(microEmpresaRepository).findAll();
	}
	
	@Test
	void test_create() {
		// given
		var employee = new MicroEmpresa();
		
		// mock definitions
		
		// test
		var result = unit.create(employee);
		
		// assert
		assertNotNull(result.getDataDeCadastro());

		// verify		
		Mockito.verify(microEmpresaRepository).save(employee);
	}
	
	@Test
	void test_update_withValidId_shouldUpdateOk() {
		// given
		var id = "001";
		var existing = new MicroEmpresa();
		existing.setEmail("newton@gmail.com");
		existing.setCnpj("123456789");
		existing.setNome("newton");

		
		var updated = new MicroEmpresa();
		updated.setEmail("newton@hotmail.com");
		updated.setCnpj("987654321");
		updated.setNome("Inter");


		// mock definitions
		Mockito.when(microEmpresaRepository.findById(id))
			.thenReturn(Optional.of(existing));
		
		// test
		var result = unit.update(id, updated);
		
		// assert
		assertEquals(result.getCnpj(), updated.getCnpj());
		assertEquals(result.getEmail(), updated.getEmail());
		assertEquals(result.getNome(), updated.getNome());
		assertEquals(result.getDataDeCadastro(), updated.getDataDeCadastro());

		// verify
		Mockito.verify(microEmpresaRepository).findById(id);
		Mockito.verify(microEmpresaRepository).save(existing);
	}

	@Test
	void test_update_withInvalidId_shouldthrowException() {
		// given
		var id = "001";
		var updated = new MicroEmpresa();
				
		// mock definitions
		Mockito.when(microEmpresaRepository.findById(id))
			.thenReturn(Optional.empty());
		
		try {
			// test
			unit.update(id, updated);
			fail("Expected NotFoundException");			
		} catch (NotFoundException ex) {
			// assert
			assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");			
		}
		
		// verify
		Mockito.verify(microEmpresaRepository).findById(id);
		Mockito.verifyNoMoreInteractions(microEmpresaRepository);
	}

}
