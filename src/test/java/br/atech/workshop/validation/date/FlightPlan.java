package br.atech.workshop.validation.date;

import java.util.Date;

import br.atech.workshop.validation.handsOn.Programas;

public class FlightPlan {

	private String indicative;
	
	private String adep;
	
	private String ades;
	
	private String alternativeAdes;

	@DateRange(max = DateRange.Ranges.ThisYear, maxGap = -18, groups = Programas.BolsaFamilia.class)
	private Date eobt;
	
}
