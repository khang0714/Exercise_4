package com.example.exercise_4

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.app.DatePickerDialog.OnDateSetListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPickDate.setOnClickListener {
            onClickDatePicker(it)
        }
    }

    private fun onClickDatePicker(view: View){
        //Calendar
        val calendar = Calendar.getInstance()
        val datePickYear = calendar.get(Calendar.YEAR)
        val datePickMonth = calendar.get(Calendar.MONTH)
        val datePickDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, onClickDatePickerListener(view, datePickYear), datePickYear, datePickMonth, datePickDay
        )

        datePickerDialog.show()
    }

    private fun onClickDatePickerListener(view: View, datePickYear: Int): OnDateSetListener {
        return OnDateSetListener{ view, year, month, day ->
            txtDOB.text = getString(R.string.date_of_birth) + day + "-" + (month + 1) + "-" + year

            val age = datePickYear - year
            val basicSaving = getMinBasicSaving(age)

            txtAge.text = getString(R.string.age) + " " + age
            txtMinSaving.text = getString(R.string.min_basic_saving) + " RM" + basicSaving.toDouble()
            txtAllowanceInvestment.text = getString(R.string.allowance_investment) + " RM" + (basicSaving * 0.3)
        }
    }

    private fun getMinBasicSaving(age: Int): Int{
        return when (age) {
            in 16..20 -> 5000
            in 21..25 -> 14000
            in 26..30 -> 29000
            in 31..35 -> 50000
            in 36..40 -> 78000
            in 41..45 -> 116000
            in 46..50 -> 165000
            in 51..55 -> 228000

            else -> 0
        }
    }

}
