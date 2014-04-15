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
		
		new TestUtil(Perfis.SeguroDesemprego.class).checkViolations(bean,
				"demissao");
	}

	@Test
	public void testUltimoSalario() {
		Emprego bean = new Emprego();
		bean.setEmpresa("XPTO");
		bean.setAdmissao(new Date());

		bean.setUltimoSalario(new BigDecimal("10000.250"));
		new TestUtil().checkViolations(bean);

		bean.setUltimoSalario(new BigDecimal("10000.001"));
		new TestUtil().checkViolations(bean, "ultimoSalario");

		bean.setUltimoSalario(new BigDecimal("1000000000.00"));
		new TestUtil().checkViolations(bean, "ultimoSalario");
	}
}
