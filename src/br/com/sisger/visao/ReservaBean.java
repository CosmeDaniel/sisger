package br.com.sisger.visao;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.sisger.controle.FacesUtil;
import br.com.sisger.dao.RecursoDAO;
import br.com.sisger.dao.ReservaDAO;
import br.com.sisger.dao.StatusDAO;
import br.com.sisger.dao.SubCategoriaDAO;
import br.com.sisger.dao.UsuarioDAO;
import br.com.sisger.modelo.Recurso;
import br.com.sisger.modelo.Reserva;
import br.com.sisger.modelo.Status;
import br.com.sisger.modelo.SubCategoria;
import br.com.sisger.modelo.Usuario;

@ManagedBean
@ViewScoped
public class ReservaBean {
	private Reserva reservaCadastro;
	private List<Reserva> listaReserva;
	private List<Reserva> listaReservaFiltradas;
	private String acao;
	private Long codigo;
	private String usuario;
	private Date dataIni;
	private Date dataFim;
	private String subCategoria;
	private String selecaoRecurso;
	private String bloqueaCalendario = null;
	private String bloqueaSelecionarRec;
	private boolean reservaLiberada;
	private SubCategoria subCategoriaReserva;
	private String funcaoUsuarioLogado;
	private Recurso recurso;
	private boolean usuarioAdmin = false;
	private Long codReservaEditar;
	
	private List<Usuario> listaUsuario;
	private List<Recurso> listaRecurso;
	private List<Status> listaStatus;
	private List<Reserva> listaRecursoReserva;
	
	private List<Reserva> listaRecursoReservaFomatada;
	
	private String funcaoUsuario;
		
	Date validaDataIni;
	Date validaDataFim;
	Usuario validaUsuario;
	SubCategoria validaRecurso;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	AutenticacaoBean autenticacaoBean;
	
	public List<Reserva> getListaRecursoReservaFomatada() {
		return listaRecursoReservaFomatada;
	}
	
	public void setListaRecursoReservaFomatada(
			List<Reserva> listaRecursoReservaFomatada) {
		this.listaRecursoReservaFomatada = listaRecursoReservaFomatada;
	}
	
	public synchronized Reserva getReservaCadastro() {
		if (reservaCadastro == null) {
			reservaCadastro = new Reserva();
		}
		return reservaCadastro;
	}

	public synchronized void setReservaCadastro(Reserva reservaCadastro) {
		this.reservaCadastro = reservaCadastro;
	}

	public synchronized List<Reserva> getListaReserva() {
		return listaReserva;
	}

	public synchronized void setListaReserva(List<Reserva> listaReserva) {
		this.listaReserva = listaReserva;
	}

	public synchronized List<Reserva> getListaReservaFiltradas() {
		return listaReservaFiltradas;
	}

	public synchronized void setListaReservaFiltradas(
			List<Reserva> listaReservaFiltradas) {
		this.listaReservaFiltradas = listaReservaFiltradas;
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

	public synchronized List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public synchronized void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public synchronized List<Recurso> getListaRecurso() {
		return listaRecurso;
	}

	public synchronized void setListaRecurso(List<Recurso> listaRecurso) {
		this.listaRecurso = listaRecurso;
	}

	public synchronized List<Status> getListaStatus() {
		return listaStatus;
	}
	
	public List<Reserva> getListaRecursoReserva() {
		return listaRecursoReserva;
	}
	
	public void setListaRecursoReserva(List<Reserva> listaRecursoReserva) {
		this.listaRecursoReserva = listaRecursoReserva;
	}

	public synchronized void setListaStatus(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}
	
	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public String getSelecaoRecurso() {
		return selecaoRecurso;
	}
	
	public void setSelecaoRecurso(String selecaoRecurso) {
		this.selecaoRecurso = selecaoRecurso;
	}
	
	public String getFuncaoUsuario() {
		return funcaoUsuario;
	}
	
	public void setFuncaoUsuario(String funcaoUsuario) {
		this.funcaoUsuario = funcaoUsuario;
	}
		
	public void novo() {
		reservaCadastro = new Reserva();

	}
	
	public synchronized String getUsuario() {
		
		return usuario;
	}

	public synchronized void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public synchronized String getSubCategoria() {
		
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}	
	
	public synchronized void Recurso(String subCategoria) {
		
		this.subCategoria = subCategoria;
	}

	public Date getDataIni() {
		return dataIni;
	}
	
	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}
	
