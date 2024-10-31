package com.example.numberlist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()

            // Kiểm tra giá trị đầu vào
            if (input.isEmpty() || input.toIntOrNull() == null || input.toInt() <= 0) {
                textViewError.text = "Vui lòng nhập một số nguyên dương hợp lệ"
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            } else {
                textViewError.visibility = TextView.GONE
            }

            val n = input.toInt()
            val resultList = mutableListOf<Int>()

            when {
                radioEven.isChecked -> {
                    for (i in 0..n step 2) {
                        resultList.add(i)
                    }
                }
                radioOdd.isChecked -> {
                    for (i in 1..n step 2) {
                        resultList.add(i)
                    }
                }
                radioSquare.isChecked -> {
                    var i = 0
                    while (i * i <= n) {
                        resultList.add(i * i)
                        i++
                    }
                }
                else -> {
                    textViewError.text = "Vui lòng chọn loại số cần hiển thị"
                    textViewError.visibility = TextView.VISIBLE
                    return@setOnClickListener
                }
            }

            // Hiển thị kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }
}
