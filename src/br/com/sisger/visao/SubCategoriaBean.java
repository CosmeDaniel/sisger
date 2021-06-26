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
// Definindo o tempo de vida útil dentro da sessão
public class SubCategoriaBean {
	// Criando um objeto e varáveis para receber dados
	private SubCategoria subCategoriaCadastro;
	private List<SubCategoria> listaSubCategorias;
	private ArrayList<SubCategoria> listaSubCategoriasFiltradas;
	private String acao;
	private Long codigo;

	private List<Categoria> listaCategorias;

	//Criando métodos de acessos aos objetos criado, Gets e Sets
	public synchronized SubCategoria getSubCategoriaCadastro() {
		//Verificando se obejto é nulo ao ser acessado, se nulo um novo objeto é instânciado
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

	// Criando Método para Criar um novo objeto da classe
	public void novo(){
		subCategoriaCadastro = new SubCategoria();
		
	}
	
	// Criando Método para excutar o método Salvar contido na DAO
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			//Executando executando método salvar criado na Classe DAO
			subCategoriaDAO.salvar(subCategoriaCadastro);
			
			//Executando método de exibição de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("SubCategoria Salva com Sucesso!");
			subCategoriaCadastro = new SubCategoria();
			
		//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Sub-Categoria não pode ser Salvar, dados estão em uso!");
				
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Subcategoria!" + ex.getMessage());
			}
		}
	}

	// Criando Método para excutar o método Listar contido na DAO
	public void carregarPesquisa(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			//Executando executando método listar criado na Classe DAO
			listaSubCategorias = subCategoriaDAO.listar();
			
			
		//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Lista de Pesquisa." + ex.getMessage());	
		}
	}
	
	// Criando Método para excutar o método Buscar por Código contido na DAO
	public void carregarCadastro(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Pegando valor passado da página de cadastro
			String valor = FacesUtil.getParam("codSubCategoria"); 
			//Executando verificação se variável contém valores
			if (valor != null) {
				//Criando um variável temporária codigo e convertendo o a varável valor
				Long codigo = Long.parseLong(valor);
				
				SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
				//Executando método Buscar por Código criado na classe DAO e inserindo em variável
				subCategoriaCadastro = subCategoriaDAO.buscarPorCodigo(codigo);
				
				
			} else {
				subCategoriaCadastro = new SubCategoria();
			}
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			//Executando método listar dentro da classe DAO e carregando um lista de Categorias
			listaCategorias = categoriaDAO.listar();
			
		//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Capiturando o erro ocorrido para ser impresso com a mensagem
			ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Subcategorias!" + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Excluir contido na DAO
	public void excluir(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			subCategoriaDAO.excluir(subCategoriaCadastro);		
			
			FacesUtil.msgSucesso("Subcategoria Excluída com Sucesso!");
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Sub-Categoria não pode ser Excluída! Sub-Categoria "+
				subCategoriaCadastro.getNomeSubCategoria() +" está em uso.");
				
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Subcategoria!" + ex.getMessage());
			}
		}		
	}
	
	// Criando Método para excutar o método Editar contido na DAO
	public void editar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
			subCategoriaDAO.editar(subCategoriaCadastro);
			
			FacesUtil.msgSucesso("Subcategoria Editada com Sucesso!");
			
		//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Sub-Categoria não pode ser Editar, dados estão em uso!");
				
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Subcategoria!" + ex.getMessage());
			}
		}
	}
}
