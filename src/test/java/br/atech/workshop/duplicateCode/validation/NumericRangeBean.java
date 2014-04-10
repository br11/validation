package br.atech.workshop.duplicateCode.validation;

/**
 * 
 * @author marcio
 * 
 */
public class NumericRangeBean implements Cloneable {

	@NumericRange({ NumericRange.PreDef.NotNull, NumericRange.PreDef.Big })
	private Integer id;

	@NumericRange({ NumericRange.PreDef.Age })
	private Integer age;

	@NumericRange({ NumericRange.PreDef.Salary })
	private Float salary;

	@NumericRange({ NumericRange.PreDef.Big })
	private Double overtime;

	public NumericRangeBean() {
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
