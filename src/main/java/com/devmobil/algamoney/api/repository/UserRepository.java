package com.devmobil.algamoney.api.repository;

import com.devmobil.algamoney.api.model.User;

public interface UserRepository extends BaseRepository<User>{

	/*
	 * Filtro para o campo 'email', isso pode ser feito para qualquer campo da
	 * modelo/entidade;
	 * 
	 * Outros exemplos e referências no arquivo RoleRepository.
	 * 
	 * */
    User findByEmail(String email);
}
