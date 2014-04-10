package br.atech.workshop.duplicateCode.validation;

/**
 * 
 * @author marcio
 * 
 */
public class NumericDomainBean implements Cloneable {

	@NumericDomain({ NumericDomain.PreDef.NotNull, NumericDomain.PreDef.Big })
	private Integer id;

	@NumericDomain({ NumericDomain.PreDef.Age })
	private Integer age;

	@NumericDomain({ NumericDomain.PreDef.Salary })
	private Float salary;

	@NumericDomain({ NumericDomain.PreDef.Big })
	private Double overtime;

	public NumericDomainBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Double getOvertime() {
		return overtime;
	}

	public void setOvertime(Double overtime) {
		this.overtime = overtime;
	}

}
