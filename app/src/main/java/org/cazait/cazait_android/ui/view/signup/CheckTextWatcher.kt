package org.cazait.cazait_android.ui.view.signup

import android.text.Editable
import android.text.TextWatcher

abstract class CheckTextWatcher : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    abstract fun checkText(text: String)
    abstract fun checkFlag()

    override fun afterTextChanged(editText: Editable?) {
        if (editText != null) {
            checkText(editText.toString())
            checkFlag()
        }
    }
}