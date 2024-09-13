package com.example.colaboradores;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.colaboradores.repositories.AdminRepository;

@SpringBootApplication
public class ColaboradoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColaboradoresApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(AdminRepository adminRepository) {
		
		return args -> {
			if(adminRepository.count() == 0) {
				Admin admin = new Admin();
				admin.setNome("admin");
				admin.setSenha("senha123");
				
				adminRepository.save(admin);
			}
		}
	}
}
