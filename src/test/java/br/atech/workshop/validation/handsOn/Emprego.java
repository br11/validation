package br.atech.workshop.validation.handsOn;

import java.math.BigDecimal;
import java.util.Date;

import br.atech.workshop.validation.numeric.Numeric;
import br.atech.workshop.validation.required.Required;
import br.atech.workshop.validation.util.IdentityUtil;

public class Emprego {

	private long id;

	private String cargo;

	@Required
	private String empresa;

	@Required
	private Date admissao;

	@Required(groups = Programas.SeguroDesemprego.class)
	private Date demissao;

	@Numeric(value = "#########.##", min = 0.01)
	private BigDecimal ultimoSalario;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the admissao
	 */
	public Date getAdmissao() {
		return admissao;
	}

	/**
	 * @param admissao
	 *            the adimissao to set
	 */
	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	/**
	 * @return the demissao
	 */
	public Date getDemissao() {
		return demissao;
	}

	/**
	 * @param demissao
	 *            the demissao to set
	 */
	public void setDemissao(Date demissao) {
		this.demissao = demissao;
	}

	/**
	 * @return the ultimoSalario
	 */
	public BigDecimal getUltimoSalario() {
		return ultimoSalario;
	}

	/**
	 * @param ultimoSalario
	 *            the ultimoSalario to set
	 */
	public void setUltimoSalario(BigDecimal ultimoSalario) {
		this.ultimoSalario = ultimoSalario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new IdentityUtil().hashCode(this, "empresa", "adimissao");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return new IdentityUtil().equals(this, obj, "empresa", "adimissao");
	}
}