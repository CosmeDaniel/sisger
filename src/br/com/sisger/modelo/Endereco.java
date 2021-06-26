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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity //@*.* Definindo a Classe como uma Entidade de Persistencia.
@Table(name = "tbl_enderecos") //@*.* Definindo que a Entidade está ligada a uma Tabela
//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "Endereco.listar", query = "SELECT endereco FROM Endereco endereco"),
	@NamedQuery(name = "Endereco.buscarPorCodigo", query = "SELECT endereco FROM Endereco endereco WHERE endereco.codEndereco = :codEndereco"),
})

public class Endereco {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codEndereco")
	private Long codEndereco;

	@NotEmpty(message = "O campo Rua é Obrigatório!")
	@Size(min = 5, max = 50, message = "Tamanho Inválido campo Rua (Min. 5 - Max. 50)")
	@Column(name = "ruaEndereco", length = 50, nullable = false)
	private String ruaEndereco;
	
	@Min(value = 1, message = "Tamanho Inválido campo Número (Maior ou Igual 1")
	@Column(name = "numeroEndereco", nullable = false)
	private Integer numeroEndereco;

	@Column(name = "complementoEndereco", length = 20)
	private String complementoEndereco;
 
	@NotEmpty(message = "O campo Bairro é Obrigatório!")
	@Size(min = 5, max = 45, message = "Tamanho Inválido campo Bairro. (Min. 5 - Max. 45)")
	@Column(name = "bairroEndereco", length = 45, nullable = false)
	private String bairroEndereco;
	
	@NotEmpty(message = "O campo CEP é Obrigatório!")
	@Column(name = "cepEndereco", length = 14, nullable = false)
	private String cepEndereco;

	// @*.*Definindo a Chave Estrangeira da Tabela Cidade, informando grau de
	// relaciomento e que ao carregar
	// as Endereços as Cidades também serão carregados.	
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Estado	
	@JoinColumn(name = "codCidade", referencedColumnName = "codCidade", nullable = false)
	@NotNull(message = "O campo Cidade é Obrigatório!")
	private Cidade cidade;

	//Gets e Sets para acesso ao Atributos da Classe
	public synchronized Long getCodEndereco() {
		return codEndereco;
	}

	public synchronized void setCodEndereco(Long codEndereco) {
		this.codEndereco = codEndereco;
	}

	public synchronized String getRuaEndereco() {
		return ruaEndereco;
	}

	public synchronized void setRuaEndereco(String ruaEndereco) {
		this.ruaEndereco = ruaEndereco;
	}

	public synchronized Integer getNumeroEndereco() {
		return numeroEndereco;
	}

	public synchronized void setNumeroEndereco(Integer numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public synchronized String getComplementoEndereco() {
		return complementoEndereco;
	}

	public synchronized void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public synchronized String getBairroEndereco() {
		return bairroEndereco;
	}

	public synchronized void setBairroEndereco(String bairroEndereco) {
		this.bairroEndereco = bairroEndereco;
	}

	public synchronized String getCepEndereco() {
		return cepEndereco;
	}

	public synchronized void setCepEndereco(String cepEndereco) {
		this.cepEndereco = cepEndereco;
	}

	public synchronized Cidade getCidade() {
		if (cidade == null) {
			cidade = new Cidade();
		}
		return cidade;
	}

	public synchronized void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	//Método para Gererar e impressão correta no momento do DAOTest
	@Override
	public String toString() {
		return "Endereco [codEndereco=" + codEndereco + ", ruaEndereco="
				+ ruaEndereco + ", numeroEndereco=" + numeroEndereco
				+ ", complementoEndereco=" + complementoEndereco
				+ ", bairroEndereco=" + bairroEndereco + ", cepEndereco="
				+ cepEndereco + ", cidade=" + cidade + "]";
	}

	//Método que ensina a comparação de dois Objetos para Listar
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codEndereco == null) ? 0 : codEndereco.hashCode());
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
		Endereco other = (Endereco) obj;
		if (codEndereco == null) {
			if (other.codEndereco != null)
				return false;
		} else if (!codEndereco.equals(other.codEndereco))
			return false;
		return true;
	}
	
	
}
