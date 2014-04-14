package br.atech.workshop.validation.date;

import java.util.Date;

import br.atech.workshop.validation.date.DateRange.Ranges;

/**
 * 
 * @author marcio
 * 
 */
public class DateRangeBean implements Cloneable {

	@DateRange(Ranges.Past)
	private Date date1;
	@DateRange(Ranges.Any)
	private Date date2;
	@DateRange(Ranges.Future)
	private Date date3;

	@DateRange(Ranges.Yesterday)
	private Date date4;
	@DateRange(Ranges.Today)
	private Date date5;
	@DateRange(Ranges.Tomorrow)
	private Date date6;

	@DateRange(Ranges.LastWeek)
	private Date date7;
	@DateRange(Ranges.ThisWeek)
	private Date date8;
	@DateRange(Ranges.NextWeek)
	private Date date9;

	@DateRange(min = Ranges.Today, max = Ranges.ThisYear)
	private Date date10;

	@DateRange(value = Ranges.Now, minGap = -45, maxGap = 120 * 60)
	private Date date11;

	@DateRange(value = Ranges.FPL)
	private Date date12;

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public Date getDate4() {
		return date4;
	}

	public void setDate4(Date date4) {
		this.date4 = date4;
	}

	public Date getDate5() {
		return date5;
	}

	public void setDate5(Date date5) {
		this.date5 = date5;
	}

	public Date getDate6() {
		return date6;
	}

	public void setDate6(Date date6) {
		this.date6 = date6;
	}

	public Date getDate7() {
		return date7;
	}

	public void setDate7(Date date7) {
		this.date7 = date7;
	}

	public Date getDate8() {
		return date8;
	}

	public void setDate8(Date date8) {
		this.date8 = date8;
	}

	public Date getDate9() {
		return date9;
	}

	public void setDate9(Date date9) {
		this.date9 = date9;
	}

	public Date getDate1() {
		return date1;
	}

	/**
	 * @return the date10
	 */
	public Date getDate10() {
		return date10;
	}

	/**
	 * @param date10
	 *            the date10 to set
	 */
	public void setDate10(Date date10) {
		this.date10 = date10;
	}

	/**
	 * @return the date11
	 */
	public Date getDate11() {
		return date11;
	}

	/**
	 * @param date11
	 *            the date11 to set
	 */
	public void setDate11(Date date11) {
		this.date11 = date11;
	}

	/**
	 * @return the date12
	 */
	public Date getDate12() {
		return date12;
	}

	/**
	 * @param date12 the date12 to set
	 */
	public void setDate12(Date date12) {
		this.date12 = date12;
	}
}
