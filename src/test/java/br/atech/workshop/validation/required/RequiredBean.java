package br.atech.workshop.validation.required;

import java.util.Date;

import br.atech.workshop.validation.required.Required;

/**
 * 
 * @author marcio
 * 
 */
public class RequiredBean implements Cloneable {

	@Required
	private Integer id;

	@Required
	private String name;

	@Required
	private Date birthDate;

	public RequiredBean() {
		super();
	}

	public RequiredBean(Integer id, String name, Date birthDate) {
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
}
