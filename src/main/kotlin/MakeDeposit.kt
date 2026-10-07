fun makeDeposit() {
    println("Select Bank: ")
    BankName.entries.forEachIndexed { index, bank ->
        println("${index + 1}. $bank")
    }

    val choice = readlnOrNull()?.toIntOrNull()
    val selectedBank = BankName.entries.getOrNull((choice ?: 0) - 1)

    if (selectedBank == null) {
        println("Invalid bank selection.")
        return
    }

    print("Enter account number: ")
    val accNo = readlnOrNull() ?: ""
    val account = bankSystem.findAccount(accNo)

    if (account?.bankName != selectedBank) {
        println("Account Number: $accNo")
        println("Bank: $selectedBank")
        println(TransactionResult.Error("Account not found."))
    } else {
        println("Minimum deposit amount is ${formatMoney(100.00)}")
        print("Enter amount to deposit: ")
        val amount = readlnOrNull()?.toDoubleOrNull() ?: 0.00
        Deposit(account).execute(amount)
    }
}