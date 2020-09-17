package com.faremanagement.groundupinvesting

import android.text.Editable
import android.text.TextWatcher

class DateMask : TextWatcher{
    private val MAX_LENGTH  = 8
    private val MIN_LENGTH = 2

    private var updatedText = ""
    private var editing = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        if (updatedText == text.toString() || editing) {
            return
        }

        val digits = text.toString().replace(Regex("[^0-9]"), "")

        when {
            digits.length <= MIN_LENGTH -> {
                updatedText = digits
                return
            }
            digits.length <= 4 -> {
                val first = digits.substring(0, 2)
                val second = digits.substring(2)
                updatedText = "$first/$second"
            }
            else -> {
                val first = digits.substring(0, 2)
                val second = digits.substring(2, 4)
                val third = if (digits.length > MAX_LENGTH) {
                    digits.substring(4, MAX_LENGTH)
                } else {
                    digits.substring(4)
                }
                updatedText = "$first/$second/$third"
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