	public Date getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(Date dataFim) {
		if (dataFim == null) {
			dataFim = new Date();
		}
		this.dataFim = dataFim;
	}
	
	public String getBloqueaCalendario() {
		return bloqueaCalendario;
	}
	
	public void setBloqueaCalendario(String bloqueaCalendario) {
		this.bloqueaCalendario = bloqueaCalendario;
	}

	public String getBloqueaSelecionarRec() {
		return bloqueaSelecionarRec;
	}
	
	public void setBloqueaSelecionarRec(String bloqueaSelecionarRec) {
		this.bloqueaSelecionarRec = bloqueaSelecionarRec;
	}
	
	public synchronized boolean isReservaLiberada() {
		return reservaLiberada;
	}

	public synchronized void setReservaLiberada(boolean reservaLiberada) {
		this.reservaLiberada = reservaLiberada;
	}
	
	public SubCategoria getSubCategoriaReserva() {
		if (subCategoriaReserva == null) {
			subCategoriaReserva = new SubCategoria();
		}
		return subCategoriaReserva;
	}
	
	public void setSubCategoriaReserva(SubCategoria subCategoriaReserva) {
		this.subCategoriaReserva = subCategoriaReserva;
	}
	
	public Recurso getRecurso() {
		if (recurso == null) {
			recurso = new Recurso();
		}
		return recurso;
	}
	
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	
	//M�todo que pega os dados do rescurso para salvar ou editar
	public void recursoReservaSalvaEdita(){	
		if (selecaoRecurso != null) {
			RecursoDAO recursoDAO = new RecursoDAO();
			recurso = recursoDAO.buscarPorCodigo(selecaoRecurso);
			reservaCadastro.setRecurso(recurso);
			reservaCadastro.setPatrimonioRecursoReserva(recurso.getPatrimonioRecurso());
		
		}
	}
	
