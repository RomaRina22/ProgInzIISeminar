package lv.venta.models.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name="users")
@Entity
public class MyUser {
	
	@Column(name="Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam")
	@Size(min = 3, max = 30, message = "Jabūt vismaz 3 un ne vairāk kā 30 simboliem")
	private String name;
	
	@Column
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam")
	@Size(min = 3, max = 30, message = "Jabūt vismaz 3 un ne vairāk kā 30 simboliem")
	private String surname;
	
	@Column
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam")
	@Size(min = 6, max = 60, message = "Jabūt vismaz 3 un ne vairāk kā 30 simboliem")
	private String username;
	
	@Column
	@NotNull
	private String password;

	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private Collection<MyAuthority> authorities = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void addAuthority(MyAuthority authority) {
		if(!authorities.contains(authority)) {
			authorities.add(authority);
		}
	}
	public void removeAuthority (MyAuthority authority) {
		if (authorities.contains(authority)){
			authorities.remove(authority);
		}
	}
	public void setName(String name) {
		if (name.matches("[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+")) {
			this.name = name;
		}
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (surname.matches("[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+")) {
			this.surname = surname;
		}
	}

	public String getUsername() {
		return username;
	}

	public void generateUsername() {
		String generatedUsername = this.name+"."+this.surname;
		this.username = generatedUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public MyUser(
			@NotNull @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam") @Size(min = 3, max = 30, message = "Jabūt vismaz 3 un ne vairāk kā 30 simboliem") String name,
			@NotNull @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam") @Size(min = 3, max = 30, message = "Jabūt vismaz 3 un ne vairāk kā 30 simboliem") String surname,
			@NotNull String password) {
		super();
		setName(name);;
		setSurname(surname);
		setPassword(password);
	}

	public MyUser() {
		super();
		setName("Default");
		setSurname("Default");
		setPassword("Default");
	}
	
	
	
}
