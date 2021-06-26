package br.com.sisger.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.CategoriaDAO;
import br.com.sisger.dao.SubCategoriaDAO;
import br.com.sisger.modelo.Categoria;
import br.com.sisger.modelo.SubCategoria;

@ManagedBean
// Definindo a classe como um Mange Bean
@ViewScoped
// Definindo o tempo de vida �til dentro da sess�o
public class SubCategoriaBean {
	// Criando um objeto e var�veis para receber dados
	private SubCategoria subCategoriaCadastro;
	private List<SubCategoria> listaSubCategorias;
	private ArrayList<SubCategoria> listaSubCategoriasFiltradas;
	private String acao;
	private Long codigo;

	private List<Categoria> listaCategorias;

	//Criando m�todos de acessos aos objetos criado, Gets e Sets
	public synchronized SubCategoria getSubCategoriaCadastro() {
		//Verificando se obejto � nulo ao ser acessado, se nulo um novo objeto � inst�nciado
		if (subCategoriaCadastro == null) {
			subCategoriaCadastro = new SubCategoria();
			
		}
		return subCategoriaCadastro;
	}

	public synchronized void setSubCategoriaCadastro(
			SubCategoria subCategoriaCadastro) {
		this.subCategoriaCadastro = subCategoriaCadastro;
	}

	public synchronized List<SubCategoria> getListaSubCategorias() {
		return listaSubCategorias;
	}

	public synchronized void setListaSubCategorias(
			List<SubCategoria> listaSubCategorias) {
		this.listaSubCategorias = listaSubCategorias;
	}

	public synchronized ArrayList<SubCategoria> getListaSubCategoriasFiltradas() {
		return listaSubCategoriasFiltradas;
	}

	public synchronized void setListaSubCategoriasFiltradas(
			ArrayList<SubCategoria> listaSubCategoriasFiltradas) {
		this.listaSubCategoriasFiltradas = listaSubCategoriasFiltradas;
	}

	public synchronized String getAcao() {
		return acao;
	}

	public synchronized void setAcao(String acao) {
		this.acao = acao;
	}

	public synchronized Long getCodigo() {
		return codigo;
	}

	public synchronized void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public synchronized List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public synchronized void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	// Criando M�todo para Criar um novo objeto da classe
	public void novo(){
		subCategoriaCadastro = new SubCategoria();
		
	}
	
	// Criando M�todo para excutar o m�todo Salvar contido na DAO
	public void salvar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			//Executando executando m�todo salvar criado na Classe DAO
			subCategoriaDAO.salvar(subCategoriaCadastro);
			
			//Executando m�todo de exibi��o de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("SubCategoria Salva com Sucesso!");
			subCategoriaCadastro = new SubCategoria();
			
		//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Sub-Categoria n�o pode ser Salvar, dados est�o em uso!");
				
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Subcategoria!" + ex.getMessage());
			}
		}
	}

	// Criando M�todo para excutar o m�todo Listar contido na DAO
	public void carregarPesquisa(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			//Executando executando m�todo listar criado na Classe DAO
			listaSubCategorias = subCategoriaDAO.listar();
			
			
		//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Lista de Pesquisa." + ex.getMessage());	
		}
	}
	
	// Criando M�todo para excutar o m�todo Buscar por C�digo contido na DAO
	public void carregarCadastro(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			//Pegando valor passado da p�gina de cadastro
			String valor = FacesUtil.getParam("codSubCategoria"); 
			//Executando verifica��o se vari�vel cont�m valores
			if (valor != null) {
				//Criando um vari�vel tempor�ria codigo e convertendo o a var�vel valor
				Long codigo = Long.parseLong(valor);
				
				SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
				//Executando m�todo Buscar por C�digo criado na classe DAO e inserindo em vari�vel
				subCategoriaCadastro = subCategoriaDAO.buscarPorCodigo(codigo);
				
				
			} else {
				subCategoriaCadastro = new SubCategoria();
			}
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando m�todo listar dentro da classe DAO e carregando um lista de Categorias
			listaCategorias = categoriaDAO.listar();
			
		//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Subcategorias!" + ex.getMessage());
		}
	}
	
	// Criando M�todo para excutar o m�todo Excluir contido na DAO
	public void excluir(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			subCategoriaDAO.excluir(subCategoriaCadastro);		
			
			FacesUtil.msgSucesso("Subcategoria Exclu�da com Sucesso!");
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Sub-Categoria n�o pode ser Exclu�da! Sub-Categoria "+
				subCategoriaCadastro.getNomeSubCategoria() +" est� em uso.");
				
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Subcategoria!" + ex.getMessage());
			}
		}		
	}
	
	// Criando M�todo para excutar o m�todo Editar contido na DAO
	public void editar(){
		//M�todo obrigat�rio para garantir teste antes da execu��o dos comandos
		try {
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			subCategoriaDAO.editar(subCategoriaCadastro);
			
			FacesUtil.msgSucesso("Subcategoria Editada com Sucesso!");
			
		//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Sub-Categoria n�o pode ser Editar, dados est�o em uso!");
				
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Subcategoria!" + ex.getMessage());
			}
		}
	}
}
