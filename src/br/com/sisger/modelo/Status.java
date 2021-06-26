package br.com.sisger.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity //@*.* Definindo a Classe como uma Entidade de Persistencia.
@Table (name = "tbl_status") //@*.* Definindo que a Entidade está ligada a uma Tabela
@NamedQueries({ //Criado HQL para executar consultas a dados nos bancos de dados
	@NamedQuery(name = "Status.listar", query = "SELECT status FROM Status status"),
	@NamedQuery(name = "Status.buscarPorCodigo", query = "SELECT status FROM Status status WHERE status.codStatus = :codStatus"),
	@NamedQuery(name = "Status.buscarPorInterv", query = "SELECT status FROM Status status WHERE status.codStatus >= :codStatus AND status.codStatus <= :codStatusM")
})
public class Status {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codStatus")
	private Long codStatus;
	
	@NotEmpty(message = "O campo Status é Obrigatório!")
	@Size(min = 3, max = 20, message = "Tamanho Inválido campo Status (Min. 3 - Max. 20)")
	@Column(name = "nomeStatus", length = 20, nullable = false, unique = true)
	private String nomeStatus;

	public Long getCodStatus() {
		return codStatus;
	}

	//Gets e Sets para acesso ao Atributos da Classe
	public void setCodStatus(Long codStatus) {
		this.codStatus = codStatus;
	}

	public String getNomeStatus() {
		return nomeStatus;
	}

	public void setNomeStatus(String nomeStatus) {
		this.nomeStatus = nomeStatus;
	}

	//Método para Gererar e impressão correta no momento do DAOTest	
	@Override
	public String toString() {
		return "Status [codStatus=" + codStatus + ", nomeStatus=" + nomeStatus
				+ "]";
	}

	//Método que ensina a comparação de dois Objetos para Listar
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codStatus == null) ? 0 : codStatus.hashCode());
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
		Status other = (Status) obj;
		if (codStatus == null) {
			if (other.codStatus != null)
				return false;
		} else if (!codStatus.equals(other.codStatus))
			return false;
		return true;
	}
	
	
	
}
