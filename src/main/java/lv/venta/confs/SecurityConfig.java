package lv.venta.confs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@SuppressWarnings("deprecation")
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		UserDetails user1 = User.withDefaultPasswordEncoder().username("Lietotajs1").password("parole1").authorities("ADMIN").build();
		UserDetails user2 = User.withDefaultPasswordEncoder().username("Lietotajs2").password("parole1").authorities("USER").build();
		UserDetails user3 = User.withDefaultPasswordEncoder().username("Lietotajs3").password("parole1").authorities("USER", "ADMIN").build();
		manager.createUser(user1);
		manager.createUser(user2);
		manager.createUser(user3);
		return manager;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) {
        try {
			http.authorizeHttpRequests()
			        .requestMatchers("/hello").permitAll()
			        .requestMatchers("/msg").permitAll()
			        .requestMatchers("/product").hasAnyAuthority("ADMIN")
			        .requestMatchers("/productOne").hasAnyAuthority("ADMIN")
			        .requestMatchers("/product/**").hasAnyAuthority("USER", "ADMIN")
			        .requestMatchers("/allproducts").permitAll()
			        .requestMatchers("/allproducts/**").permitAll()
			        .requestMatchers("/insert").hasAnyAuthority("ADMIN")
			        .requestMatchers("/update/**").hasAnyAuthority("ADMIN")
			        .requestMatchers("/error").permitAll()
			        .requestMatchers("/delete/**").hasAnyAuthority("ADMIN")
			        .requestMatchers("/filter/quantity/**").permitAll()
			        .and().formLogin(login -> login.permitAll()).logout(logout -> logout.permitAll());
			return http.build();
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
		
	}
}
