package main.java.br.com.antonylins.controle_transacoes_financeiras.model;

@EqualsAndHashCode
@ToString
@Getter
public class Money {

    private final List<MoneyAudit> history = new ArrayList<>();

    
    public Money(MoneyAudit history){
        this.hisstory.add(history);
    }

    public void addHistory(final MoneyAudit history){
        this.history.add(history);
    }

}