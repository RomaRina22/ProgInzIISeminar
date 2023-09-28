package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.models.security.MyAuthority;
import lv.venta.models.security.MyUser;
import lv.venta.repo.security.IMyAuthorityRepo;
import lv.venta.repo.security.IMyUserRepo;

@SpringBootApplication
public class Seminar1Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar1Application.class, args);
	}

	@Bean
	public CommandLineRunner testDb (IMyUserRepo userRepo,IMyAuthorityRepo authRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				MyUser use1 = new MyUser("Abcx","Nmee","password");
				userRepo.save(use1);
				MyUser use2 = new MyUser("Abcxie","Nmeea","password");
				userRepo.save(use2);
				
				MyAuthority auth1 = new MyAuthority("ADMIN");
				MyAuthority auth2 = new MyAuthority("USER");
				authRepo.save(auth1);
				authRepo.save(auth2);
				
				use1.addAuthority(auth1);
				use2.addAuthority(auth2);
				userRepo.save(use1);
				userRepo.save(use2);
				
			}
		};
	}
	
	@Bean
	PasswordEncoder passwordEncoderSimple() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
