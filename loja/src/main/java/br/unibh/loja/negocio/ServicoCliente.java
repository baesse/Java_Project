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

public class ServicoCliente implements DAO<Client, Long> {
	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	@Override
	public Client insert(Client t) throws Exception {
		
		
		log.info("Persistindo " + t);
		em.persist(t);
		return t;

	}

	@Override
	public Client update(Client t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);

	}

	@Override
	public void delete(Client t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);

	}

	@Override
	public Client find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Client.class, k);
	}

	@Override
	public List<Client> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Client").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Client> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Client.findByName").setParameter("nome", "%" + name + "%").getResultList();
	}

}
