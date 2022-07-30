package com.demo.gradiantcolordemo

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var etEnterName: TextInputEditText
    private var defaultText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tv_result)
        etEnterName = findViewById(R.id.et_enter_text)
        defaultText = tvResult.text.toString()

        onApplyGradientColorOnText(defaultText ?: getString(R.string.app_name))
        initListener()
    }

    private fun onApplyGradientColorOnText(text: String) {
        val purple = ContextCompat.getColor(this, R.color.purple_200)
        val teal = ContextCompat.getColor(this, R.color.teal_200)

        val spannableText = text.toSpannable()
        spannableText[0..text.length] = LinearGradientSpan(text, text, purple, teal)
        tvResult.text = spannableText
    }

    private fun initListener() {
        etEnterName.addTextChangedListener {
            val text = it.toString()
            val resultText = text.ifEmpty { defaultText.toString() }
            onApplyGradientColorOnText(resultText)
        }
    }

}