package cufa.conecta.com.br.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"cufa.conecta.com.br"})
@EntityScan("cufa.conecta.com.br.resources")
@EnableJpaRepositories("cufa.conecta.com.br.resources")
public class CufaConectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CufaConectaApplication.class, args);
	}
}
