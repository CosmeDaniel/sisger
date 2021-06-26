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

//@*.* Definindo a Classe como uma Entidade de Persistencia.
@Entity
//@*.* Definindo que a Entidade está ligada a uma Tabela
@Table(name = "tbl_categorias")

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "Categoria.listar", query = "SELECT categoria FROM Categoria categoria"),
	@NamedQuery(name = "Categoria.buscarPorCodigo", query = "SELECT categoria FROM Categoria categoria WHERE categoria.codCategoria = :codCategoria")
})
public class Categoria {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codCategoria")
	private Long codCategoria;
	
	@NotEmpty(message = "O campo Nome Categoria é Obrigatório!")
	@Size( min = 3, max = 20, message = "Tamanho Inválido para o campo Categoria (Min. 3 - Max. 20)")
	@Column(name = "nomeCategoria", length = 20, nullable = false, unique = true)
	private String nomeCategoria;

	//Gets e Sets para acesso ao Atributos da Classe
	public Long getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(Long codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	//Método para Gererar e impressão correta no momento do DAOTest	
	@Override
	public String toString() {
		return "Categoria [codCategoria=" + codCategoria + ", nomeCategoria="
				+ nomeCategoria + "]";
	}


	//Método que ensina a comparação de dois Objetos para Listar
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codCategoria == null) ? 0 : codCategoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (codCategoria == null) {
			if (other.codCategoria != null)
				return false;
		} else if (!codCategoria.equals(other.codCategoria))
			return false;
		return true;
	}
	
}
