package com.korinver.maxbox

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class CustomPointer : ConstraintLayout {

    constructor(context: Context) : super(context) {
        View.inflate(context, R.layout.custom_pointer, this)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        View.inflate(context, R.layout.custom_pointer, this)
    }

    init {

    }
}