package loja;

import java.util.Date;

import org.junit.Test;

import br.unibh.loja.entidades.Client;
import junit.framework.Assert;

public class Teste {

	@Test
	public void PrintObject() {

		Client c = new Client(1L, "Igor Baesse", "Baesse", "1234567", "Consumidor", "12988494622", "31999999999",
				"igorbaesse86@gmail.com", new Date("17/05/1996"), new Date("17/05/1996"));
		System.out.print(c);

	}
	@Test
	public void EqualsObject() {

		Client c = new Client(1L, "Igor Baesse", "Baesse", "1234567", "Consumidor", "12988494622", "31999999999",
				"igorbaesse86@gmail.com", new Date("17/05/1996"), new Date("17/05/1996"));
		Client d = new Client(1L, "Igor Baesse", "Baesse", "1234567", "Consumidor", "12988494622", "31999999999",
				"igorbaesse86@gmail.com", new Date("17/05/1996"), new Date("17/05/1996"));		
		Assert.assertTrue(c.equals(d));
		

	}
	
	@Test
	public void ToStringObject() {
		Client c = new Client(1L, "Igor Baesse", "Baesse", "1234567", "Consumidor", "12988494622", "31999999999",
				"igorbaesse86@gmail.com", new Date("17/05/1996"), new Date("17/05/1996"));
		
		System.out.print(c.toString());
		
	}

}
