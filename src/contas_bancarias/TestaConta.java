package contas_bancarias;

public class TestaConta {
	public static void main(String[] args) {

		Cliente aretha = new Cliente(); // cria o cliente
		aretha.setNome("Aretha Martins");
		aretha.setCpf("388.258.254-12");
		aretha.setProfissao("programadora");

		ContaCorrente contaDaAretha = new ContaCorrente(288, aretha, "Conta corrente", 100.0); // cria a conta

		contaDaAretha.setTitular(aretha); // vincula o cliente como titular da conta

		System.out.println("****************************************");

		Cliente victor = new Cliente(); // cria o cliente
		victor.setNome("Victor");
		victor.setCpf("398.205.204-15");
		victor.setProfissao("programador");

		ContaPoupanca contaDoVictor = new ContaPoupanca(288, victor, "Conta poupança", 100.0); // cria a conta

		contaDoVictor.setTitular(victor); // vincula o cliente como titular da conta

		System.out.println("****************************************");

		Cliente rafaela = new Cliente(); // cria o cliente
		rafaela.setNome("Rafaela");
		rafaela.setCpf("378.345.200-15");
		rafaela.setProfissao("programadora");

		ContaPoupanca contaDaRafaela = new ContaPoupanca(288, rafaela, "Conta poupança", 200.0); // cria a conta

		contaDaRafaela.setTitular(rafaela); // vincula o cliente como titular da conta

		System.out.println("****************************************");

		// verifica dados da conta:
		System.out.println("******************Dados do titular da conta**********************");
		System.out.println("Titular: " + contaDaAretha.getTitular().getNome());
		System.out.println("CPF: " + contaDaAretha.getTitular().getCpf());
		System.out.println("Profissão: " + contaDaAretha.getTitular().getProfissao());
		System.out.println("Agência: " + contaDaAretha.getAgencia());
		System.out.println("Número: " + contaDaAretha.getNumero());
		System.out.println("Tipo de Conta: " + contaDaAretha.getTipoConta());
		System.out.println("Saldo inicial R$: " + contaDaAretha.getSaldo());
		System.out.println("Status: " + contaDaAretha.getStatus());

		System.out.println("****************************************");

		// operando com a conta
		System.out.println("******************Depósito de 500,00**********************");
		contaDaAretha.deposita(500.0);
		System.out.println("R$" + contaDaAretha.getSaldo());
		System.out.println("******************Saque de 20,00**********************");
		contaDaAretha.saca(20.0);
		System.out.println("R$" + contaDaAretha.getSaldo());
		System.out.println("******************Saldo final**********************");
		System.out.println("Saldo total da conta: R$" + contaDaAretha.getSaldo());
		
		System.out.println("------------------------------------------------------------------");

		// verifica dados da conta:
		System.out.println("******************Dados do titular da conta**********************");
		System.out.println("Titular: " + contaDaRafaela.getTitular().getNome());
		

		System.out.println("Saldo inicial: R$" + contaDaRafaela.getSaldo());

		System.out.println("Transferência de R$100,00 da conta da Aretha para conta da Rafaela ");

		contaDaAretha.transfere(100.0, contaDaRafaela);

		System.out.println("Saldo final: R$" + contaDaRafaela.getSaldo());

	}

}
