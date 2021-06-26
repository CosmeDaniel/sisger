package br.com.sisger.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity //@*.* Definindo a Classe como uma Entidade de Persistencia.
@Table(name = "tbl_recursos") //@*.* Definindo que a Entidade est� ligada a uma Tabela

//Criado HQL para executar consultas a dados nos bancos de dados
@NamedQueries({
	@NamedQuery(name = "Recurso.listar", query = "SELECT recurso FROM Recurso recurso"),
	@NamedQuery(name = "Recurso.buscarPorCodigo", query = "SELECT recurso FROM Recurso recurso WHERE recurso.numSerieRecurso = :numSerieRecurso"),
	@NamedQuery(name = "Recurso.buscarPorUnidade", query = "SELECT recurso FROM Recurso recurso WHERE recurso.unidade = :codUnidade"),
	@NamedQuery(name = "Recurso.listarLiberados", query = "SELECT recurso FROM Recurso recurso WHERE recurso.status = :codStatus"),
	@NamedQuery(name = "Recurso.listarLiberadosUnidade", query = "SELECT recurso FROM Recurso recurso WHERE recurso.status = :codStatus AND recurso.unidade = :codUnidade")
})
public class Recurso {
	// @*.* Definindo as propriedades de Colunas	
	@Id
	@Column(name = "numSerieRecurso", length = 45, unique = true)
	@NotEmpty(message = "O campo N�. S�rie � Obrigat�rio!")
	private String numSerieRecurso;
	
	@NotEmpty(message = "O campo Patrim�nio � Obrigat�rio!")
	@Column(name = "patrimonioRecurso", length = 25, nullable = false, unique = true)
	private String patrimonioRecurso;
	
	@NotEmpty(message = "O campo Recurso � Obrigat�rio!")
	@Size(min = 3, max = 30, message = "Tamanho Inv�lido campo Recurso. (Min. 3 - Max. 30)")
	@Column(name = "identificacaoRecurso", length = 30, nullable = false)
	private String identificacaoRecurso;
	
	@NotEmpty(message = "O campo Modelo � Obrigat�rio!")
	@Size(min = 3, max = 20, message = "Tamanho Inv�lido campo Modelo. (Min. 3 - Max. 20)")
	@Column(name = "modeloRecurso", length = 20, nullable = false)
	private String modeloRecurso;

	// @*.*Definindo a Chave Estrangeira da Tabela Unidade, informando grau de
	// relaciomento e que ao carregar os Recurso as Unidades tamb�m ser�o carregados.
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem � a Chave Estrangeira na Tabela Unidade	
	@JoinColumn(name = "codUnidade", referencedColumnName = "codUnidade", nullable = false)
	private Unidade unidade;

	// @*.*Definindo a Chave Estrangeira da Tabela Categoria, informando grau de
	// relaciomento e que ao carregar os Recurso as Categorias tamb�m ser�o carregados.	
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem � a Chave Estrangeira na Tabela Categoria	
	@JoinColumn(name = "codSubCategoria", referencedColumnName = "codSubCategoria", nullable = false)
	@NotNull(message = "O Campo Subcategoria � Obrigat�rio!")
	private SubCategoria subCategoria;
	
	// @*.*Definindo a Chave Estrangeira da Tabela Status, informando grau de
	// relaciomento e que ao carregar os Recurso os Status tamb�m ser�o carregados.	
	@ManyToOne(fetch = FetchType.EAGER)
	// @*.* Definindo quem � a Chave Estrangeira na Tabela Status	
	@JoinColumn(name = "codStatus", referencedColumnName = "codStatus", nullable = false)
	@NotNull(message = "O Campo Status � Obrigat�rio!")
	private Status status;

	//Gets e Sets para acesso ao Atributos da Classe
	public synchronized String getNumSerieRecurso() {
		return numSerieRecurso;
	}

	public synchronized void setNumSerieRecurso(String numSerieRecurso) {
		this.numSerieRecurso = numSerieRecurso;
	}

	public synchronized String getPatrimonioRecurso() {
		return patrimonioRecurso;
	}

	public synchronized void setPatrimonioRecurso(String patrimonioRecurso) {
		this.patrimonioRecurso = patrimonioRecurso;
	}

	public synchronized String getIdentificacaoRecurso() {
		return identificacaoRecurso;
	}

	public synchronized void setIdentificacaoRecurso(String identificacaoRecurso) {
		this.identificacaoRecurso = identificacaoRecurso;
	}

	public synchronized String getModeloRecurso() {
		return modeloRecurso;
	}

	public synchronized void setModeloRecurso(String modeloRecurso) {
		this.modeloRecurso = modeloRecurso;
	}

	public synchronized Unidade getUnidade() {
		return unidade;
	}

	public synchronized void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public synchronized SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public synchronized void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public synchronized Status getStatus() {
		return status;
	}

	public synchronized void setStatus(Status status) {
		this.status = status;
	}

	//M�todo para Gererar e impress�o correta no momento do DAOTest	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recurso other = (Recurso) obj;
		if (numSerieRecurso == null) {
			if (other.numSerieRecurso != null)
				return false;
		} else if (!numSerieRecurso.equals(other.numSerieRecurso))
			return false;
		return true;
	}
	

	//M�todo que ensina a compara��o de dois Objetos para Listar	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numSerieRecurso == null) ? 0 : numSerieRecurso.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Recurso [numSerieRecurso=" + numSerieRecurso
				+ ", patrimonioRecurso=" + patrimonioRecurso
				+ ", identificacaoRecurso=" + identificacaoRecurso
				+ ", modeloRecurso=" + modeloRecurso + ", unidade=" + unidade
				+ ", subCategoria=" + subCategoria + ", status=" + status
				+ ", getNumSerieRecurso()=" + getNumSerieRecurso()
				+ ", getPatrimonioRecurso()=" + getPatrimonioRecurso()
				+ ", getIdentificacaoRecurso()=" + getIdentificacaoRecurso()
				+ ", getModeloRecurso()=" + getModeloRecurso()
				+ ", getUnidade()=" + getUnidade() + ", getSubCategoria()="
				+ getSubCategoria() + ", getStatus()=" + getStatus()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
}
