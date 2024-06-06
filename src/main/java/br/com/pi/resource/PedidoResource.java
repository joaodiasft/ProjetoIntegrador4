package br.com.pi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pi.model.Pedido;
import br.com.pi.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoResource {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public List<Pedido> list(){
		return pedidoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Pedido> findById(@PathVariable Long id){
		return pedidoRepository.findById(id);
	}
	@PostMapping
	public ResponseEntity<Pedido> create (@RequestBody Pedido pedido,HttpServletResponse response){
		
		Pedido save = pedidoRepository.save(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		pedidoRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id,@RequestBody Pedido pedido){
		Optional<Pedido> pedidoBanco = pedidoRepository.findById(id);
		BeanUtils.copyProperties(pedido, pedidoBanco.get());
		pedidoRepository.save(pedidoBanco.get());
		return ResponseEntity.ok(pedido);
	}
}
