package br.com.ifba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class SigecapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigecapApplication.class, args);
		System.out.println("FUNCINOU?");
	}

}
