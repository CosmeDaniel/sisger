package br.com.sisger.visao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.CidadeDAO;
import br.com.sisger.dao.EstadoDAO;
import br.com.sisger.modelo.Cidade;
import br.com.sisger.modelo.Estado;

@ManagedBean // Definindo a classe como um Mange Bean
@ViewScoped // Definindo o tempo de vida útil dentro da sessão
public class CidadeBean {
	// Criando um objeto e atributos para  receber dados
	private Cidade cidadeCadastro;
	private List<Cidade> listaCidades;
	private List<Cidade> listaCidadesFiltradas;
	private String acao;
	private Long codigo;
	
	private List<Estado> listaEstados;
	
	private Cidade selecaoCidade;
	
	//Criando métodos de acessos aos objetos criado, Gets e Sets
	public synchronized Cidade getCidadeCadastro() {
		//Verificando se obejto é nulo ao ser acessado, se nulo um novo objeto é instânciado
		if (cidadeCadastro == null) {
			cidadeCadastro = new Cidade();
		}
		return cidadeCadastro;
	}
	
	public synchronized void setCidadeCadastro(Cidade cidadeCadastro) {
		this.cidadeCadastro = cidadeCadastro;
	}

	public synchronized List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public synchronized void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public synchronized List<Cidade> getListaCidadesFiltradas() {
		return listaCidadesFiltradas;
	}

	public synchronized void setListaCidadesFiltradas(
			List<Cidade> listaCidadesFiltradas) {
		this.listaCidadesFiltradas = listaCidadesFiltradas;
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

	public synchronized List<Estado> getListaEstados() {
		return listaEstados;
	}

	public synchronized void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	public Cidade getSelecaoCidade() {
		return selecaoCidade;
	}
	
	public void setSelecaoCidade(Cidade selecaoCidade) {
		this.selecaoCidade = selecaoCidade;
		if (selecaoCidade != null) {
			selecaoCidade.getCodCidade();
		}
	}
	
	// Criando Método para excutar o método Salvar contido na DAO
	public void salvar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
			CidadeDAO cidadeDAO = new CidadeDAO();
			//Executando executando método salvar criado na Classe DAO
			cidadeDAO.salvar(cidadeCadastro);
			
			//Executando método de exibição de mensagem na Classe FacesUtil e criando mensagem
			FacesUtil.msgSucesso("Cidade Salva com Sucesso!");
			
			cidadeCadastro = new Cidade();
			
			//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Cidade não pode ser Salva dados já cadastrados!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Cidade." + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Listar contido na DAO
	public void carregarPesquisa(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
			CidadeDAO cidadeDAO = new CidadeDAO();
			
			//Executando executando método listar criado na Classe DAO
			listaCidades = cidadeDAO.listar();
			
			//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Lista Pesquisa de Cidades." + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Buscar por Código contido na DAO
	public void carregarCadastro(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Pegando valor passado da página de cadastro
			String valor = FacesUtil.getParam("codCidade");
			
			//Executando verificação se variável contém valores
			if (valor != null) {
				//Criando um variável temporária codigo e convertendo o a varável valor
				Long codigo = Long.parseLong(valor);
				//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
				CidadeDAO cidadeDAO = new CidadeDAO();
				//Executando método Buscar por Código criado na classe DAO e inserindo em variável
				cidadeCadastro = cidadeDAO.buscarPorCodigo(codigo);
				
			}else{
				cidadeCadastro = new Cidade();
			}
			//Criando um novo EstadoDAO para acesso aos metodos contidos na DAO
			EstadoDAO estadoDAO = new EstadoDAO();
			//Executando método listar dentro da classe DAO e carregando um lista de estados
			listaEstados = estadoDAO.listar();
			
			//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
			} catch (RuntimeException ex) {
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				ex.printStackTrace();
			FacesUtil.msgErro("Erro ao Carregar Cadastro das Cidades." + ex.getMessage());
		}
	}
	
	// Criando Método para excutar o método Excluir contido na DAO
	public void excluir(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidadeCadastro);
			
			FacesUtil.msgSucesso("Cidade Excluida com Sucesso.");
			
			cidadeCadastro = new Cidade();
			
			//Caso haja alguem erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de exclusão do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Cidade não pode ser Excluída! Cidade "+
				cidadeCadastro.getNomeCidade() +" está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Cidade." + ex.getMessage());
			}
		}
	}
	
	// Criando Método para excutar o método Editar contido na DAO
	public void editar(){
		//Método obrigatório para garantir teste antes da execução dos comandos
		try {
			//Criando uma nova CidadeDAO para acesso aos metodos contidos na DAO
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.editar(cidadeCadastro);
			
			FacesUtil.msgSucesso("Cidade Editada com Sucesso!");
			
			cidadeCadastro = new Cidade();
			
			//Caso haja algum erro ao executar Try O catch é executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribuiíndo erro capturado a uma variável
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impressão do log de erro para análise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento é exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de não permissão de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Cidade não pode ser Editada! Cidade "+
				cidadeCadastro.getNomeCidade() +" está em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Cidade." + ex.getMessage());
			}
		}
	}
}
