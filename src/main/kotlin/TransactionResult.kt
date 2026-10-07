// -------------------- SEALED CLASS --------------------
sealed class TransactionResult {
    data class Success(val message: String) : TransactionResult() {
        override fun toString(): String = message
    }
    data class Error(val message: String) : TransactionResult() {
        override fun toString(): String = message
    }
}