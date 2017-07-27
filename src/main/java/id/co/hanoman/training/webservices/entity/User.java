package id.co.hanoman.training.webservices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="username", unique=true, nullable=false)
	private String name;
	
	@Column(name="password", unique=true, nullable=false)
	private String password;
	
	@Column(name="enabled", columnDefinition="tinyint(1) default 1")
	private boolean enabled;
}
