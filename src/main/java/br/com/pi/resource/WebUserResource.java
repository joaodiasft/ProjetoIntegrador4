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

import br.com.pi.model.WebUser;
import br.com.pi.repository.WebUserRepository;

@RestController
@RequestMapping("/webuser")
public class WebUserResource {
	
	@Autowired
	private WebUserRepository webuserRepository;
	
	@GetMapping
	public List<WebUser> list(){
		return webuserRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<WebUser> findById(@PathVariable Long id){
		return webuserRepository.findById(id);
	}
	@PostMapping
	public ResponseEntity<WebUser> create (@RequestBody WebUser webUser,HttpServletResponse response){
		
		WebUser save = webuserRepository.save(webUser);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		webuserRepository.deleteById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<WebUser> update(@PathVariable Long id,@RequestBody WebUser webUser){
		Optional<WebUser> webBanco = webuserRepository.findById(id);
		BeanUtils.copyProperties(webUser, webBanco.get());
		webuserRepository.save(webBanco.get());
		return ResponseEntity.ok(webUser);
	}

}
