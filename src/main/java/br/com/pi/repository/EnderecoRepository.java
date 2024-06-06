package br.com.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pi.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
