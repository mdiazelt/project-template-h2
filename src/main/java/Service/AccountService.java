package Service;

import DAO.AccountDAO;
import Model.Account;
import Model.Reservation;
public class AccountService{

    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account CreateAccount(Account account) {
        if(account.getUsername() == null || account.getUsername().isEmpty()){
            return null;
        }
        if(account.getPassword() == null || account.getPassword().length() < 4){
            return null;
        }
        return accountDAO.createAccount(account);
    }

    public Account getAccount(String username, String password) {
        return accountDAO.getAccount(username, password);

    }

    public Account login(Account account) {
        return accountDAO.getAccount(account.getUsername(), account.getPassword());
    }

    public Reservation getReservationByAccount(int account_id) {
        return null;
    }
}