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

import br.com.pi.model.Categoria;
import br.com.pi.repository.CategoriaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categoria")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> list(){
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Categoria> findById(@PathVariable Long id){
		return categoriaRepository.findById(id);
	}
	@PostMapping
	public ResponseEntity<Categoria> create (@RequestBody Categoria categoria,HttpServletResponse response){
		
		Categoria save = categoriaRepository.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		categoriaRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Long id,@RequestBody Categoria categoria){
		Optional<Categoria> categoriaBanco = categoriaRepository.findById(id);
		BeanUtils.copyProperties(categoria, categoriaBanco.get());
		categoriaRepository.save(categoriaBanco.get());
		return ResponseEntity.ok(categoria);
	}

}
