package contas_bancarias;

public abstract class Conta {
	private double saldo;
	private int agencia;
	private double numero;
	private String tipoConta;
	private Cliente titular;
	private boolean status = false;

	public Conta(int agencia, Cliente titular, String tipoConta, double saldo) {
		this.status = true;
		this.titular = titular;
		if (agencia <= 0) {
			System.out.println("Número deve ser maior que 0");
		}
		this.agencia = agencia;

		this.numero = Math.random();
		this.numero = Math.round(this.numero);

		this.tipoConta = tipoConta;

		this.saldo = saldo;

		System.out.println("Estou criando uma conta do tipo: " + tipoConta + " /Agência:" + ' ' + agencia + " /Número: "
				+ numero + " /com saldo inicial de R$" + saldo + " /Status:" + status);

	}

	public void abrirConta() {
		this.setStatus(true);
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public double getNumero() {
		return this.numero;
	}

	public void setAgencia(int agencia) {
		if (agencia <= 0) {
			System.out.println("O número deve ser maior que 0");
		}
		this.agencia = agencia;
	}

	public int getAgencia() {
		return this.agencia;
	}

	void deposita(double valor) {
		this.saldo += valor;
	}

	public boolean saca(double valor) {
		if (this.saldo >= valor) {
			this.saldo -= valor;
			return true;
		} else {
			return false;
		}
	}

	public boolean transfere(double valor, Conta destino) {
		if (this.saldo >= valor) {
			this.saldo -= valor;
			destino.deposita(valor);
			return true;
		} else {
			return false;
		}
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void listaExtrato() {

	}

	public void sacar() {

	}

	public void depositar() {

	}

	public void adicionarLimite() {

	}

	public void encerrarConta() {
		if (this.getSaldo() > 0) {
			System.out.println("Conta não pode ser fechada pois ainda tem saldo");
		} else if (this.getSaldo() < 0) {
			System.out.println("Conta não pode ser fechada pois está em débito");
		} else {
			this.setStatus(false);
			System.out.println("Conta fechada com sucesso!");
		}

	}

}
