package com.newton.aaw.hr.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.newton.aaw.hr.domain.entity.MicroEmpresa;

public interface MicroEmpresaRepository extends MongoRepository<MicroEmpresa, String>{

}
