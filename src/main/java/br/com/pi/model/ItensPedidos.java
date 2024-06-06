package br.com.pi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "itenspedidos")
public class ItensPedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedidos_id")
	private Long id;
	
	@Column(name = "pedidos_quantidade")
	@NotBlank
	private String quantidade;
	
	@Column(name = "pedidos_valor")
	@NotBlank
	private Float valorUnitario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public Float getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	
}
