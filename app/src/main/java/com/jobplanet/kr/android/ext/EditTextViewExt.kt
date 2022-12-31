package com.jobplanet.kr.android.ext

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

@SuppressLint("ClickableViewAccessibility")
fun EditText.checkSearchWord(searchWord: (CharSequence?) -> Unit) {

    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(char: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(char: Editable?) {
            searchWord(char)
        }
    })
}