import kotlin.random.Random


// -------------------- BANK SYSTEM --------------------
class BankSystem {
    private val accounts = mutableListOf<BankAccount>()

    private val usedAccountNumbers = mutableSetOf<String>()

    private fun generateUniqueAccountNumber(): String {
        var newNumber: String
        do {
            newNumber = Random.nextLong(1_000_000_000L, 10_000_000_000L).toString()
        } while (usedAccountNumbers.contains(newNumber))

        usedAccountNumbers.add(newNumber)
        return newNumber
    }

    fun createAccount(firstName: String?, lastName: String?, bankName: BankName): BankAccount {
        val fName = firstName?.capitalizeFirst()
        val lName = lastName?.capitalizeFirst()
        val accNo = generateUniqueAccountNumber()

        val fullName = "$fName $lName"
        val account = BankAccount(
            accountNumber = accNo,
            bankName = bankName,
            firstName = fName,
            lastName = lName,
            fullName = fullName
        )
        accounts.add(account)
        return account
    }

    fun findAccount(accountNumber: String): BankAccount? {
        return accounts.find { it.accountNumber == accountNumber }
    }

    fun displayAccounts() {
        if (accounts.isEmpty()) {
            println("No accounts available.")
            return
        }

        println("\n--- Registered Accounts ---")
        accounts.forEach {
            println("Bank: ${it.bankName} \t Name: ${it.fullName} \t Account Number: ${it.accountNumber} \t Balance: ${it.balance}")
        }
    }
}