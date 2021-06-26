package br.com.sisger.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity //@*.* Definindo a Classe como uma Entidade de Persistencia.
@Table(name = "tbl_unidades") // @*.* Definindo que a Entidade está ligada a uma Tabela

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "Unidade.listar", query = "SELECT unidade FROM Unidade unidade"),
	@NamedQuery(name = "Unidade.buscarPorCodigo", query = "SELECT unidade FROM Unidade unidade WHERE unidade.codUnidade = :codUnidade"),
	@NamedQuery(name = "Unidade.CampiPorCodigo", query = "SELECT unidade FROM Unidade unidade WHERE unidade.codUnidade = :codCampiUnidade"
					 + " or unidade.unidade =:codCampiUnidade")
})

public class Unidade {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codUnidade")
	private Long codUnidade;

	@NotEmpty(message = "O campo CNPJ é Obrigatório!")
	@CNPJ(message = "CNPJ Inválido!")
	@Column(name = "cnpjUnidade", length = 45, nullable = false, unique = true)
	private String cnpjUnidade;
	
	@NotEmpty(message = "O campo Inscr. Estadual é Obrigatório!")
	@Size(min = 1, message = "Tamanho Inválido campo Inscr. Estadual (Maior ou Igual a 1)")
	@Column(name = "inscricaoEstadualUnidade", length = 45, nullable = false, unique = true)
	private String inscricaoEstadualUnidade;
	
	@NotEmpty(message = "O campo Razão Social é Obrigatório!")
	@Size(min = 5, max = 50, message = "Tamanho Inválido campo Razão Social. (Min. 5 - Max. 50)")
	@Column(name = "razaoSocialUnidade", length = 50, nullable = false, unique = true)
	private String razaoSocialUnidade;

	@Column(name = "contatoUnidade", length = 45)
	private String contatoUnidade;

	// @*.*Definindo a Chave Estrangeira da Tabela Endereço, informando grau de
	// relaciomento e que ao carregar as Unidades os Endereços também serão carregados.
	@OneToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Endereço
	@JoinColumn(name = "codEndereco", referencedColumnName = "codEndereco", nullable = false, unique = true)
	@NotNull(message = "O campo Endereço é Obrigatório!")
	private Endereco endereco;
	
	// @*.*Definindo a Chave Estrangeira da Tabela Unidade, informando grau de
	// relaciomento e que ao carregar as Unidades os Campi também serão carregados.
	@OneToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Unidade
	@JoinColumn(name = "codCampiUnidade", referencedColumnName = "codUnidade")
	private Unidade unidade;
	
	//Gets e Sets para acesso ao Atributos da Classe
	public synchronized Long getCodUnidade() {
		return codUnidade;
	}

	public synchronized void setCodUnidade(Long codUnidade) {
		this.codUnidade = codUnidade;
	}

	public synchronized String getCnpjUnidade() {
		return cnpjUnidade;
	}

	public synchronized void setCnpjUnidade(String cnpjUnidade) {
		this.cnpjUnidade = cnpjUnidade;
	}

	public synchronized String getInscricaoEstadualUnidade() {
		return inscricaoEstadualUnidade;
	}

	public synchronized void setInscricaoEstadualUnidade(
			String inscricaoEstadualUnidade) {
		this.inscricaoEstadualUnidade = inscricaoEstadualUnidade;
	}

	public synchronized String getRazaoSocialUnidade() {
		return razaoSocialUnidade;
	}

	public synchronized void setRazaoSocialUnidade(String razaoSocialUnidade) {
		this.razaoSocialUnidade = razaoSocialUnidade;
	}

	public synchronized String getContatoUnidade() {
		return contatoUnidade;
	}

	public synchronized void setContatoUnidade(String contatoUnidade) {
		this.contatoUnidade = contatoUnidade;
	}

	public synchronized Endereco getEndereco() {
		return endereco;
	}

	public synchronized void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Unidade getUnidade() {
		return unidade;
	}
	
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@Override
	public String toString() {
		return "Unidade [codUnidade=" + codUnidade + ", cnpjUnidade="
				+ cnpjUnidade + ", inscricaoEstadualUnidade="
				+ inscricaoEstadualUnidade + ", razaoSocialUnidade="
				+ razaoSocialUnidade + ", contatoUnidade=" + contatoUnidade
				+ ", endereco=" + endereco + ", unidade=" + unidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codUnidade == null) ? 0 : codUnidade.hashCode());
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
		Unidade other = (Unidade) obj;
		if (codUnidade == null) {
			if (other.codUnidade != null)
				return false;
		} else if (!codUnidade.equals(other.codUnidade))
			return false;
		return true;
	}
	
}
