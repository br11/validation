package br.atech.workshop.duplicateCode.validation;

import java.util.Date;

/**
 * 
 * @author marcio
 * 
 */
public class DomainBean implements Cloneable {

	@Numeric(Numeric.PreDef.NotNull)
	private Integer id;

	@Numeric({ Numeric.PreDef.NotNull, Numeric.PreDef.Name })
	private String name;

	private Date birthDate;

	@Numeric({ Numeric.PreDef.Alfa })
	private String text1;

	@Numeric({ Numeric.PreDef.Alfanum })
	private String text2;

	@Numeric({ Numeric.PreDef.Any })
	private String text3;

	private String text4;

	@Numeric({ Numeric.PreDef.Text })
	private String text5;

	private String text6;

	public DomainBean() {
		super();
	}

	public DomainBean(Integer id, String name, Date birthDate) {
		this();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getText3() {
		return text3;
	}

	public void setText3(String text3) {
		this.text3 = text3;
	}

	public String getText4() {
		return text4;
	}

	public void setText4(String text4) {
		this.text4 = text4;
	}

	public String getText5() {
		return text5;
	}

	public void setText5(String text5) {
		this.text5 = text5;
	}

	public String getText6() {
		return text6;
	}

	public void setText6(String text6) {
		this.text6 = text6;
	}
}
