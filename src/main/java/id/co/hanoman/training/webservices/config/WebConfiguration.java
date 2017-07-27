package id.co.hanoman.training.webservices.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter{

	//Method ini untuk mengarahkan rest controller ke /src/main/templates/, untuk meng-integrasi ke halaman web
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("alamat/list").setViewName("alamat/list");
		
		registry.addViewController("/login").setViewName("/login");
	}
	
	

}
