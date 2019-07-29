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
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteItiApplicationTests {
	
	LogService logservice = new LogService();
	
	@Test(expected = Exception.class)
	public void leituraArquivoInexistente() throws IOException{
		
	//	LogService logservice = new LogService();
		logservice.extrairDadosTxt("xxx.txt");
				
	}
	
	@Test
	public void validarCategoriaMaiorGasto () {
		
		List<ItemLog> items = new ArrayList<>();
		
		ItemLog itemlog = new ItemLog();
		
		itemlog.setCategoria("viagem");
		itemlog.setData("17-Feb");
		itemlog.setDescricao("TAM SITE ");
		itemlog.setValor(Double.parseDouble("-430.49"));
		items.add(itemlog);
		
		ItemLog itemlog2 = new ItemLog();
		
		itemlog2.setCategoria("Festa");
		itemlog2.setData("17-Feb");
		itemlog2.setDescricao("Festa Fabio ");
		itemlog2.setValor(Double.parseDouble("-420.49"));
		items.add(itemlog2);
			
		assertEquals("Encontrando a categoria com maior gasto ","viagem" , logservice.getCategoriaMaiorGasto(items));
	}
	
	@Test
	public void validarTotalGasto () {
		
		List<ItemLog> items = new ArrayList<>();
		
		ItemLog itemlog = new ItemLog();
		
		itemlog.setCategoria("viagem");
		itemlog.setData("17-Feb");
		itemlog.setDescricao("TAM SITE ");
		itemlog.setValor(Double.parseDouble("-1000.99"));
		items.add(itemlog);
		
		ItemLog itemlog2 = new ItemLog();
		
		itemlog2.setCategoria("Festa");
		itemlog2.setData("17-Feb");
		itemlog2.setDescricao("Festa Fabio ");
		itemlog2.setValor(Double.parseDouble("-20.10"));
		items.add(itemlog2);
		
		String retorno = logservice.getMaiorGasto(items).toString();
			
		assertEquals("Encontrando o maior valos gasto","-1021.09" , retorno);
	}

	
	@Test
	public void validarGanhoDoMes () {
		
		List<ItemLog> items = new ArrayList<>();
		
		ItemLog itemlog = new ItemLog();
		
		itemlog.setCategoria("viagem");
		itemlog.setData("17-Feb");
		itemlog.setDescricao("TAM SITE ");
		itemlog.setValor(Double.parseDouble("100.00"));
		items.add(itemlog);
		
		ItemLog itemlog2 = new ItemLog();
		
		itemlog2.setCategoria("Festa");
		itemlog2.setData("17-Feb");
		itemlog2.setDescricao("Festa Fabio ");
		itemlog2.setValor(Double.parseDouble("50.50"));
		items.add(itemlog2);
		
		String retorno = logservice.getMaiorReceita(items).toString();
			
		assertEquals("Encontrando o maior valos gasto","150.5" , retorno);
	}
	
	
	@Test
	public void validarValorTotal () {
		
		List<ItemLog> items = new ArrayList<>();
		
		ItemLog itemlog = new ItemLog();
		
		itemlog.setCategoria("viagem");
		itemlog.setData("17-Feb");
		itemlog.setDescricao("TAM SITE ");
		itemlog.setValor(Double.parseDouble("-100.00"));
		items.add(itemlog);
		
		ItemLog itemlog2 = new ItemLog();
		
		itemlog2.setCategoria("Festa");
		itemlog2.setData("17-Feb");
		itemlog2.setDescricao("Festa Fabio ");
		itemlog2.setValor(Double.parseDouble("50.50"));
		items.add(itemlog2);
		
		String retorno = logservice.getSaldoTotal(items).toString();
			
		assertEquals("Encontrando o maior valos gasto","-49.5" , retorno);
	}
	
	
	
	
	@Test
	public void contextLoads() {
	}

}
