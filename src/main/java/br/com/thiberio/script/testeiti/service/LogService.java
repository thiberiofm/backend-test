package br.com.thiberio.script.testeiti.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thiberio.script.testeiti.model.ItemLog;

public class LogService {
	
	public List<ItemLog> extrairDadosTxt(String file) throws IOException {
		ArrayList<ItemLog> itemsLog = new ArrayList<ItemLog>();

		BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(file)));

		int contador = 0;
		while (br.ready()) {
			String linha = br.readLine();
			if (contador > 0) {
				String[] itensLinha = linha.replace("\t", "  ").split("  ");

				List<String> itensLinhaFinal = new ArrayList<String>();
				for (int i = 0; i < itensLinha.length; i++) {
					if (!itensLinha[i].trim().equals("")) {
						itensLinhaFinal.add(itensLinha[i]);
					}
				}

				ItemLog itemLog = new ItemLog();
				itemLog.setData(itensLinhaFinal.get(0));
				itemLog.setDescricao(itensLinhaFinal.get(1));
				itemLog.setValor(Double.parseDouble(itensLinhaFinal.get(2).replace(".", "").replace(",", ".")));
				itemLog.setCategoria(itensLinhaFinal.size() > 3 ? itensLinhaFinal.get(3) : null);

				itemsLog.add(itemLog);
			}

			contador++;
		}

		br.close();

		return itemsLog;
	}

	public void processarLog(List<ItemLog> itemsLog) {
		
		if (itemsLog != null || !itemsLog.isEmpty())
		{			
			System.out.println("Categoria com maior gasto é: " + getCategoriaMaiorGasto(itemsLog));
			System.out.println("Mes com maior gasto é: " +  getMesMaiorGasto(itemsLog));
			System.out.println("A quantidade total de gasta : " + String.format("%.2f", getMaiorGasto(itemsLog)));
			System.out.println("A quantidade total recebida é:  " + String.format("%.2f", getMaiorReceita(itemsLog)));
			System.out.println("Saldo total das movimentações é: " + String.format("%.2f", getSaldoTotal(itemsLog)));
		}
		
	}
	
	private String getCategoriaMaiorGasto(List<ItemLog> itemsLog) {
		Map<String, Double> categoriasValor = new HashMap<String, Double>();

		for(ItemLog item : itemsLog) {
			if(item.getCategoria() != null && item.getValor() < 0) {
				String categoria = item.getCategoria().trim();
				if(categoriasValor.get(categoria) == null) {
					categoriasValor.put(categoria, item.getValor());
				} else {
					categoriasValor.put(categoria, (categoriasValor.get(categoria) + item.getValor()));
				}
			}
		}

		Double valorMaior = 0d;
		String categoria = "";
		for(Map.Entry<String, Double> entry : categoriasValor.entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();

		    if(value < valorMaior) {
		    	valorMaior = value;
			    categoria = key;
		    }
		    
		}
		
		return categoria;
	}

	private String getMesMaiorGasto(List<ItemLog> itemsLog) {
				
		Map<String, Double> mesMaiorValor = new HashMap<String, Double>();

		for(ItemLog item : itemsLog) {
			if(item.getData() != null && item.getValor() < 0) {
				String data = item.getData().trim().split("-")[1];
				if(mesMaiorValor.get(data) == null) {
					mesMaiorValor.put(data, item.getValor());
				} else {
					mesMaiorValor.put(data, (mesMaiorValor.get(data) + item.getValor()));
				}
			}
		}

		Double valorMaior = 0d;
		String mes = "";
		for(Map.Entry<String, Double> entry : mesMaiorValor.entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();

		    if(value < valorMaior) {
		    	valorMaior = value;
			    mes = key;
		    }
		    
		}	
		
		
		return mes;
	}

	private Double getMaiorGasto(List<ItemLog> itemsLog) {
		
		Double gastoMes = 0d;
		for(ItemLog item : itemsLog) {
			if(item.getValor() < 0) {
				gastoMes = gastoMes + item.getValor();
			}
		}
		
		return gastoMes;
	}

	private Double getMaiorReceita(List<ItemLog> itemsLog) {
		Double ganhoMes = 0d;
		for(ItemLog item : itemsLog) {
			if(item.getValor() > 0) {
				ganhoMes = ganhoMes + item.getValor();
			}
		}
		
		return ganhoMes;
	}

	private Double getSaldoTotal(List<ItemLog> itemsLog) {
		Double saldoTotal = 0d;
		for(ItemLog item : itemsLog) {
			saldoTotal = saldoTotal + item.getValor();
		}
		
		
		return saldoTotal;
		
	}
}
