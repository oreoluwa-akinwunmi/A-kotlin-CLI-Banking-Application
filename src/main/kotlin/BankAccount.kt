import kotlin.random.Random

// -------------------- DATA CLASS --------------------
data class BankAccount(
    val accountNumber: String,
    val bankName: BankName,
    val firstName: String?,
    val lastName: String?,
    val fullName: String,
    var balance: Double = 0.0
)