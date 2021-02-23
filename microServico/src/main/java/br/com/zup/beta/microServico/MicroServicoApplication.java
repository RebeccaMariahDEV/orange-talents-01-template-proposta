package br.com.zup.beta.microServico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@RestController
public class MicroServicoApplication {

//	@RequestMapping("/")
//	public  String home(){
//		return "teste de docker";
//	}

	public static void main(String[] args) {
		SpringApplication.run(MicroServicoApplication.class, args);
	}

}
