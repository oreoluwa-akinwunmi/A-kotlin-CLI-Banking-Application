class Deposit(account: BankAccount) : AccountService(account), Transaction {
    override fun execute(amount: Double) {
        if (amount <= 0) {
            println(TransactionResult.Error("Invalid deposit amount"))
        } else {
            account.balance += amount
            println(TransactionResult.Success("Deposited $amount. New balance: ${account.balance}"))
        }
    }
}