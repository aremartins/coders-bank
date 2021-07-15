package contas_bancarias;

public class ContaCorrente extends Conta {
	Double overdraft;

	public ContaCorrente(Cliente titular, double saldo, Double overdraft) throws Exception {
		super(titular, saldo);
		this.overdraft = overdraft;

	}

	public void saca(double valorSaque) throws Exception {
		if (valorSaque <= 0) {
			System.out.println("Valor deve ser maior do que 0.");
		} else {
			if (this.getSaldo() < valorSaque && valorSaque <= 1000) {
				this.saldo -= valorSaque;
				System.out.println("***************************************");
				System.out.println("Você entrou no cheque especial. Valor: " + Math.abs(getSaldo()));
			} else if (valorSaque > this.saldo && valorSaque > 1000) {
				System.out.println("***************************************");
				System.out.println("Limite indisponível");
			} else if (this.saldo >= valorSaque) {
				this.saldo -= valorSaque;
				System.out.println("Saque realizado com sucesso!");
			}

		}
	}

	public Double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(Double overdraft, String password) throws Exception {
		if (overdraft <= 0) {
			throw new Exception("Valor deve ser maior que zero");
		}
		if (overdraft < this.overdraft) {
			throw new Exception("Valor deve ser maior que limite atual");
		}
		if (password.equals(this.manager.getPassword())) {
			this.overdraft = overdraft;
			System.out.printf("Limite cheque especial aumentado para R$ %.2f%n", this.overdraft);
		} else {
			System.out.println("Erro senha inválida");
		}
	}

}
