package com.newton.aaw.hr.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.newton.aaw.hr.domain.entity.MicroEmpresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MicroEmpresaDTO {

	private String id;

	private String nome;

	private String cnpj;

	private String email;

	private LocalDate dataDeCadastro; // yyyy-mm-dd HH:mm:ss

	public MicroEmpresaDTO(MicroEmpresa e) {
		this.setId(e.getId());
		this.setNome(e.getNome());
		this.setCnpj(e.getCnpj());
		this.setEmail(e.getEmail());
		this.setDataDeCadastro(e.getDataDeCadastro());
	}
}
