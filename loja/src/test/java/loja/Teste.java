package loja;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Client;
import br.unibh.loja.entidades.Produto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Teste {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void PrintObject() {

		Client c = new Client(1L, "Igor Baesse", "Baesse", "1234567", "Consumidor", "12988494622", "31999999999",
				"igorbaesse86@gmail.com", new Date("17/05/1996"), new Date("17/05/1996"));
		System.out.println(c);

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

		System.out.println(c.toString());

	}

	@Test
	public void CreateClientValid() {
		Client c = new Client(1L, "Igor Baesse", "cliente@client", "123456789", "perfil", "12988494622", "(31)9999-1111",
				"cliente@cliente.com", new Date(), new Date());
		System.out.print(c);
		Set<ConstraintViolation<Client>> constraintViolations = validator.validate(c);
		for (ConstraintViolation<Client> cl : constraintViolations) {
			System.out.println(" Erro de Validacao Client valido: " + cl.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());

	}
	
	@Test
	public void CreateClientNotValid() {
		Client c = new Client(1L, "", "cliente@client", "123456789", "perfil", "12988494622", "(31)99991111",
				"cliente@cliente.com", new Date(), new Date());
		System.out.print(c);
		Set<ConstraintViolation<Client>> constraintViolations = validator.validate(c);
		for (ConstraintViolation<Client> cl : constraintViolations) {
			System.out.println(" Erro de Validacao Cliente invalido: " + cl.getMessage());
		}
		Assert.assertEquals(4, constraintViolations.size());

	}
	
	@Test
	public void testCategori() {
		Categoria c = new Categoria(1L, "Categoria");
		System.out.println(c);
		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate( c );
		for (ConstraintViolation<Categoria> ct: constraintViolations) {
			System.out.println(" Erro de Validacao Categoria valida: "+ct.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size() );
	}

	@Test
	public void testCategoriInvalid() {
		Categoria c = new Categoria(1L, "");
		System.out.println(c);
		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate( c );
		for (ConstraintViolation<Categoria> ct: constraintViolations) {
			System.out.println(" Erro de Validacao Categoria valida: "+ct.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size() );
	}
	
	

	@Test
	public void testeProdutoValid() {
		Categoria c = new Categoria(1L, "Categoria");
		Produto p = new Produto(1L, "Prato", "Um produto qualuqer", c, new BigDecimal(5), "teste");
		assertEquals(p.getNome(), "Prato");
		System.out.println(p);
		
		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate( p );
		for (ConstraintViolation<Produto> pd: constraintViolations) {
			System.out.println(" Erro de Validacao Produto valido : "+pd.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size() );
	}
	

	@Test
	public void testeProdutoInValid() {
		Categoria c = new Categoria();
		Produto p = new Produto(1L, "prato", "Um produto qualuqer", c, new BigDecimal(5), "te11ste");
		assertEquals(p.getNome(), "prato");
		System.out.println(p);
		
		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate( p );
		for (ConstraintViolation<Produto> pd: constraintViolations) {
			System.out.println(" Erro de Validacao Produto valido : "+pd.getMessage());
		}
		Assert.assertEquals(3, constraintViolations.size() );
	}

}
