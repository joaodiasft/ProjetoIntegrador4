package br.com.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pi.model.WebUser;

public interface WebUserRepository extends JpaRepository<WebUser, Long> {

}
