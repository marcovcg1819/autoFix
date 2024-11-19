package cl.usach.ms_reparaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsReparacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReparacionesApplication.class, args);
	}

}
