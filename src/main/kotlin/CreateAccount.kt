fun createAccount() {
    println("Select a bank:")
    BankName.entries.forEachIndexed { index, bank ->
        println("${index + 1}. $bank")
    }

    val choice = readlnOrNull()?.toIntOrNull()

    if (choice != null && choice in 1..BankName.entries.size) {

        val selectedBank = BankName.entries[choice - 1]
        println("You selected: ${selectedBank.displayName}")

        print("Enter first name: ")
        val firstName = readlnOrNull()?.trim()

        print("Enter last name: ")
        val lastName = readlnOrNull()?.trim()

        if (firstName.isNullOrBlank() || lastName.isNullOrBlank()) {
            println(TransactionResult.Error("First name and last name cannot be empty."))
        } else {
            val account = bankSystem.createAccount(firstName, lastName, selectedBank)

            println(
                TransactionResult.Success(
                    "Account created! Your account number is ${account.accountNumber}, ${account.bankName}"
                )
            )
        }

    } else {
        println("Invalid bank selection.")
    }
}