package org.jh.com.account;

import org.assertj.core.api.Assertions;
import org.jh.com.exception.InvalidAmountException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTest {
    Account account;

    @Before
    public void setUp(){
        account = new Account(new TransactionHistory());
    }
    @Test
    public void return_account_with_zero_balance(){
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void increase_balance_when_save_money() {
        account.deposit(BigDecimal.valueOf(100));
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(100));
    }
    @Test
    public void throw_exception_when_amount_is_negative(){

        Assertions.assertThatThrownBy(() -> account.deposit(BigDecimal.valueOf(-12)))
                .isInstanceOf(InvalidAmountException.class)
                .hasMessage("The Amount should not be null or negative");
    }

    @Test
    public void throw_exception_when_amount_is_null(){

        Assertions.assertThatThrownBy(() -> account.deposit(null))
                .isInstanceOf(InvalidAmountException.class)
                .hasMessage("The Amount should not be null or negative");
    }
    @Test
    public void add_transaction_when_save_money(){
        account.deposit(BigDecimal.valueOf(100));
        account.deposit(BigDecimal.valueOf(100));
        Assertions.assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(200));
    }

}
