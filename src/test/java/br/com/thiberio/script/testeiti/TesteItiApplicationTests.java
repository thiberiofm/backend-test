package br.com.thiberio.script.testeiti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiberio.script.testeiti.model.ItemLog;
import br.com.thiberio.script.testeiti.service.LogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteItiApplicationTests {
	
	
	@Test(expected = Exception.class)
	public void leituraArquivoInexistente() throws IOException{
		
		LogService logservice = new LogService();
		logservice.extrairDadosTxt("xxx.txt");
				
	}
	

	@Test
	public void contextLoads() {
	}

}
