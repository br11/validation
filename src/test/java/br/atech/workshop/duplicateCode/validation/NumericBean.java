package br.atech.workshop.duplicateCode.validation;

/**
 * 
 * @author marcio
 * 
 */
public class NumericBean implements Cloneable {

	@Numeric("000")
	private Integer number1;

	@Numeric("#00")
	private Integer number2;

	@Numeric("##0")
	private Integer number3;

	@Numeric("#00")
	private Double number4;

	@Numeric("##0.00")
	private Double number5;

	@Numeric("##0.0#")
	private Double number6;

	public Integer getNumber1() {
		return number1;
	}

	public void setNumber1(Integer number1) {
		this.number1 = number1;
	}

	public Integer getNumber2() {
		return number2;
	}

	public void setNumber2(Integer number2) {
		this.number2 = number2;
	}

	public Integer getNumber3() {
		return number3;
	}

	public void setNumber3(Integer number3) {
		this.number3 = number3;
	}

	public Double getNumber4() {
		return number4;
	}

	public void setNumber4(Double number4) {
		this.number4 = number4;
	}

	public Double getNumber5() {
		return number5;
	}

	public void setNumber5(Double number5) {
		this.number5 = number5;
	}

	public Double getNumber6() {
		return number6;
	}

	public void setNumber6(Double number6) {
		this.number6 = number6;
	}

}
