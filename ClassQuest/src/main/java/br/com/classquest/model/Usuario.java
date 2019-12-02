package br.com.classquest.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Usuario() {
		this.dataCriacao = Calendar.getInstance().getTime();
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=70)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	private String nome;
	
	@Column(nullable=false, length=70)
	@NotBlank(message = "Email é uma informação obrigatória.")
	private String email;
	
	@Column(unique = true)
	@Size(min = 4, message = "USERNAME dev ter pelo menos 4 letras")
	private String username;
	
	@Column(nullable = false, length = 255)
	@NotBlank(message = "Senha é uma informação obrigatória.")
	private String senha;
	
	/*@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;*/

	/*@OneToOne(optional = true)
	private Aluno aluno;
	
	@OneToOne(optional = true)
	private Professor professor;*/
		
	@Lob
	private byte[] foto;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private Set<Role> role = new HashSet<Role>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoAcesso;
	
	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.addAll(getRole());
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public String getRoleString() {
		String txt="";
		if(role!=null) {
			for(Role r: role) {
				txt+= r.getNome() + ", "; 
			}
		}
		return txt;
	}

}
