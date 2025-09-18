package main.java.br.com.antonylins.controle_transacoes_financeiras.repository;

import java.time.OffsetDateTime;

import br.com.antonylins.controle_transacoes_financeiras.model.Wallet;
import main.java.br.com.antonylins.controle_transacoes_financeiras.exception.NotFundsEnoughException;
import main.java.br.com.antonylins.controle_transacoes_financeiras.model.MoneyAudit;

@NoArgsConstructor(acess = PRIVATE)
public final class CommomsRepository {

    public static void checkFundsForTransaction(final Wallet source, final long amount){
        if (source.getFunds() < amount) {
            throw new NotFundsEnoughException("Sua conta não tem dinheiro o suficiente para realizar essa transação.");
        }
    }

    public static List<Money> generateMoney(final UUID transactionId, final long funds, final String description){
        var history = new MoneyAudit(transactionId, ACCOUNT, description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history)).limit(funds).toList();
    }
}
