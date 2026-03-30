
val bankSystem = BankSystem()


// -------------------- MAIN FUNCTION --------------------
fun main() {
    var running = true

    while (running) {
        println("\n--- Banking System ---")
        println("1. Create Account")
        println("2. Deposit")
        println("3. Withdraw")
        println("4. Transfer")
        println("5. View Accounts")
        println("6. Exit")
        print("Choose option: ")

        // Using readlnOrNull() is the modern Kotlin way
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> createAccount()
            2 -> makeDeposit()
            3 -> withdrawal()
            4 -> makeTransfer()
            5 -> bankSystem.displayAccounts()
            6 -> {
                println("Goodbye!")
                running = false
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}