package projetofiep.projetofiep.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import projetofiep.projetofiep.dao.PessoaDAO;
import projetofiep.projetofiep.entidades.Pessoa;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

		private static final long serialVersionUID = 1L;

		private List<Pessoa> pessoas;
	 
	    private Pessoa pessoa = new Pessoa();
	 
	    @Inject
	    private PessoaDAO pessoaManager;
	 
	    @PostConstruct
	    public void init() {
	        this.pessoas = pessoaManager.loadAllPessoas();
	    }
	 
	    public void delete(Pessoa pessoa) {
	        pessoaManager.delete(pessoa);
	        pessoas.remove(pessoa);
	    }
	 
	    public void add() {
	        pessoaManager.addNewPessoa(pessoa);
	        this.pessoas = pessoaManager.loadAllPessoas();
	        this.pessoa = new Pessoa();
	    }
	 
	    public void update() {
	        pessoaManager.update(pessoas);
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
	    }
	
//	private List<Pessoa> pessoas =
//	         new ArrayList<>();
//	
//	public PessoaBean () {
//		 for (int i = 0; i < 20; i++) {
//             pessoas.add(generateRandomPessoa());
//      }
//	}
//	
//	public String [] nomes = {"Eduardo", "Luiz",
//	         "Henrique", "Felipe", "Bruna", "Brianda", "Sonia"};
//	
//	 public List<Pessoa> getPessoas() {
//         return pessoas;
//   }
//
//	
//	  public Pessoa generateRandomPessoa() {
//
//          int indice = (int) Math.floor(Math.random()*7);
//          Pessoa pessoa2 = new Pessoa();
//          pessoa2.setNome(nomes[indice]);
//          pessoa2.setEmail("E-mail: " + nomes[indice] + indice + "@gmail.com");
//          pessoa2.setTelefone(indice + "33212683");
//          pessoa2.setCpf("01990135943");
//          return pessoa2;
//
//    }


}
