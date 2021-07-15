package contas_bancarias;

import java.util.Objects;
import java.util.Random;

public abstract class Conta {
	Random random = new Random();
	private int numero = random.nextInt(9999);
	protected double saldo;
	private Cliente cliente;
	protected Manager manager;
    protected Statement statement = new Statement();

	

	private double somaDeposito = 0;
	private double somaTransferencias;

	public Conta(Cliente titular, double saldo) throws Exception {
		this.cliente = titular;
		deposita(saldo);

		System.out.println("Estou criando uma conta /Número: " + numero + " /com saldo inicial de R$" + saldo);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return "Conta [random=" + random + ", numero=" + numero + ", saldo=" + saldo + ", titular=" + cliente + "]";
	}

	public void deposita(double valor) throws Exception {
		if (valor <= 0) {
			throw new Exception("Valor deve ser maior que zero");
		}
		this.saldo += valor;
		System.out.println("******Depósito realizado com sucesso!*******");
		this.somaDeposito += valor;
		this.statement.addTransaction(valor);
	}

	public void saca(double valor) throws Exception {
		if (valor <= 0) {
			System.out.println("Valor deve ser maior do que 0");
		}
		if (this.getSaldo() < valor) {
			throw new Exception("Seu saldo não é suficiente!");
		} else {
			this.saldo -= valor;
			this.statement.addTransaction(-valor);
			System.out.println("******Saque realizado com sucesso!*******");

		}

	}

	public void transfere(double valor, Conta destino) throws Exception {
		if (this.getSaldo() < valor) {
			throw new Exception("Saldo insuficiente");
		}
		this.saldo -= valor;
		destino.deposita(valor);
		this.somaTransferencias += valor;
		this.statement.addTransaction(-valor);
		System.out.println("******Transferência realizada com sucesso!*******");

	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setTitular(Cliente titular) {
		this.cliente = titular;
	}

	public Cliente getTitular() {
		return cliente;
	}

	public void listarExtrato() {
		System.out.println("**********Extrato de movimentações***********");
		System.out.println("Valor total em depósitos: " + this.somaDeposito);
		System.out.println("Valor total transferido: " + this.somaTransferencias);
		System.out.println("Saldo atual :" + this.getSaldo());
		System.out.println("*********************************************");
	}
	
	 public void showStatement() {
	        this.statement.showTransactions();
	    }


}
