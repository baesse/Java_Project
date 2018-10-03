package br.unibh.loja.negocio;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.unibh.loja.entidades.Client;

@Stateless
@LocalBean
public class ServicoCliente {

	@Inject
	private EntityManager em;
	
	@Inject
	private Logger log;
	
	public Client insert(Client c) throws Exception {
		log.info("Persistindo "+c);
		em.persist(c);
		return c;
	}
	
	public Client update(Client c) throws Exception {
		log.info("Atualizando "+c);
		return em.merge(c);
	}
	
	public void delete(Client c) throws Exception {
		log.info("Removendo "+c);
		Object o = em.merge(c);
		em.remove(o);
	}
	
	public Client find(Long k) throws Exception {
		log.info("Encontrando pela chave "+k);
		return em.find(Client.class, k);
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Client").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> findByName(String name) throws Exception {
		log.info("Encontrando o "+name);
		return em.createNamedQuery("Client.findByName")
		.setParameter("nome", "%"+name+"%").getResultList();
	}

}