package id.co.hanoman.training.webservices.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	//hardcode user
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		// TODO Auto-generated method stub
//		auth.inMemoryAuthentication().withUser("ipung").password("123").roles("USER");
//	}
	
	@Autowired
	private DataSource dataSource;
	
	
	//method ini untuk query ke database untuk cek user + password
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// TODO Auto-generated method stub
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT username, password," + 
				"enabled FROM users WHERE username=?")
				.authoritiesByUsernameQuery("SELECT u.username, a.authority "
				+ "FROM users u INNER JOIN authorities a ON u.id = a.id_user WHERE u.username=?");
		}


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//method ini untuk mengarahkan default url pada saat kita mengakses web
		http.authorizeRequests().anyRequest().authenticated().and().formLogin()
		.loginPage("/login").permitAll().defaultSuccessUrl("/alamat/list", true)
		.and().logout()
		//method ini untuk mem-filter web berdasarkan token(XSRF-TOKEN) yang dibawa di header
		.and().addFilterAfter(new CsrfAttributeToCookieFilter(), CsrfFilter.class)
		
		//1.method ini untuk override nama token di sisi server, apabila nama token di yang dikirim dari client belom sesuai 
		.csrf().csrfTokenRepository(csrfTokenRepository());;
	}
	
	//1.method ini untuk override nama token di sisi server, apabila nama token di yang dikirim dari client belom sesuai 	
	private CsrfTokenRepository csrfTokenRepository() {
	 	HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
	 	repository.setHeaderName("X-XSRF-TOKEN");
	 	return repository;
	 }

}
