package br.atech.workshop.validation.numeric;

/**
 * 
 * @author marcio
 * 
 */
public class NumericBean implements Cloneable {

	@Numeric("#")
	private Integer number1;

	@Numeric("##")
	private Integer number2;

	@Numeric("###")
	private Integer number3;

	@Numeric("###")
	private Double number4;

	@Numeric("###.#")
	private Double number5;

	@Numeric("###.##")
	private Double number6;

	@Numeric(value = "###.##", max = 98)
	private Double number7;

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

	/**
	 * @return the number7
	 */
	public Double getNumber7() {
		return number7;
	}

	/**
	 * @param number7
	 *            the number7 to set
	 */
	public void setNumber7(Double number7) {
		this.number7 = number7;
	}

}
