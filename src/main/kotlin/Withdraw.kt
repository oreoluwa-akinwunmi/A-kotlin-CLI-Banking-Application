class Withdraw(account: BankAccount) : AccountService(account), Transaction {
    override fun execute(amount: Double) {
        if (amount <= 0) {
            println(TransactionResult.Error("Invalid withdrawal amount"))
        } else if (amount > account.balance) {
            println(TransactionResult.Error("Insufficient balance"))
        } else {
            account.balance -= amount
            println(TransactionResult.Success("Withdrawn ₦$amount. Remaining balance: ${account.balance}"))
        }
    }
}