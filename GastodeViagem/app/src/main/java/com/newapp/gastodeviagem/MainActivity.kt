package com.newapp.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calcular.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.btn_calcular) {
            calcular()
        }
    }

    private fun calcular() {
        if (validationOk()) {
            try {
                val distance = editDistance.text.toString().toFloat()
                val price = editPrice.text.toString().toFloat()
                val autonomy = editAutonomy.text.toString().toFloat()

                val totalValue = (distance * price) / autonomy
                textTotal.text = "R$ ${"%.2f".format(totalValue)}"
            } catch (nfe: NumberFormatException) {
                Toast.makeText(this, getString(R.string.informe_valores_validos), Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            Toast.makeText(this, getString(R.string.preencher_todos_campos), Toast.LENGTH_LONG)
                .show() // mostra um pop-up na tela por um tempo
        }
    }

    private fun validationOk(): Boolean {
        return (editDistance.text.toString().isNotEmpty() && editPrice.text.toString()
            .isNotEmpty() && editAutonomy.text.toString().isNotEmpty())
    }
}