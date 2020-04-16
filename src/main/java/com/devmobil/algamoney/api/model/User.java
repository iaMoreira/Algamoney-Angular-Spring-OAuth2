package com.devmobil.algamoney.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity{

	
	@Column(name = "name", length = 100)
	@NotEmpty(message = "O campo nome é obrigátorio.")
	private String name;
	
	@Column(name = "email", length = 100, unique = true)
	@Email(message = "Insira o email válido.")
    private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY) // funciona como um hidden e esconde o campo quando é rederizado em Json 
	@Column(name = "password", length = 100)
    @NotEmpty(message = "O campo senha é obrigátorio.")
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER) // define um relacionamento de muitos para muitos entre duas entidades passando a tabela de relacionamento
    @JoinTable(name="user_roles",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private List<Role> roles;

    public User() {
    	
    }
    public User(String name, String email) {
	    super();
	    this.name = name;
	    this.email = email;
	}
	public User(User user) {
	    super();
	    this.name = user.getName();
	    this.email = user.getEmail();
	    this.password = user.getPassword();
	    this.roles = user.getRoles();
	    this.id = user.getId();
	}
	public User(String name, String email, String password, List<Role> roles) {
	    super();
	    this.name = name;
	    this.email = email;
	    this.roles = roles;
	    this.password = password;
	}

}
