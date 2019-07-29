package br.com.thiberio.script.testeiti;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.thiberio.script.testeiti.service.LogService;

@SpringBootApplication
public class TesteItiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteItiApplication.class, args);
		LogService logService = new LogService();

		try {
			logService.processarLog(logService.extrairDadosTxt("compras.txt"));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
