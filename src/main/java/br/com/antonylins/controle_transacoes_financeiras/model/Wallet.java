package br.com.antonylins.controle_transacoes_financeiras.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;
import br.com.antonylins.controle_transacoes_financeiras.model.BankService;
import br.com.antonylins.controle_transacoes_financeiras.model.Money;
import br.com.antonylins.controle_transacoes_financeiras.model.MoneyAudit;

@ToString
public abstract class Wallet {

    @Getter
    private final BankService serviceType;

    protected final List<Money> money;

    public Wallet(final BankService serviceType) {
        this.serviceType = serviceType;
        this.money = new ArrayList<>();
    }

    protected List<Money> generateMoney(final long amount, final String description) {
        var history = new MoneyAudit(UUID.randomUUID(), this.serviceType, description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history)).limit(amount).toList();
    }

    public long getFunds() {
        return money.size();
    }

    public void addMoney(final List<Money> money, final BnakService service, final String description) {
        var history = new MoneyAudit(UUID.randomUUID(), this.serviceType, description, OffsetDateTime.now());
        moneyToAdd.forEach(m -> m.addHistory(history));
        this.money.addAll(moneyToAdd);
    }

    public List<Money> reduceMoney(final long amount) {
        List<Money> toRemove = new ArrayList<>();
        for (int i = 0; i < amount && !this.money.isEmpty(); i++) {
            toRemove.add(this.money.removeFirst());
        }
        return toRemove;
    }

    public List<MoneyAudit> getFinancialTransactions() {
        return money.stream()
                .flatMap(m -> m.getHistory().stream())
                .toList();
    }
}