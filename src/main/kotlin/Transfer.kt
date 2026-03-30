// transfer charges - 10 naira per transaction
// 50 naira charge for transactions above 10,000
// add naira symbol
// no charge to same bank

class Transfer(account: BankAccount, private val recipientAccount: BankAccount) : AccountService(account), Transaction {
    override fun execute(amount: Double) {
        val isSameAccount = account.bankName == recipientAccount.bankName

        if (amount < 50){
            println(TransactionResult.Error("Minimum transfer amount is 50."))
        } else if (amount > (account.balance - 10)) {
            println(TransactionResult.Error("Insufficient balance"))
        } else if (amount < 10000 && amount > (account.balance - 10)) {
            println(TransactionResult.Error("Insufficient balance"))
        } else if (amount >= 10000 && amount > (account.balance - 50 - 10)) {
            println(TransactionResult.Error("Insufficient balance."))
        } else if (amount < 10000 && amount <= (account.balance - 10) && isSameAccount) {
            account.balance -= amount
            recipientAccount.balance += amount
            println(TransactionResult.Success("You have successfully transferred $amount to: "))
            println("Name: ${recipientAccount.fullName}")
            println("Account Number: ${recipientAccount.fullName}")
            println("Bank Name: ${recipientAccount.bankName}")
        } else if (amount < 10000 && amount <= (account.balance - 10)) {
            account.balance -= (10 + amount)
            recipientAccount.balance += amount
            println(TransactionResult.Success("You have successfully transferred $amount to: "))
            println("Name: ${recipientAccount.fullName}")
            println("Account Number: ${recipientAccount.accountNumber}")
            println("Bank Name: ${recipientAccount.bankName}")
        } else if (amount >= 10000 && isSameAccount && amount <= (account.balance - 50)) {
            println("You are about to transfer $amount to: ")
            println("Name: ${recipientAccount.fullName}")
            println("Account Number: ${recipientAccount.accountNumber}")
            println("Bank Name: ${recipientAccount.bankName}")
            println("Confirm: y/n")
            val confirmation = readlnOrNull()?.trim()

            if (confirmation.equals("y", ignoreCase = true)) {
                account.balance -= (50 + amount)
                recipientAccount.balance += amount
                println(TransactionResult.Success("You have successfully transferred $amount to: "))
                println("Name: ${recipientAccount.fullName}")
                println("Account Number: ${recipientAccount.accountNumber}")
                println("Bank Name: ${recipientAccount.bankName}")
            } else println("Transfer cancelled.")
        }
        else {
            println("You are about to transfer $amount to: ")
            println("Name: ${recipientAccount.fullName}")
            println("Account Number: ${recipientAccount.accountNumber}")
            println("Bank Name: ${recipientAccount.bankName}")
            println("Confirm: y/n")
            val confirmation = readlnOrNull()?.trim()

            if (confirmation.equals("y", ignoreCase = true)) {
                account.balance -= (50 + 10 + amount)
                recipientAccount.balance += amount
                println(TransactionResult.Success("You have successfully transferred $amount to: "))
                println("Name: ${recipientAccount.fullName}")
                println("Account Number: ${recipientAccount.accountNumber}")
                println("Bank Name: ${recipientAccount.bankName}")
            } else println("Transfer cancelled.")
        }

    }
}