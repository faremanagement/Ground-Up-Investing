package com.faremanagement.groundupinvesting

import android.text.Editable
import android.text.TextWatcher

class MoneyMask : TextWatcher {
    private val MAX_LENGTH  = 8
    private val MIN_LENGTH = 3

    private var updatedText = ""
    private var editing = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        if (updatedText == text.toString() || editing) {
            return
        }

        var digits = text.toString().replace(Regex("[^0-9]"), "")
        while (digits.length < MIN_LENGTH) {
            digits = "0$digits"
        }
        while (digits.length > MIN_LENGTH && digits[0] == '0') {
            digits = digits.substring(1)
        }

        when {
            digits.length <= 5 -> {
                val first = digits.substring(0, digits.length - 2)
                val second = digits.substring(digits.length - 2)
                updatedText = "$$first.$second"
            }
            else -> {
                if (digits.length > MAX_LENGTH) {
                     digits = digits.substring(0, MAX_LENGTH)
                }
                val first = digits.substring(0, digits.length - 5)
                val second = digits.substring(digits.length - 5, digits.length - 2)
                val third = digits.substring(digits.length - 2)
                updatedText = "$$first,$second.$third"
            }
        }
    }

    override fun afterTextChanged(editable: Editable?) {
        if (editing) return

        editing = true

        editable?.clear()
        editable?.insert(0, updatedText)

        editing = false
    }
}