package br.com.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pi.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
