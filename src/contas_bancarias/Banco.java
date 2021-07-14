package contas_bancarias;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	private List<Conta> accounts;

	public Banco() {
		this.accounts = new ArrayList<>();
	}

	public void addAccount(Conta account) {
		accounts.add(account);
	}

	public Conta getAccount(String name) {
		// tratar null pointer Exception
		for (int i = 0; i < this.accounts.size(); i++) {
			if (this.accounts.get(i).getCliente().getNome().equals(name)) {
				return this.accounts.get(i);
			}
		}
		return null;
	}

	public void deleteAccount(String name) {
		if (getAccount(name).getSaldo() > 0) {
			System.out.println("Voc� deve sacar todo dinheiro antes de encerrar a conta");
		} else if (getAccount(name).getSaldo() < 0) {
			System.out.println("Verifique d�bitos pendentes com a institui��o.");
		}else if(getAccount(name).getSaldo() == 0) {
			Conta account = getAccount(name);
			accounts.remove(account);
			System.out.println("Conta encerrada com sucesso.");
		}
	}
}
