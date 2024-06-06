package br.com.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
