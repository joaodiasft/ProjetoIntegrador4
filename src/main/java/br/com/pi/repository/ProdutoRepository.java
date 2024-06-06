package br.com.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
