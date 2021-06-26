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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity //@*.* Definindo a Classe como uma Entidade de Persistencia.

@Table(name = "tbl_subcategorias") //@*.* Definindo que a Entidade está ligada a uma Tabela

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "SubCategoria.listar", query = "SELECT subCategoria FROM SubCategoria subCategoria"),
	@NamedQuery(name = "SubCategoria.buscarPorCodigo", query = "SELECT subCategoria FROM SubCategoria subCategoria WHERE subCategoria.codSubCategoria = :codSubCategoria"),
	@NamedQuery(name = "SubCategoria.buscarPorNome", query = "SELECT subCategoria FROM SubCategoria subCategoria WHERE subCategoria.nomeSubCategoria = :nomeSubCategoria")

})
public class SubCategoria {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codSubCategoria")
	private Long codSubCategoria;
	
	@NotEmpty(message = "O campo Subcategoria é Obrigatório!")
	@Size(min = 3, max = 30, message = "Tamanho Inválido campo Subcategoria. (Min. 3 - Max. 30)")
	@Column(name = "nomeSubCategoria", length = 30, nullable = false, unique = true)
	private String nomeSubCategoria;

	// @*.*Definindo a Chave Estrangeira da Tabela Categorias, informando grau
	// de
	// relaciomento e que ao carregar
	// as SubCategorias as Categorias também serão carregados.
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem é a Chave Estrangeira na Tabela Categoria
	@JoinColumn(name = "codCategoria", referencedColumnName = "codCategoria", nullable = false)
	private Categoria categoria;

	//Gets e Sets para acesso ao Atributos da Classe
	public synchronized Long getCodSubCategoria() {
		return codSubCategoria;
	}

	public synchronized void setCodSubCategoria(Long codSubCategoria) {
		this.codSubCategoria = codSubCategoria;
	}

	public synchronized String getNomeSubCategoria() {
		return nomeSubCategoria;
	}

	public synchronized void setNomeSubCategoria(String nomeSubCategoria) {
		this.nomeSubCategoria = nomeSubCategoria;
	}

	public synchronized Categoria getCategoria() {
		return categoria;
	}

	public synchronized void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	//Método para Gererar e impressão correta no momento do DAOTest	
	@Override
	public String toString() {
		return "SubCategoria [codSubCategoria=" + codSubCategoria
				+ ", nomeSubCategoria=" + nomeSubCategoria + ", categoria="
				+ categoria + "]";
	}

	
	//Método que ensina a comparação de dois Objetos para Listar	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result
				+ ((codSubCategoria == null) ? 0 : codSubCategoria.hashCode());
		result = prime
				* result
				+ ((nomeSubCategoria == null) ? 0 : nomeSubCategoria.hashCode());
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
		SubCategoria other = (SubCategoria) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codSubCategoria == null) {
			if (other.codSubCategoria != null)
				return false;
		} else if (!codSubCategoria.equals(other.codSubCategoria))
			return false;
		if (nomeSubCategoria == null) {
			if (other.nomeSubCategoria != null)
				return false;
		} else if (!nomeSubCategoria.equals(other.nomeSubCategoria))
			return false;
		return true;
	}	
}
