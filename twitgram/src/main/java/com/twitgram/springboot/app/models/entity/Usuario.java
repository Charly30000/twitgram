package com.twitgram.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4901685386821425201L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false, length = 30)
	@Size(min = 3, max = 30)
	private String name;

	@NotEmpty
	@Column(nullable = false, unique = true, length = 30)
	@Size(min = 3, max = 30)
	private String username;

	@NotEmpty
	@Column(nullable = false)
	@Size(min = 5, max = 60)
	private String password;

	@NotEmpty
	@Column(nullable = false, unique = true)
	@Email
	private String email;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createAt;

	private String fotoPerfil;

	private Boolean enabled;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Role> roles;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Twit> twits;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_sigue_a", 
		joinColumns = @JoinColumn(name="usuario_id"), 
		inverseJoinColumns = @JoinColumn(name="sigue_a_id"))
	private List<Usuario> sigueA;

	public Usuario(@NotEmpty @Size(min = 6, max = 30) String name, @NotEmpty @Size(min = 6, max = 30) String username,
			@NotEmpty @Size(min = 5, max = 60) String password, @NotEmpty @Email String email, Date createAt,
			Boolean enabled, List<Role> roles) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createAt = createAt;
		this.enabled = enabled;
		this.roles = roles;
	}

	public Usuario() {

	}

	/*
	 * @PrePersist public void prePersist() { this.createAt = new Date(); }
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Twit> getTwits() {
		return twits;
	}

	public void setTwits(List<Twit> twits) {
		this.twits = twits;
	}

	public List<Usuario> getSigueA() {
		return sigueA;
	}

	public void setSigueA(List<Usuario> sigueA) {
		this.sigueA = sigueA;
	}

	// private static final long serialVersionUID = 1L;

}
