
public class CreateAccount{

    private AccountDAO accountDAO;

    public CreatingAccount() {
        accountDAO = new AccountDAO();
    }

    public CreatingAccount(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public CreatingAccount(Account account) {

    }

    public Account getAccount(String username, String password) {


    }

    public Account login () {

    }

    public Reservation getReservationByAccount() {

    }
}