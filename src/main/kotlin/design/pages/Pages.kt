package design.pages

enum class Pages(val pageName: String) {
    DEFAULT("Default Mode"),
    EXPLAIN("Mnemonic Mode"),
    DEBUG("Debug Mode"),
    UNKNOWN("Encoded"),
    READING("Reading"),
}