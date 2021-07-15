package contas_bancarias;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) throws Exception {
		// Criar conta - perguntar nome cliente, saldo inicial, tipo de conta e id
		// aleatório de 4 digitos
		Locale.setDefault(new Locale("pt", "BR"));
		Banco bank = new Banco();
		mainMenu(bank);

		// Ao terminar de preencher os dados de abertura da conta, o programa deverá
		// mostrar um menu com as seguintes
		// opções;
		// * Listar
		// * Realizar Saque
		// * Depositar;
		// * Adicionar limite de cheque especial ( Deve pedir a senha do gerente)

		// Encerrar conta (Ao encerrar a conta, o usuário deve realizar o saque de
		// todo o valor restante na conta e não pode estar devendo nada no cheque
		// especial).
	}

	public static void mainMenu(Banco bank) throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;
		do {
			System.out.println("================== Coders Bank ==================");
			System.out.println("Para cadastrar uma nova conta digite 1");
			System.out.println("Para acessar uma conta digite 2");
			System.out.println("Para sair, digite 3");
			System.out.print("Opção: ");
			if (!sc.hasNextInt()) {
				System.out.println("Comando inválido");
				sc.next();
				continue;
			}

			int resp = sc.nextInt();
			switch (resp) {
			case 1:
				System.out.print("Digite o nome do cliente: ");
				sc.nextLine();
				String clientName = sc.nextLine().trim();
				System.out.print("Digite o cpf do cliente: ");
				String cpf = sc.nextLine().trim();
				System.out.print("Digite a profissão do cliente: ");
				String occupation = sc.nextLine().trim();
				System.out.print("Conta corrente (CC) ou Conta Poupança (CP)? ");
				String accountType;
				if (sc.hasNext("C[C|P]")) {
					accountType = sc.next();
				} else {
					System.out.println("Erro comando inválido");
					sc.next();
					continue;
				}
				System.out.print("Digite o depósito inicial: ");
				double initialDeposit = sc.nextDouble();
                Cliente client = new Cliente(clientName, cpf, occupation);

				if (accountType.equals("CC")) {
                    bank.addAccount(new ContaCorrente(client, initialDeposit, 300.0));					
				} else {
					bank.addAccount(new ContaPoupanca(new Cliente(clientName, cpf, occupation), initialDeposit));
				}
				break;
			case 2:
				// Acessar conta
				clientMenu(bank);
				break;
			case 3:
				isRunning = false;
				break;
			default:
				System.out.println("Comando inválido");
			}
		} while (isRunning);
		sc.close();
	}

	public static void clientMenu(Banco bank) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite seu nome completo: ");
		String clientName = sc.nextLine().trim();
		if (bank.getAccount(clientName) == null) {
			System.out.println("Conta não existente");
			return;
		}
		Conta account = bank.getAccount(clientName);
		System.out.printf("============== Bem vindo(a) %s", account.getCliente().getNome() + " ================");
		System.out.println();
		int resp;
		do {
			System.out.println("Para consultar seu saldo, digite 1");
			System.out.println("Para listar seu extrato, digite 2");
			System.out.println("Para realizar saque, digite 3");
			System.out.println("Para realizar depósito, digite 4");
			System.out.println("Para efetuar uma transferência, digite 5");
			System.out.println("Para adicionar limite de cheque especial, digite 6");
			System.out.println("Para encerrar sua conta, digite 7");
			System.out.println("Para voltar, digite 0");
			resp = sc.nextInt();

			switch (resp) {
			case 1:
				// saldo
				System.out.printf("Seu saldo é R$ %.2f%n", account.getSaldo());
				break;
			case 2:
				account.listarExtrato();
				account.showStatement();
				break;
			case 3:
				// realizar saque
				System.out.println("Quanto deseja sacar?");
				System.out.print("Valor: ");
				try {
					account.saca(sc.nextDouble());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.printf("Seu saldo atualizado é %.2f%n", account.getSaldo());

				break;
			case 4:
				// depositar
				System.out.println("Quanto deseja depositar?");
				System.out.print("Valor: ");
				account.deposita(sc.nextDouble());
				System.out.printf("Seu saldo atualizado é %.2f%n", account.getSaldo());
				break;
			case 5:
				// transferir
				System.out.print("Digite o cpf do destinatário: ");
				String cpf = sc.next();
				System.out.print("Digite o nome do destinatário: ");
				String name = sc.next();
				Conta destination = bank.getAccount(name);
				System.out.println("Digite o valor para transferir:");
				try {
					account.transfere(sc.nextDouble(), destination);

				} catch (Exception e) {

				}
				break;
			case 6:				
				 if (account instanceof ContaCorrente) {
                     System.out.println("Solicite um valor maior de cheque especial");
                     System.out.printf("Seu valor atual é %.2f%n",
                             ((ContaCorrente) account).getOverdraft());
                     System.out.print("Novo valor: ");
                     double newOverdraft = sc.nextDouble();
                     System.out.println("Simulação gerente aprovando e digitando a senha ...");
                     System.out.print("Digite a senha gerente: ");
                     String password = sc.next();
                     try {
                         ((ContaCorrente) account).setOverdraft(newOverdraft, password);
                     } catch (Exception e) {
                         System.out.println(e.getMessage());
                     }
                 } else {
                     System.out.println("Função indisponível para conta poupança");
                 }
                 break;
			case 7:
				System.out.println("Tem certeza que deseja encerrar a conta [s/N]?");
				if (sc.next().charAt(0) == 's') {
					bank.deleteAccount(clientName);
					resp = 0;
				} else {
					System.out.println("Resposta inválida");
				}
				break;
			default:
				System.out.println("Saindo");
				break;

			}
		} while (resp != 0);
	}
}