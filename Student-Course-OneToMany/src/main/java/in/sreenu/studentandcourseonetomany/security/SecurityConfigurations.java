package in.sreenu.studentandcourseonetomany.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfigurations {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf)-> csrf.disable())
		.authorizeHttpRequests(
			(authorize)-> authorize.requestMatchers(HttpMethod.GET,"/api/**")
			.permitAll().anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		return http.build();
		
	}
	@Bean
	public UserDetailsService userDeetails() {
		UserDetails sreenu = User.builder()
				.username("Sreenu")
				.password(passwordEncoder().encode("sreenu"))
				.roles("User")
				.build();
		UserDetails admin = User.builder()
				.username("Admin")
				.password(passwordEncoder().encode("Admin@123"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(sreenu, admin);
	}
	
}
