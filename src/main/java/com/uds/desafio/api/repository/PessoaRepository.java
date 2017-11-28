package com.uds.desafio.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uds.desafio.api.model.Pessoa;
import com.uds.desafio.api.repository.pessoa.PessoaRepositoryQuery;


public interface PessoaRepository extends JpaRepository<Pessoa, UUID>, PessoaRepositoryQuery {

}