	public void salvar() {
		recursoReservaSalvaEdita();
		ReservaDAO reservaDAO = new ReservaDAO();
		
		try {	
			if (usuarioAdmin == true) {				
				
				reservaDAO.salvar(reservaCadastro);		
				
				//Editando o status do recurso reservado na tabela recurso para ele n�o estar mais na lista de reserva
				alterarStatusRecurso();
				
				FacesUtil.msgSucesso("Reserva Salva com Sucesso!");	
				
				reservaCadastro = new Reserva();
					
			}else{
				reservaCadastro.setUsuario(autenticacaoBean.getUsuarioAutenticado());
				
				reservaDAO.salvar(reservaCadastro);	
				
				//Editando o status do recurso reservado na tabela recurso para ele n�o estar mais na lista de reserva
				alterarStatusRecurso();
				
				FacesUtil.msgSucesso("Reserva Salva com Sucesso!");	
				
				reservaCadastro = new Reserva();
			}	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Salvar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Reserva n�o pode ser Salva, dados est� em uso!");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Salvar Reserva!" + ex.getMessage());
			}
		}
	}

	//M�todos para pegar data Inicial escolhida pelo usu�rio para periodo de reserva
	public void pegarDataInicial(){
		//Verificando se o campo data Inicial da reserva est� nulo se tiver valor e pego
		if (dataIni == null && dataFim == null) {
			dataIni = reservaCadastro.getDataInicialReserva();
			
			bloqueaCalendario = FacesUtil.getParam("Ativar");				
		
		}else if(dataIni != null && dataFim == null){
			dataIni = reservaCadastro.getDataInicialReserva();
			
			bloqueaCalendario = FacesUtil.getParam("Ativar");			
		}
	}
	
	//M�todos para pegar data Final escolhida pelo usu�rio para periodo de reserva
	public void pegarDataFinal(){
		//Pegando data selecionada pelo usu�rios
		dataFim = reservaCadastro.getDataFinalReserva();
		
		if(dataFim.before(dataIni) || dataFim.equals(dataIni)){
			FacesUtil.msgErro("Data Final n�o poder ser Igual ou Maior que Data Inicial!");
			bloqueaSelecionarRec = null;
		}else{
			bloqueaSelecionarRec = "Ativar";
		}
	}

	//M�todo que verifica Recurso, Usu�rio e Data Inicial e Final da reserva.
	public void verificaConteudoDaReserva(){
		boolean achou = false;
		//Bloqueando Bot�o de selecionar Recurso ap�s sele��o
		if(bloqueaSelecionarRec != null){bloqueaSelecionarRec = null; }
		
		//Pegando o C�digo do Recurso passado por parametro
		selecaoRecurso = FacesUtil.getParam("codRecurso");
		//Buscando Recurso pelo c�digo passado ao selecionar recurso para verifica��o e edi��o
		RecursoDAO recursoDAO = new RecursoDAO();
		recurso = recursoDAO.buscarPorCodigo(selecaoRecurso);
				
		usuario = FacesUtil.getParam("usuario");			
		subCategoria = FacesUtil.getParam("nomeSubCategoria");	
				
		//Buscando reservas que existam para validade disponibilidade do recurso
		ReservaDAO reservaDAO = new ReservaDAO();
		listaRecursoReserva = reservaDAO.listar();
		
		//Busca usuario no banco para modificar String usuario para objeto usuario para compara��o
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuarioBuscado = usuarioDAO.buscarPorCodigoString(usuario);
		
		//Busca SubCategoria no banco para modificar String SubCategoria para objeto SubCategoria para compara��o
		SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
		SubCategoria subCategoriaBuscada = subCategoriaDAO.buscarPorNome(subCategoria); 
				
		//Criando um novo contexto para passar um parametro externo
		RequestContext fechaTelaPegarRecurso = RequestContext.getCurrentInstance();
		fechaTelaPegarRecurso.addCallbackParam("equipamentoLiberado", true);
		
			//Criando um la�o de repeti��o para verificar os dados de cada reserva da lista
			for (Reserva recursoReservado : listaRecursoReserva) {
				validaDataIni = recursoReservado.getDataInicialReserva();
				validaDataFim = recursoReservado.getDataFinalReserva();
				validaUsuario = recursoReservado.getUsuario();
				validaRecurso = recursoReservado.getRecurso().getSubCategoria();
				
				//Verifica��o se Usu�rio est� reservando um mesmo recursoque ja reservou
				if ((validaRecurso.getNomeSubCategoria().equals(subCategoriaBuscada.getNomeSubCategoria()) && validaUsuario.getNomeUsuario().equals(usuarioBuscado.getNomeUsuario())
				  && (dataIni.equals(validaDataIni) || (dataFim.equals(validaDataFim))
				  || (dataIni.equals(validaDataIni) && (dataIni.before(validaDataFim))
				  || (dataFim.equals(validaDataFim) && (dataFim.before(validaDataFim))))))){
					achou = true;
					break;
				}
				}if(achou){
					FacesUtil.msgErro("J� existe uma Reserva deste Recurso para, "+ usuarioBuscado.getNomeUsuario() +" no mesmo per�odo.");		
					fechaTelaPegarRecurso.addCallbackParam("equipamentoLiberado", false);
					
				}else{
					//Adicionando valores de parametros passados para a tela xhtml
					fechaTelaPegarRecurso.addCallbackParam("equipamentoLiberado", true);
					
					FacesUtil.msgSucesso("Equimento Liberado!");
					
				}
	}
	
	public void carregarPesquisa() {
		funcaoUsuarioLogado = autenticacaoBean.getUsuarioAutenticado().getFuncaoUsuario().getDescricaoFuncaoUsuario();
		
		if (funcaoUsuarioLogado.equals("ADMINISTRADOR") || (funcaoUsuarioLogado.equals("SUPERVISOR"))) {			
			usuarioAdmin = true;
		}
		try {
			//Pegando Fun��o do usu�rio logado para permitir ou n�o que ele reserve recurso para outro usu�rio
			String funcaoUsuarioLogado = autenticacaoBean.getUsuarioAutenticado().getFuncaoUsuario().getDescricaoFuncaoUsuario();
			ReservaDAO reservaDAO = new ReservaDAO();
			
			//Verificando se usu�rio � um administrador(Se sim todas as reservas da unidade � carregada)
			if (usuarioAdmin == true) {
				funcaoUsuario = funcaoUsuarioLogado;
				//Pegando o unidade logada no sistema para carregar lista de reserva que pertence aos usu�rios dela
				Long unidadeLogada = autenticacaoBean.getUsuarioAutenticado().getUnidade().getCodUnidade();
				
				listaReserva = reservaDAO.buscarPorUnidade(unidadeLogada);
				
			//Se usu�rio n�o for um administrador somente as reservas do usu�rio � carregada na lista	
			}else{
				//Pegando o c�digo so usu�rio logado para carregar somenta as reservas dele.
				Long reservaDoUsuario = autenticacaoBean.getUsuarioAutenticado().getCodUsuario();
				listaReserva = reservaDAO.buscarReservaPorUsuario(reservaDoUsuario);

			}	
		} catch (RuntimeException ex) {
			FacesUtil.msgErro("Erro ao Carregar Pesquisa de Reserva!" + ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			String valor = FacesUtil.getParam("codReserva");
					
			if (valor != null) {
				Long codigo = Long.parseLong(valor);
				
				ReservaDAO reservaDAO = new ReservaDAO();
				reservaCadastro = reservaDAO.buscarPorCodigo(codigo);
				
				codReservaEditar = reservaCadastro.getCodReserva();
				
			} else {
				reservaCadastro = new Reserva();
			}
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			listaUsuario = usuarioDAO.listar();
			
			RecursoDAO recursoDAO = new RecursoDAO();
			listaRecurso = recursoDAO.listar();

			StatusDAO statusDAO = new StatusDAO();
			listaStatus = statusDAO.buscarPorInterv(3L, 5L);

		} catch (RuntimeException ex) {
			FacesUtil.msgErro("Erro ao Carregar Cadastro de Reservas!" + ex.getMessage());
		}
	}

	public void excluir(){
		try {
			ReservaDAO reservaDAO = new ReservaDAO();
			reservaDAO.excluir(reservaCadastro);
			
			FacesUtil.msgSucesso("Reserva Exclu�da com Sucesso!");
			
			//Caso haja alguem erro ao executar Try O catch � executado emitindo uma menssagem de erro	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de exclus�o do valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Reserva n�o pode ser Exclu�da! Reserva C�d.: "+
				reservaCadastro.getCodReserva() +" est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Excluir Reserva!" + ex.getMessage());
			}
		}
	}
		
	public void editar(){
		recursoReservaSalvaEdita();
		ReservaDAO reservaDAO = new ReservaDAO();
		if (reservaCadastro.getCodReserva() == null) {
			reservaCadastro.setCodReserva(codReservaEditar);
		}
		try {	
			if (usuarioAdmin == true) {				
				
				reservaDAO.editar(reservaCadastro);		
				FacesUtil.msgSucesso("Reserva Editada com Sucesso!");	
				
				reservaCadastro = new Reserva();
					
			}else{
				reservaCadastro.setUsuario(autenticacaoBean.getUsuarioAutenticado());
				
				reservaDAO.editar(reservaCadastro);		
				
				FacesUtil.msgSucesso("Reserva Editada com Sucesso!");	
				
				reservaCadastro = new Reserva();
			}	
		} catch (RuntimeException ex) {
			//Atribui�ndo erro capturado a uma vari�vel
			String erroUso = ex.getMessage();
			//Capiturando mensagem de erro e gerando impress�o do log de erro para an�lise
			ex.printStackTrace();
			//Verificando mensagem de erro para tratamento(Se mensagem capturada for igual msg de tratamento � exibida)
			if (erroUso.equals("could not execute statement")) {
				//Mensagem de tratamento de n�o permiss�o de Editar o valor por estar sendo usado em algum cadastro
				FacesUtil.msgErro("Reserva n�o pode ser Editada, dados est� em uso.");
			}else{
				//Capiturando o erro ocorrido para ser impresso com a mensagem
				FacesUtil.msgErro("Erro ao Editar Reserva!" + ex.getMessage());
			}
		}
	}
	
	//M�todo para alterar status do recurso quando reservado na pagina de reserva de recurso
	public void alterarStatusRecurso(){
		RecursoDAO recursoDAO = new RecursoDAO();
		try {
			Recurso recursoReservadoAlterar =  recursoDAO.buscarPorCodigo(reservaCadastro.getRecurso().getNumSerieRecurso());
			recursoReservadoAlterar.getStatus().setCodStatus(7L);
			recursoDAO.editar(recursoReservadoAlterar);
						
		} catch (Exception ex) {
			FacesUtil.msgErro("Erro ao alterar Status do Recurso!" + ex.getMessage());
		}
		
	}
}
