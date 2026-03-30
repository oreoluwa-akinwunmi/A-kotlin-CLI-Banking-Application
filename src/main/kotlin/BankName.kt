enum class BankName(val displayName: String) {
    ACCESS("Access Bank"),
    ZENITH("Zenith Bank"),
    FIRSTBANK("First Bank"),
    GTBANK("GTBank"),
    UBA("UBA");

    override fun toString(): String {
        return displayName
    }
}