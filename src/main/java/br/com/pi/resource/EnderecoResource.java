package br.com.pi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.pi.model.Endereco;
import br.com.pi.repository.EnderecoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/endereco")
public class EnderecoResource {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping
	public List<Endereco> list(){
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Endereco> findById(@PathVariable Long id){
		return enderecoRepository.findById(id);
	}
	@PostMapping
	public ResponseEntity<Endereco> create (@RequestBody Endereco endereco,HttpServletResponse response){
		
		Endereco save = enderecoRepository.save(endereco);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		enderecoRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> update(@PathVariable Long id,@RequestBody Endereco endereco){
		Optional<Endereco> enderecoBanco = enderecoRepository.findById(id);
		BeanUtils.copyProperties(endereco, enderecoBanco.get());
		enderecoRepository.save(enderecoBanco.get());
		return ResponseEntity.ok(endereco);
	}

}
