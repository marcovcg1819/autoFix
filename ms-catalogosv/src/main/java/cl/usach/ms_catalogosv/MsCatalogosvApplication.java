package cl.usach.ms_catalogosv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@SpringBootApplication
@EnableDiscoveryClient
@SpringBootApplication
public class MsCatalogosvApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCatalogosvApplication.class, args);
	}

}
