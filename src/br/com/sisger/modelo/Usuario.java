package br.com.sisger.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity //@*.* Definindo a Classe como uma Entidade de Persistencia.
@Table(name = "tbl_usuarios") //@*.* Definindo que a Entidade está ligada a uma Tabela

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "Usuario.listar", query = "SELECT usuario FROM Usuario usuario"),
	@NamedQuery(name = "Usuario.buscarPorCodigo", query = "SELECT usuario FROM Usuario usuario WHERE usuario.codUsuario = :codUsuario"),
	@NamedQuery(name = "Usuario.autenticar", query = "SELECT usuario FROM Usuario usuario WHERE usuario.loginUsuario = :loginUsuario AND usuario.senhaUsuario = :senhaUsuario"),
	@NamedQuery(name = "Usuario.buscarPorUnidade", query = "SELECT usuario FROM Usuario usuario WHERE usuario.unidade = :codUnidade"),
	@NamedQuery(name = "Usuario.buscarPorEmail", query = "SELECT usuario FROM Usuario usuario WHERE usuario.emailUsuario = :emailUsuario")
	
})
public class Usuario {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codUsuario")
	private Long codUsuario;

	@NotEmpty(message = "O campo CPF é Obrigatório!")
	@CPF(message = "CPF Inválido.")
	@Column(name = "cpfUsuario", length = 15, nullable = false, unique = true)
	private String cpfUsuario;

	@NotNull(message = "O campo Data de Nascimento é Obrigatório!")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dataNascimentoUsuario", nullable = false)
	private Date dataNascimentoUsuario;

	@NotEmpty(message = "O campo Nome é Obrigatório!")
	@Size(min = 3, max = 50, message = "Tamanho Inválido campo Nome. (Min. 3 - Max. 50)")
	@Column(name = "nomeUsuario", length = 50, nullable = false)
	private String nomeUsuario;

	@NotEmpty(message = "O campo Telefone é Obrigatório!")
	@Column(name = "telefoneUsuario", length = 15, nullable = false)
	private String telefoneUsuario;

	@NotEmpty(message = "O campo E-mail é Obrigatório!")
	@Column(name = "emailUsuario", length = 45, unique = true, nullable = false)
	private String emailUsuario;

	@NotEmpty(message = "O campo Login é Obrigatório!")
	@Size(min = 5, max = 30, message = "Tamanho Inválido campo Login. (Min. 5 - Max. 30)")
	@Column(name = "loginUsuario", length = 30, nullable = false, unique = true)
	private String loginUsuario;

	@Column(name = "senhaUsuario")
	private String senhaUsuario;

	// @*.*Definindo a Chave Estrangeira da Tabela Status, informando grau de
	// relaciomento e que ao carregar os Usuários os Status também serão carregados.	
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Status	
	@JoinColumn(name = "STATUS_codStatus", referencedColumnName = "codStatus", nullable = false)
	@NotNull(message = "O campo Status é Obrigatório!")
	private Status status;

	// @*.*Definindo a Chave Estrangeira da Tabela Unidade, informando grau de
	// relaciomento e que ao carregar os Usuários as Unidades também serão carregados.	
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Unidades	
	@JoinColumn(name = "UNIDADES_codUnidade", referencedColumnName = "codUnidade", nullable = false)
	@NotNull(message = "O campo Unidade é Obrigatório!")
	private Unidade unidade;

	// @*.*Definindo a Chave Estrangeira da Tabela Função do Usuário, informando grau de
	// relaciomento e que ao carregar os Usuários as Função do Usuário também serão carregados.	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FUNCAOUSUARIO_codFuncaoUsuario", referencedColumnName = "codFuncaoUsuario", nullable = false)
	@NotNull(message = "O campo Função é Obrigatório!")
	private FuncaoUsuario funcaoUsuario;

	// Gets e Sets para acesso ao Atributos da Classe
	public synchronized Long getCodUsuario() {
		return codUsuario;
	}

	public synchronized void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
	}

	public synchronized String getCpfUsuario() {
		return cpfUsuario;
	}

	public synchronized void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public synchronized Date getDataNascimentoUsuario() {
		return dataNascimentoUsuario;
	}

	public synchronized void setDataNascimentoUsuario(Date dataNascimentoUsuario) {
		this.dataNascimentoUsuario = dataNascimentoUsuario;
	}

	public synchronized String getNomeUsuario() {
		return nomeUsuario;
	}

	public synchronized void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public synchronized String getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public synchronized void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	public synchronized String getEmailUsuario() {
		return emailUsuario;
	}

	public synchronized void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public synchronized String getLoginUsuario() {
		return loginUsuario;
	}

	public synchronized void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public synchronized String getSenhaUsuario() {
		return senhaUsuario;
	}

	public synchronized void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public synchronized Status getStatus() {
		return status;
	}

	public synchronized void setStatus(Status status) {
		this.status = status;
	}

	public synchronized Unidade getUnidade() {
		return unidade;
	}

	public synchronized void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public synchronized FuncaoUsuario getFuncaoUsuario() {
		return funcaoUsuario;
	}

	public synchronized void setFuncaoUsuario(FuncaoUsuario funcaoUsuario) {
		this.funcaoUsuario = funcaoUsuario;
	}

	//Método para Gererar e impressão correta no momento do DAOTest
	@Override
	public String toString() {
		return "Usuario [codUsuario=" + codUsuario + ", cpfUsuario="
				+ cpfUsuario + ", dataNascimentoUsuario="
				+ dataNascimentoUsuario + ", nomeUsuario=" + nomeUsuario
				+ ", telefoneUsuario=" + telefoneUsuario + ", emailUsuario="
				+ emailUsuario + ", loginUsuario=" + loginUsuario
				+ ", senhaUsuario=" + senhaUsuario + ", status=" + status
				+ ", unidade=" + unidade + ", funcaoUsuario=" + funcaoUsuario
				+ "]";
	}

	//Método que ensina a comparação de dois Objetos para Listar
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codUsuario == null) ? 0 : codUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codUsuario == null) {
			if (other.codUsuario != null)
				return false;
		} else if (!codUsuario.equals(other.codUsuario))
			return false;
		return true;
	}
	
	
	
}
