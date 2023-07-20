package com.example.androidkt

data class Transaction(
    val bankName: String,
    val remark: String,
    val amount: String,
    val date: String,
    val isExpense: Boolean,
    val imageId: Int
)


object FakeData {
    val transactions = listOf(
        Transaction(
            "Green Bank",
            "Withdraw",
            "$800",
            "4d ago",
            true,
            R.drawable.img_bank_logo_green
        ),
        Transaction("Bitcoin", "Withdraw", "$800", "4d ago", true, R.drawable.img_bank_logo_2),
        Transaction(
            "Legendary Bank",
            "Withdraw",
            "$800",
            "4d ago",
            false,
            R.drawable.img_bank_logo_3
        ),
        Transaction("Green Bank", "Withdraw", "$800", "4d ago", true, R.drawable.img_bank_logo_2),
        Transaction("Green Bank", "Withdraw", "$800", "4d ago", false, R.drawable.img_bank_logo_3),
        Transaction("Green Bank", "Withdraw", "$800", "4d ago", true, R.drawable.img_bank_logo_2),
        Transaction("Green Bank", "Withdraw", "$800", "4d ago", false, R.drawable.img_bank_logo_3),
        Transaction("Green Bank", "Withdraw", "$800", "4d ago", true, R.drawable.img_bank_logo_2)
    )
}


