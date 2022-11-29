package projetofiep.projetofiep.dao;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projetofiep.projetofiep.entidades.Pessoa;

@Stateless
public class PessoaDAO<E> {
	
	 @PersistenceContext
	    private EntityManager entityManager;
	 
	    public List<Pessoa> loadAllPessoas() {
	        return this.entityManager.createQuery("SELECT * FROM Pessoa", Pessoa.class).getResultList();
	    }
	 
	    public void delete(Pessoa customer) {
	        if (entityManager.contains(customer)) {
	            entityManager.remove(customer);
	        } else {
	            Pessoa managedPessoa = entityManager.find(Pessoa.class, customer.getId());
	            if (managedPessoa != null) {
	                entityManager.remove(managedPessoa);
	            }
	        }
	    }
	 
	    public void addNewPessoa(Pessoa pessoa) {
	 
	        Pessoa newPessoa = new Pessoa();
	        newPessoa.setNome(pessoa.getNome());
	        newPessoa.setEmail(pessoa.getEmail());
	        newPessoa.setTelefone(pessoa.getTelefone());
	        newPessoa.setCpf(pessoa.getCpf());
	        newPessoa.setId(UUID.randomUUID().timestamp());
	 
	        this.entityManager.persist(newPessoa);
	    }
	 
	    @SuppressWarnings("unchecked")
		public void update(List<Pessoa> pessoas) {
	        pessoas.forEach((Consumer<? super Pessoa>) entityManager.merge(pessoas));
	    }
	}