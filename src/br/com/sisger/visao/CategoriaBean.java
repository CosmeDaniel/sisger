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
//Definindo o tempo de vida útil dentro da sessão
@ViewScoped

// Criando um objeto e atributos para  receber dados	
public class CategoriaBean {

	private Categoria categoriaCadastro;
	private List<Categoria> listaCategorias;
	private ArrayList<Categoria> listaCategoriasFiltradas;
	private String acao;
	private String codigo;

	//Criando métodos de acessos aos objetos criado, Gets e Sets
	public synchronized Categoria getCategoriaCadastro() {
		//Verificando se obejto é nulo ao ser acessado, se nulo um novo objeto é instânciado
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
	
	// Criando Método para excutar o método Salvar contido na DAO
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Estênciando uma nova categoria para acessar os metodos de manipulação
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando executando método salvar criado na Classe DAO
			categoriaDAO.salvar(categoriaCadastro);
			
			//Emitindo mensagem de sucesso caso não ocorra erro.
			FacesUtil.msgSucesso("Categoria Salva com Sucesso!");
			categoriaCadastro = new Categoria();
			
		//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Categoria não pode ser Salva, dados já existe!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Cadastrar Catagoria!" + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Listar contido na DAO
	public void carregarPesquisa(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando método listar criado na Classe DAO
			listaCategorias =  categoriaDAO.listar();
		
		//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Listar Categoria." + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Buscar por Código contido na DAO
	public void carregarCadastro(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
		//Pegando Codigo da Categoria passado da página de cadastro via URL por parâmetro
		String codCategoria = FacesUtil.getParam("codCategoria");
		
		//Executando verificação se variável contém valores
		if (codCategoria != null) {
			//Criando um variável temporária codigo e convertendo o a varável valor
			Long codigo = Long.parseLong(codCategoria);
			
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando método Buscar por Código criado na classe DAO e inserindo no objeto categoria
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
	
	// Criando Método para excutar o método Exclulir contido na DAO
	public void excluir(){
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.excluir(categoriaCadastro);
			
			FacesUtil.msgSucesso("Categoria Excluída com Sucesso!");
			categoriaCadastro = new Categoria();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Categoria não pode ser Excluída! Categoria "+
				categoriaCadastro.getNomeCategoria() +" está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Catagoria!" + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Editar contido na DAO
	public void editar(){
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.editar(categoriaCadastro);
			
			FacesUtil.msgSucesso("Categoria Editada com Sucesso!");
			categoriaCadastro = new Categoria();
			
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de edição do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Categoria não pode ser Editada!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Catagoria!" + ex.getMessage());
			}
		}
	}
}	
