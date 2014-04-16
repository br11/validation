package br.atech.workshop.validation.handsOn;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import br.atech.workshop.validation.TestUtil;

public class TesteEmprego {

	@Test
	public void testRequired() {
		Emprego bean = new Emprego();

		new TestUtil().checkViolations(bean, "empresa", "admissao");

		bean.setEmpresa("    ");
		new TestUtil().checkViolations(bean, "empresa", "admissao");
	}

	@Test
	public void testSeguroDesemprego() {
		Emprego bean = new Emprego();

		new TestUtil(Programas.SeguroDesemprego.class).checkViolations(bean,
				"demissao");
	}

	@Test
	public void testUltimoSalario() {
		Emprego bean = new Emprego();
		bean.setEmpresa("XPTO");
		bean.setAdmissao(new Date());

		/* salário ok */
		bean.setUltimoSalario(new BigDecimal("10000.250"));
		new TestUtil().checkViolations(bean);

		/* excesso de casas decimais */
		bean.setUltimoSalario(new BigDecimal("10000.001"));
		new TestUtil().checkViolations(bean, "ultimoSalario");

		/* excede o valor máximo permitido para o campo */
		bean.setUltimoSalario(new BigDecimal("1000000000.00"));
		new TestUtil().checkViolations(bean, "ultimoSalario");
	}
}
