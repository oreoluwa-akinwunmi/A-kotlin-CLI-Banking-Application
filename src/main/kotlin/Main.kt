import kotlin.random.Random

// -------------------- INTERFACE --------------------
interface Transaction {
    fun execute(amount: Double)
}

// -------------------- SEALED CLASS --------------------
sealed class TransactionResult {
    data class Success(val message: String) : TransactionResult()
    data class Error(val message: String) : TransactionResult()
}

// -------------------- DATA CLASS --------------------
data class BankAccount(
    val accountNumber: String = Random.nextLong(1_000_000_000L, 10_000_000_000L).toString(),
    val firstName: String?,
    val lastName: String?,
    val fullName: String,
    var balance: Double = 0.0
)

// -------------------- BASE CLASS --------------------
open class AccountService(protected val account: BankAccount)

// -------------------- INHERITANCE --------------------
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

// -------------------- BANK SYSTEM --------------------
class BankSystem {
    private val accounts = mutableListOf<BankAccount>()

    fun createAccount(firstName: String?, lastName: String?): BankAccount {
        val fullName = "$firstName $lastName"
        val account = BankAccount(firstName = firstName, lastName = lastName, fullName = fullName)
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
            println("Name: ${it.fullName} | Account Number: ${it.accountNumber} | Balance: ${it.balance}")
        }
    }
}

// -------------------- MAIN FUNCTION --------------------
fun main() {
    val bankSystem = BankSystem()
    var running = true

    while (running) {
        println("\n--- Banking System ---")
        println("1. Create Account")
        println("2. Deposit")
        println("3. Withdraw")
        println("4. View Accounts")
        println("5. Exit")
        print("Choose option: ")

        // Using readlnOrNull() is the modern Kotlin way
        when (readlnOrNull()?.toIntOrNull()) {

            1 -> {
                print("Enter first name: ")
                val firstName = readlnOrNull()?.trim()
                print("Enter last name: ")
                val lastName = readlnOrNull()?.trim()

                // Check validation BEFORE creating the account
                if (firstName.isNullOrBlank() || lastName.isNullOrBlank()) {
                    println("Error: First name and last name cannot be empty.")
                } else {
                    val account = bankSystem.createAccount(firstName, lastName)
                    println("Account created! Your account number is ${account.accountNumber}")
                }
            }

            2 -> {
                print("Enter account number: ")
                val accNo = readlnOrNull() ?: ""
                val account = bankSystem.findAccount(accNo)

                if (account != null) {
                    print("Enter amount to deposit: ")
                    val amount = readlnOrNull()?.toDoubleOrNull() ?: 0.0
                    Deposit(account).execute(amount)
                } else {
                    println("Error: Account not found.")
                }
            }

            3 -> {
                print("Enter account number: ")
                val accNo = readlnOrNull() ?: ""
                val account = bankSystem.findAccount(accNo)

                if (account != null) {
                    print("Enter amount to withdraw: ")
                    val amount = readlnOrNull()?.toDoubleOrNull() ?: 0.0
                    Withdraw(account).execute(amount)
                } else {
                    println("Error: Account not found.")
                }
            }

            4 -> bankSystem.displayAccounts()

            5 -> {
                println("Goodbye!")
                running = false
            }

            else -> println("Invalid option. Please try again.")
        }
    }
}