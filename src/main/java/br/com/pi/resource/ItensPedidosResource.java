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

import br.com.pi.model.ItensPedidos;
import br.com.pi.repository.ItensPedidosRepository;

@RestController
@RequestMapping("/itenspedidos")
public class ItensPedidosResource {
	
	@Autowired
	private ItensPedidosRepository itensPedidosRepository;
	
	@GetMapping
	public List<ItensPedidos> list(){
		return itensPedidosRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<ItensPedidos> findById(@PathVariable Long id){
		return itensPedidosRepository.findById(id);
	}
	@PostMapping
	public ResponseEntity<ItensPedidos> create (@RequestBody ItensPedidos itensPedidos,HttpServletResponse response){
		
		ItensPedidos save = itensPedidosRepository.save(itensPedidos);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itensPedidosRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ItensPedidos> update(@PathVariable Long id,@RequestBody ItensPedidos itensPedidos){
		Optional<ItensPedidos> itensBanco = itensPedidosRepository.findById(id);
		BeanUtils.copyProperties(itensPedidos, itensBanco.get());
		itensPedidosRepository.save(itensBanco.get());
		return ResponseEntity.ok(itensPedidos);
	}

}
