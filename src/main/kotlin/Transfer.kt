class Transfer(
    account: BankAccount,
    private val recipientAccount: BankAccount
) : AccountService(account), Transaction {

    override fun execute(amount: Double) {

        val isSameBank = account.bankName == recipientAccount.bankName

        val interBankCharge = if (isSameBank) 0.0 else 10.0
        val highAmountCharge = if (amount >= 10_000) 50.0 else 0.0

        val totalCharge = interBankCharge + highAmountCharge
        val totalDebit = amount + totalCharge

        when {
            amount < 50 -> {
                println(TransactionResult.Error("Minimum transfer amount is ${formatMoney(50.0)}"))
            }

            totalDebit > account.balance -> {
                println(TransactionResult.Error("Insufficient balance"))
            }

            else -> {
                // Confirmation
                println("You are about to transfer ${formatMoney(amount)} to:")
                println("Name: ${recipientAccount.fullName}")
                println("Account Number: ${recipientAccount.accountNumber}")
                println("Bank Name: ${recipientAccount.bankName}")
                println("Charges: ${formatMoney(totalCharge)}")
                println("Confirm: y/n")

                val confirmation = readlnOrNull()?.trim()

                when (confirmation?.lowercase()) {
                    "y" -> {
                        account.balance -= totalDebit
                        recipientAccount.balance += amount

                        println(
                            TransactionResult.Success(
                                "Transfer successful! ${formatMoney(amount)} sent."
                            )
                        )
                        println("New Balance: ${formatMoney(account.balance)}")
                    }

                    else -> {
                        println(TransactionResult.Error("Transfer cancelled."))
                    }
                }
            }
        }
    }
}