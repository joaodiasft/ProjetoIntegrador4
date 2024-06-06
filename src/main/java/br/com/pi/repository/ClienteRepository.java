package br.com.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
