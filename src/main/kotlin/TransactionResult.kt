// -------------------- SEALED CLASS --------------------
sealed class TransactionResult {
    data class Success(val message: String) : TransactionResult()
    data class Error(val message: String) : TransactionResult()
}