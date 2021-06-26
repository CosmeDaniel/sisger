package br.com.sisger.modelo;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity //@*.* Definindo a Classe como uma Entidade de Persistencia.
@Table(name = "tbl_cidades") // @*.* Definindo que a Entidade está ligada a uma Tabela

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "Cidade.listar", query = "SELECT cidade FROM Cidade cidade"),
	@NamedQuery(name = "Cidade.buscarPorCodigo", query = "SELECT cidade FROM Cidade cidade WHERE cidade.codCidade = :codCidade"),
	@NamedQuery(name = "Cidade.buscarPorCodigoEstado", query = "SELECT cidade FROM Cidade cidade WHERE cidade.estado = :codEstado")
})
public class Cidade {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codCidade")
	private Long codCidade;
	
	@NotNull(message = "O campo Nome Cidade é Obrigatório!")
	@Size(min = 5, max  = 35, message = "O campo Nome Cidade é Obrigatório!")
	@Column(name = "nomeCidade", length = 35, nullable = false, unique = true)
	private String nomeCidade;

	// @*.*Definindo a Chave Estrangeira da Tabela Estado, informando grau de
	// relaciomento e que ao carregar
	// as Cidades os Estados também serão carregados.
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Estado
	@JoinColumn(name = "codEstado", referencedColumnName = "codEstado", nullable = false)
	@NotNull(message = "O campo Estado é Obrigatório!")
	private Estado estado;

	//Gets e Sets para acesso ao Atributos da Classe
	public synchronized Long getCodCidade() {
		return codCidade;
	}

	public synchronized void setCodCidade(Long codCidade) {
		this.codCidade = codCidade;
	}

	public synchronized String getNomeCidade() {
		return nomeCidade;
	}

	public synchronized void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public synchronized Estado getEstado() {
		return estado;
	}

	public synchronized void setEstado(Estado estado) {
		this.estado = estado;
	}

	//Método para Gererar e impressão correta no momento do DAOTest	
	@Override
	public String toString() {
		return "Cidade [codCidade=" + codCidade + ", nomeCidade=" + nomeCidade
				+ ", estado=" + estado + "]";
	}


	//Método que ensina a comparação de dois Objetos para Listar	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codCidade == null) ? 0 : codCidade.hashCode());
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
		Cidade other = (Cidade) obj;
		if (codCidade == null) {
			if (other.codCidade != null)
				return false;
		} else if (!codCidade.equals(other.codCidade))
			return false;
		return true;
	}
	
	
}
