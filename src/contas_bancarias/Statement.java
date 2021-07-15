package contas_bancarias;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public class Statement {
    Map<LocalDateTime, Double> transactions;

    public Statement() {
        this.transactions = new TreeMap<>((o1, o2) -> -o1.compareTo(o2));
    }

    public void addTransaction(Double value) {
        LocalDateTime now = LocalDateTime.now();
        this.transactions.put(now, value);
    }

    public void showTransactions() {
        System.out.println("****************** Extrato ********************");
        this.transactions.forEach((d, v) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String formattedNow = d.format(formatter);
            System.out.printf("Data: %s, Valor: R$ %.2f%n",formattedNow,v);
        });
        System.out.println("***********************************************");
    }

    //não vou implementar pra remover transação, porque não foi pedido, e no programa não tem opção de dar rollback
    //em uma transação
}
