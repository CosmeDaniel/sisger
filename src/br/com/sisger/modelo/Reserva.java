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

@Entity //@*.* Definindo a Classe como uma Entidade de Persistencia.
@Table(name = "tbl_reservas") //@*.* Definindo que a Entidade está ligada a uma Tabela

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "Reserva.listar", query = "SELECT reserva FROM Reserva reserva"),
	@NamedQuery(name = "Reserva.buscarPorCodigo", query = "SELECT reserva FROM Reserva reserva WHERE reserva.codReserva = :codReserva"),	
	@NamedQuery(name = "Reserva.buscarReservaPorUsuario", query = "SELECT reserva FROM Reserva reserva WHERE reserva.usuario = :codUsuario"),
	@NamedQuery(name = "Reserva.buscarPorCodigoRecurso", query = "SELECT reserva FROM Reserva reserva WHERE reserva.recurso = :numSerieRecurso"),
	@NamedQuery(name = "Reserva.buscarReservaPorUnidade", query = "SELECT reserva FROM Reserva reserva WHERE reserva.usuario.unidade.codUnidade = :codUnidade")
})
public class Reserva {
	// @*.* Definindo as propriedades de Colunas	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codReserva")
	private Long codReserva;
	
	@NotNull(message = "O campo Data da Reserva é Obrigatório!")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dataReserva", nullable = false)
	private Date dataReserva = new Date();
	
	@NotNull(message = "O campo Data Inicial da Reserva é Obrigatório!")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dataInicialReserva", nullable = false)
	private Date dataInicialReserva;
	
	@NotNull(message = "O campo Data Final da Reserva é Obrigatório!")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dataFinalReserva", nullable = false)
	private Date dataFinalReserva;
	
	@NotEmpty(message = "O campo Patrimônio é Obrigatório!")
	@Size(min = 1, message = "Tamanho Inválido campo Patrimônio. (Maior ou Igual. 1)")
	@Column(name = "patrimonioRecursoReserva", length = 25, nullable = false, unique = false)
	private String patrimonioRecursoReserva;

	// @*.*Definindo a Chave Estrangeira da Tabela Usuário, informando grau de
	// relaciomento e que ao carregar as Reservas os Usuarios também serão carregados.	
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Usuarios	
	@JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario", nullable = false)
	private Usuario usuario;
	
	// @*.*Definindo a Chave Estrangeira da Tabela Status, informando grau de
	// relaciomento e que ao carregar as Reservas os Status também serão carregados.		
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Status	
	@JoinColumn(name = "codStatus", referencedColumnName = "codStatus", nullable = false)
	private Status status;
	
	// @*.*Definindo a Chave Estrangeira da Tabela Status, informando grau de
	// relaciomento e que ao carregar as Reservas os Recursos também serão carregados.	
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Recursos		
	@JoinColumn(name = "RECURSOS_numSerieRecurso", referencedColumnName = "numSerieRecurso", nullable = false)
	private Recurso recurso;
		
	//Gets e Sets para acesso ao Atributos da Classe
	public synchronized Long getCodReserva() {
	
		return codReserva;
	}

	public synchronized void setCodReserva(Long codReserva) {
		this.codReserva = codReserva;
	}

	public synchronized Date getDataReserva() {
		return dataReserva;
	}

	public synchronized void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public synchronized Date getDataInicialReserva() {
		return dataInicialReserva;
	}

	public synchronized void setDataInicialReserva(Date dataInicialReserva) {
		this.dataInicialReserva = dataInicialReserva;
	}

	public synchronized Date getDataFinalReserva() {
		return dataFinalReserva;
	}

	public synchronized void setDataFinalReserva(Date dataFinalReserva) {
		this.dataFinalReserva = dataFinalReserva;
	}

	public synchronized String getPatrimonioRecursoReserva() {
		return patrimonioRecursoReserva;
	}

	public synchronized void setPatrimonioRecursoReserva(
			String patrimonioRecursoReserva) {
		this.patrimonioRecursoReserva = patrimonioRecursoReserva;
	}

	public synchronized Usuario getUsuario() {
		return usuario;
	}

	public synchronized void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public synchronized Status getStatus() {
		return status;
	}

	public synchronized void setStatus(Status status) {
		this.status = status;
	}

	public synchronized Recurso getRecurso() {
		return recurso;
	}

	public synchronized void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	//Método para Gererar e impressão correta no momento do DAOTest	
	@Override
	public String toString() {
		return "Reserva [codReserva=" + codReserva + ", dataReserva="
				+ dataReserva + ", dataInicialReserva=" + dataInicialReserva
				+ ", dataFinalReserva=" + dataFinalReserva
				+ ", patrimonioRecursoReserva=" + patrimonioRecursoReserva
				+ ", usuario=" + usuario + ", status=" + status + ", recurso="
				+ recurso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dataFinalReserva == null) ? 0 : dataFinalReserva.hashCode());
		result = prime
				* result
				+ ((dataInicialReserva == null) ? 0 : dataInicialReserva
						.hashCode());
		result = prime * result
				+ ((dataReserva == null) ? 0 : dataReserva.hashCode());
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
		Reserva other = (Reserva) obj;
		if (dataFinalReserva == null) {
			if (other.dataFinalReserva != null)
				return false;
		} else if (!dataFinalReserva.equals(other.dataFinalReserva))
			return false;
		if (dataInicialReserva == null) {
			if (other.dataInicialReserva != null)
				return false;
		} else if (!dataInicialReserva.equals(other.dataInicialReserva))
			return false;
		if (dataReserva == null) {
			if (other.dataReserva != null)
				return false;
		} else if (!dataReserva.equals(other.dataReserva))
			return false;
		return true;
	}
	
	
	
}