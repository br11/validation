package br.atech.workshop.validation.handsOn;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import br.atech.workshop.validation.TestUtil;
import br.atech.workshop.validation.util.DateRangeUtil;

public class TesteMenorDeIdade {

	private DateRangeUtil util = new DateRangeUtil();

	@Test
	public void testRequired() {
		MenorDeIdade bean = new MenorDeIdade();

		new TestUtil().checkViolations(bean, "nome");

		bean.setNome("     ");
		new TestUtil().checkViolations(bean, "nome");
	}

	@Test
	public void testBolsaFamiliaRequired() {
		MenorDeIdade bean = new MenorDeIdade();

		new TestUtil(Perfis.BolsaFamilia.class).checkViolations(bean,
				"dataDeNascimento");
	}

	@Test
	public void testBolsaFamiliaMenorIdade() {
		MenorDeIdade bean = new MenorDeIdade();
		bean.setNome("fulano de tal");

		bean.setDataDeNascimento(util.add(new Date(), Calendar.YEAR, -17));
		new TestUtil(Perfis.BolsaFamilia.class).checkViolations(bean);

		bean.setDataDeNascimento(util.add(new Date(), Calendar.YEAR, -18));
		new TestUtil(Perfis.BolsaFamilia.class).checkViolations(bean);

		bean.setDataDeNascimento(util.add(new Date(), Calendar.YEAR, -19));
		new TestUtil(Perfis.BolsaFamilia.class).checkViolations(bean,
				"dataDeNascimento");
	}

}
