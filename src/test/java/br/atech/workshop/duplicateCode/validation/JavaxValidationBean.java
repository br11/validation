package br.atech.workshop.duplicateCode.validation;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author marcio
 * 
 */
public class JavaxValidationBean implements Cloneable {

	@javax.validation.constraints.DecimalMax(value = "123456789012345678901234567890", inclusive = true)
	private BigDecimal number1;

	@javax.validation.constraints.DecimalMin(value = "-123456789012345678901234567890", inclusive = true)
	private BigDecimal number2;

	@javax.validation.constraints.Digits(integer = 5, fraction = 2)
	private Double number3;

	@javax.validation.constraints.Digits(integer = 5, fraction = 2)
	private BigDecimal number4;

	@javax.validation.constraints.NotNull
	private String text1 = "abc";

	@javax.validation.constraints.Pattern(regexp = ".*[^ ]+.*")
	private String text2;

	@javax.validation.constraints.Past
	private Date date1;

	@javax.validation.constraints.Future
	private Date date2;

	public BigDecimal getNumber1() {
		return number1;
	}

	public void setNumber1(BigDecimal number1) {
		this.number1 = number1;
	}

	public BigDecimal getNumber2() {
		return number2;
	}

	public void setNumber2(BigDecimal number2) {
		this.number2 = number2;
	}

	public Double getNumber3() {
		return number3;
	}

	public void setNumber3(Double number3) {
		this.number3 = number3;
	}

	public BigDecimal getNumber4() {
		return number4;
	}

	public void setNumber4(BigDecimal number4) {
		this.number4 = number4;
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

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

}
