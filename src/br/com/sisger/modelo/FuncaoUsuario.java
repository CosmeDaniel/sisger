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
@Table(name = "tbl_funcaoUsuarios") //@*.* Definindo que a Entidade está ligada a uma Tabela 

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "FuncaoUsuario.listar", query = "SELECT funcaoUsuario FROM FuncaoUsuario funcaoUsuario"),
	@NamedQuery(name = "FuncaoUsuario.buscarPorCodigo", query = "SELECT funcaoUsuario FROM FuncaoUsuario funcaoUsuario WHERE funcaoUsuario.codFuncaoUsuario = :codFuncaoUsuario"),
	@NamedQuery(name = "FuncaoUsuario.buscarPorCodMai", query = "SELECT funcaoUsuario FROM FuncaoUsuario funcaoUsuario WHERE funcaoUsuario.codFuncaoUsuario >= :codFuncaoUsuario")
	
})
public class FuncaoUsuario {
	// @*.* Definindo as propriedades de Colunas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codFuncaoUsuario")
	private Long codFuncaoUsuario;
	
	@NotEmpty(message = "O campo Função é Obrigatório!")
	@Size(min = 3, max = 30, message = "Tamanho Inválido campo Função. (Min. 3 - Max. 30)")
	@Column(name = "descricaoFuncaoUsuario", length = 30, nullable = false, unique = true)
	private String descricaoFuncaoUsuario;

	//Gets e Sets para acesso ao Atributos da Classe
	public synchronized Long getcodFuncaoUsuario() {
		return codFuncaoUsuario;
	}

	public synchronized void setcodFuncaoUsuario(Long codFuncaoUsuario) {
		this.codFuncaoUsuario = codFuncaoUsuario;
	}

	public synchronized String getDescricaoFuncaoUsuario() {
		return descricaoFuncaoUsuario;
	}

	public synchronized void setDescricaoFuncaoUsuario(
			String descricaoFuncaoUsuario) {
		this.descricaoFuncaoUsuario = descricaoFuncaoUsuario;
	}

	//Método para Gererar e impressão correta no momento do DAOTest	
	@Override
	public String toString() {
		return "FuncaoUsuario [codFuncaoUsuario=" + codFuncaoUsuario
				+ ", descricaoFuncaoUsuario=" + descricaoFuncaoUsuario + "]";
	}


	//Método que ensina a comparação de dois Objetos para Listar
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codFuncaoUsuario == null) ? 0 : codFuncaoUsuario.hashCode());
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
		FuncaoUsuario other = (FuncaoUsuario) obj;
		if (codFuncaoUsuario == null) {
			if (other.codFuncaoUsuario != null)
				return false;
		} else if (!codFuncaoUsuario.equals(other.codFuncaoUsuario))
			return false;
		return true;
	}
	
	
	
}
