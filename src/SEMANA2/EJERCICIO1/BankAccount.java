package SEMANA2.EJERCICIO1;

public class BankAccount {
    private double balance;
    private boolean locked;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.locked = false;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Monto invalido: " + amount);
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Monto invalido: " + amount);
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Fondos insuficientes para retirar $" + amount,
                amount - balance
            );
        }

        balance -= amount;
    }

    public void transfer(BankAccount target, double amount)
            throws InsufficientBalanceException {

        try (TransactionLog log = new TransactionLog()) {

            this.withdraw(amount);
            log.log("Retiro de $" + amount + " de cuenta origen. Saldo: $" + this.balance);

            target.deposit(amount);
            log.log("Deposito de $" + amount + " en cuenta destino. Saldo: $" + target.balance);
        }
    }

    public void lock() {
        this.locked = true;
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccount cuenta1 = new BankAccount(1000.00);
        BankAccount cuenta2 = new BankAccount(500.00);

        try {
            cuenta1.deposit(500);
            System.out.printf("Deposito exitoso. Saldo: $%.2f%n", cuenta1.getBalance());

            cuenta1.withdraw(200);
            System.out.printf("Retiro exitoso. Saldo: $%.2f%n", cuenta1.getBalance());

            cuenta1.transfer(cuenta2, 300);
            System.out.printf(
                "Transferencia exitosa. Saldo cuenta1: $%.2f, cuenta2: $%.2f%n",
                cuenta1.getBalance(), cuenta2.getBalance()
            );

        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Manejo de Errores ===");

        // multi-catch (aquí solo uno, pero estructura correcta)
        try {
            cuenta1.deposit(-100);
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            cuenta1.withdraw(999999);
        } catch (InsufficientBalanceException e) {
            System.out.printf(
                "Error: %s (deficit: $%.2f)%n",
                e.getMessage(),
                e.getDeficit()
            );
        }
    }
}