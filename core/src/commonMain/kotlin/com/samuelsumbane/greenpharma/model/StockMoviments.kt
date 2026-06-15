package com.samuelsumbane.greenpharma.model

data class StockMoviments(
    val id: String,
    val productId: String,
    val loteId: String,
    val employeeId: String,
    val type: StockMovimentType,
    val quantity: Int,
    val previousStock: Int,
    val forwardStock: Int,
    val referenceId: String,
    val referenceType: ReferenceType,
    val observations: String
)

enum class StockMovimentType(val stringValue: String) {
    Entry("Entrada"),
    Exit("Saída"),
    Adjustment("Ajuste"),
    Return("Devolução")
}

enum class ReferenceType(val stringValue: String) {
    Sale("Venda"),
    PurchaseOrder("Ordem compra")
}
