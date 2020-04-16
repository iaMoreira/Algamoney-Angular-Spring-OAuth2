package com.devmobil.algamoney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devmobil.algamoney.api.model.User;
import com.devmobil.algamoney.api.repository.UserRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service // declaração de um serviço, ele vai ser mapeado e padronizado para ser usado como um serviço de configuração
public class MyUserDetailsService implements UserDetailsService {

	@Autowired 
    private UserRepository userRepository;

    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username); 
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Usuário {0} não existe! ", username));
        }
//        return new UserRepositoryUserDetails(user);
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getPermissoes(user));
    } 
    

	private Collection<? extends GrantedAuthority> getPermissoes(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getAuthority().toUpperCase())));
		return authorities;
	}


}
