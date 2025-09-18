package main.java.br.com.antonylins.controle_transacoes_financeiras.repository;

import java.util.ArrayList;
import java.util.List;

import main.java.br.com.antonylins.controle_transacoes_financeiras.exception.AccountWithInvestmentException;
import main.java.br.com.antonylins.controle_transacoes_financeiras.exception.InvestmentNotFoundException;
import main.java.br.com.antonylins.controle_transacoes_financeiras.exception.WalletNotFoundException;
import main.java.br.com.antonylins.controle_transacoes_financeiras.model.AccountWallet;
import main.java.br.com.antonylins.controle_transacoes_financeiras.model.Investment;
import main.java.br.com.antonylins.controle_transacoes_financeiras.model.InvestmentWallet;

public class InvestmentRepository {

    private long nextId;
    private final List<Investment> investments = new ArrayList<>();
    private final List<InvestWallet> wallets = new ArrayList<>();

    public Investment create(final long tax, final long initialFunds){
        this.nextId ++;
        var investmnet = new Investment(this.netxId, tax, initialFunds);
        investments.add(investment);
        return investment;
    }

    public InvestWallet initInvestment(final AccountWallet account, final long id){
        var accountsInUse = wallets.stream().map(InvestmentWallet :: getAccount).toList();
        if (accountsInUse.contains(account)) {
                throw new AccountWithInvestmentException("A conta " + account + "já ja possui um investimento");
    }
}

    public InvestmentWallet deposit(final String pix, final long funds){
        var wallet = findWalletByAccountPix(pic);
        wallet.addMoney(wallet.getAccount().reduceMoney(funds), wallet.getService(), "Investimento");
        return wallet;
    }

    public InvestmentWallet withdraw(final String pic, final long funds){
        var wallet = findWalletByAccountPix(pic);
        checkFundsForTransaction(wallet, funds);
        wallet.getAccount().addMoney(wallet.reduceMoney(funds), wallet.getService(), "saque de investimento");
        if (wallet.getFunds() == 0){
            wallets.remove(wallet);
        }
        return wallet;
    }

    public void updateAmount(final long percent){
        wallets.forEach(w -> w.updateAmount(percent));
    }

    public Investment findById(final long id){
        return investments.stream()
            .filter(a -> a.id() == id)
            .findFirst()
            .orElseThrow(
                    () -> new InvestmentNotFoundException("O investimento" + id + " não foi encontrado")
            );
    }

    public InvestmentWallet findWalletByAccountPix(final String pix) {
        return wallets.stream()
            .filter(w -> w.getAccount().getPix().contains(pix))
            .findFirst()
            .orElseThrow(
                    () -> new WalletNotFoundException("A carteira não foi encontrada")
            );
    }
}
