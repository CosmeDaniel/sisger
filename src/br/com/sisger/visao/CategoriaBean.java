package br.com.sisger.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.CategoriaDAO;
import br.com.sisger.modelo.Categoria;

//Definindo a classe como um Mange Bean
@ManagedBean
//Definindo o tempo de vida �til dentro da sess�o
@ViewScoped

// Criando um objeto e atributos para  receber dados	
public class CategoriaBean {

	private Categoria categoriaCadastro;
	private List<Categoria> listaCategorias;
	private ArrayList<Categoria> listaCategoriasFiltradas;
	private String acao;
	private String codigo;

	//Criando m�todos de acessos aos objetos criado, Gets e Sets
	public synchronized Categoria getCategoriaCadastro() {
		//Verificando se obejto � nulo ao ser acessado, se nulo um novo objeto � inst�nciado
		if (categoriaCadastro == null) {
			categoriaCadastro = new Categoria();
		} else {

		}
		return categoriaCadastro;
	}

	public synchronized void setCategoriaCadastro(Categoria categoriaCadastro) {
		this.categoriaCadastro = categoriaCadastro;
	}

	public synchronized List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public synchronized void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public synchronized ArrayList<Categoria> getListaCategoriasFiltradas() {
		return listaCategoriasFiltradas;
	}

	public synchronized void setListaCategoriasFiltradas(
			ArrayList<Categoria> listaCategoriasFiltradas) {
		this.listaCategoriasFiltradas = listaCategoriasFiltradas;
	}

	public synchronized String getAcao() {
		return acao;
	}

	public synchronized void setAcao(String acao) {
		this.acao = acao;
	}

	public synchronized String getCodigo() {
		return codigo;
	}

	public synchronized void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	// Criando M�todo para excutar o m�todo Salvar contido na DAO
	public void salvar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Est�nciando uma nova categoria para acessar os metodos de manipula��o
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando executando m�todo salvar criado na Classe DAO
			categoriaDAO.salvar(categoriaCadastro);
			
			//Emitindo mensagem de sucesso caso n�o ocorra erro.
			FacesUtil.msgSucesso("Categoria Salva com Sucesso!");
			categoriaCadastro = new Categoria();
			
		//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Categoria n�o pode ser Salva, dados j� existe!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Cadastrar Catagoria!" + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Listar contido na DAO
	public void carregarPesquisa(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando m�todo listar criado na Classe DAO
			listaCategorias =  categoriaDAO.listar();
		
		//Caso haja algum erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Listar Categoria." + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Buscar por C�digo contido na DAO
	public void carregarCadastro(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
		//Pegando Codigo da Categoria passado da p�gina de cadastro via URL por par�metro
		String codCategoria = FacesUtil.getParam("codCategoria");
		
		//Executando verifica��o se vari�vel cont�m valores
		if (codCategoria != null) {
			//Criando um vari�vel tempor�ria codigo e convertendo o a var�vel valor
			Long codigo = Long.parseLong(codCategoria);
			
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo no objeto categoria
			categoriaCadastro = categoriaDAO.buscarPorCodigo(codigo);
		}else {
			categoriaCadastro = new Categoria();
		}			
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();			
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Categorias." + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Exclulir contido na DAO
	public void excluir(){
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.excluir(categoriaCadastro);
			
			FacesUtil.msgSucesso("Categoria Exclu�da com Sucesso!");
			categoriaCadastro = new Categoria();
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Categoria n�o pode ser Exclu�da! Categoria "+
				categoriaCadastro.getNomeCategoria() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Catagoria!" + ex.getMessage());
			}
		}
	}
	
	// Criando M�todo para excutar o m�todo Editar contido na DAO
	public void editar(){
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.editar(categoriaCadastro);
			
			FacesUtil.msgSucesso("Categoria Editada com Sucesso!");
			categoriaCadastro = new Categoria();
			
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de edi��o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Categoria n�o pode ser Editada!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Catagoria!" + ex.getMessage());
			}
		}
	}
}	
