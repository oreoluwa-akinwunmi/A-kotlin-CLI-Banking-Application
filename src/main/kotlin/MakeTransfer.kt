fun makeTransfer() {
    println("A fee of 10 naira will be deducted for every transfer made to a different account.")
    println("You will be charged 50 naira for every transfer of 10,000 and above.")

    // Sender details
    println("Enter your account number: ")
    val accNo = readlnOrNull()?.trim() ?: ""
    val account = bankSystem.findAccount(accNo)
    println("Select your bank:")
    BankName.entries.forEachIndexed { index, bank ->
        println("${index + 1}. $bank")
    }

    val senderChoice = readlnOrNull()?.toIntOrNull()
    val senderBank = BankName.entries.getOrNull((senderChoice ?: 0) - 1)

    if (senderBank == null) {
        println("Invalid bank selection.")
        return
    }

    if (account?.bankName != senderBank) {
        println(TransactionResult.Error("Account not found."))
        return
    }

    // Recipient details
    println("Enter recipient's account number: ")
    val recipientAcctNo = readlnOrNull()?.trim() ?: ""
    println("Select receiver bank:")
    BankName.entries.forEachIndexed { index, bank ->
        println("${index + 1}. $bank")
    }

    val receiverChoice = readlnOrNull()?.toIntOrNull()
    val receiverBank = BankName.entries.getOrNull((receiverChoice ?: 0) - 1)

    if (receiverBank == null) {
        println("Invalid bank selection.")
        return
    }
    val recipientAccount = bankSystem.findAccount(recipientAcctNo)

    if (recipientAccount?.bankName != receiverBank) {
        println(TransactionResult.Error("Account not found."))
        return
    }

    println("Enter amount to transfer: ")
    val amount = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    if (recipientAccount == account) {
        println(TransactionResult.Error("You cannot transfer to yourself"))
    } else {
        Transfer(account, recipientAccount).execute(amount)
    }
}
