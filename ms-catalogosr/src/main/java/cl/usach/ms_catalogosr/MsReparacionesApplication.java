package cl.usach.ms_catalogosr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
//@EnableFeignClients
public class MsReparacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReparacionesApplication.class, args);
	}

}
