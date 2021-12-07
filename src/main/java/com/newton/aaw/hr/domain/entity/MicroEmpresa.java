package com.newton.aaw.hr.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.newton.aaw.hr.api.MicroEmpresaDTO;
import com.newton.aaw.hr.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With // builder pattern
@AllArgsConstructor
@NoArgsConstructor
public class MicroEmpresa {

	@Id
	private String id;

	private String nome;

	private String cnpj;

	private String email;


	private LocalDate dataDeCadastro; // yyyy-mm-dd HH:mm:ss

	public MicroEmpresa(MicroEmpresaDTO e) {
		this.setNome(e.getNome());
		this.setCnpj(e.getCnpj());
		this.setEmail(e.getEmail());
		this.setDataDeCadastro(e.getDataDeCadastro());
	}
}
