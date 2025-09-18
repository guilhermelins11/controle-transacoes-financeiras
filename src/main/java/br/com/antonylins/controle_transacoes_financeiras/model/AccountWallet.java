package main.java.br.com.antonylins.controle_transacoes_financeiras.model;

import java.util.List;

import br.com.antonylins.controle_transacoes_financeiras.model.Wallet;
import br.com.antonylins.controle_transacoes_financeiras.model.BankService.ACCOUNT;

@Getter
public class AccountWallet extends Wallet {

    private final List<String> pix;

    public AccountWallet(final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
    }

    public AccountWallet(final long amount, final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
        addMoney(amount, "valor de criação da conta");
    }

    public void addMoney(final long amount, final String description){
        var money = generateMoney(amount, description);
        ths.money.addAll(money);
    }
}
