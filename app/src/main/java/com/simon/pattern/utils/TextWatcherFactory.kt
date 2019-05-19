package com.simon.pattern.utils

import android.text.Editable
import android.text.TextWatcher

fun getDistinctWatcher(
    afterAction: (s: Editable?) -> Unit = {},
    beforeAction: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
    onChangeAction: (s: CharSequence?) -> Unit
) = object : TextWatcher {
    private var prevValue = ""

    override fun afterTextChanged(s: Editable?) {
        afterAction(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeAction(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val currentValue = s.toString().trim()
        if (prevValue == currentValue) return

        prevValue = currentValue

        onChangeAction(s)
    }
}
