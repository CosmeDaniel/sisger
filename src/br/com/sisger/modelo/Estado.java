package br.com.sisger.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// @*.* Definindo a Classe como uma Entidade de Persistencia.
@Entity
// @*.* Definindo que a Entidade está ligada a uma Tabela
@Table(name = "tbl_estados")

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "Estado.listar", query = "SELECT estado FROM Estado estado"),
	@NamedQuery(name = "Estado.buscarPorCodigo", query = "SELECT estado FROM Estado estado WHERE estado.codEstado = :codEstado")
})
	public class Estado {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codEstado")
	private Long codEstado;
	
	@NotNull(message = "O campo Estado é Obrigatório!")
	@Size(min = 2, max = 2, message = "Tamanho Inválido campo Estado. (Min. 2 - Max. 2)")
	@Column(name = "ufEstado", length = 2, nullable = false, unique = true)
	private String ufEstado;

	//Gets e Sets para acesso ao Atributos da Classe
	public Long getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(Long codEstado) {
		this.codEstado = codEstado;
	}

	public String getUfEstado() {
		return ufEstado;
	}

	public void setUfEstado(String ufEstado) {
		this.ufEstado = ufEstado;
	}

	//Método para Gererar e impressão correta no momento do DAOTest	
	@Override
	public String toString() {
		return "Estado [codEstado=" + codEstado + ", ufEstado=" + ufEstado
				+ "]";
	}


	//Método que ensina a comparação de dois Objetos para Listar
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codEstado == null) ? 0 : codEstado.hashCode());
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
		Estado other = (Estado) obj;
		if (codEstado == null) {
			if (other.codEstado != null)
				return false;
		} else if (!codEstado.equals(other.codEstado))
			return false;
		return true;
	}
	
	
}
