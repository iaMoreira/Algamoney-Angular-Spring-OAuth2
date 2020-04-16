package com.devmobil.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmobil.algamoney.api.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

	/*
	 * Filtro para o campo 'name', isso pode ser feito para qualquer campo da
	 * modelo/entidade   
	 * 
	 * Também pode ser implementada querys sql para adaptar a consulta:
	 *  @Query("SELECT con FROM Contact con  WHERE con.phoneType=(:pType) AND con.lastName= (:lName)")
	 *  
	 *  List<User> findByEmailAddressAndLastname(String emailAddress, String lastname);
	 *  select u from User u where u.emailAddress = ?1 and u.lastname = ?2
	 *  
	 *  Mais exemplos podem serem encontrados aqui: https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
	 * */
    Role findByName(String name); 

}
