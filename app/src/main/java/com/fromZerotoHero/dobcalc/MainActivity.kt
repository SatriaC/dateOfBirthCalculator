package com.fromZerotoHero.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInYears: TextView? = null
//    private var tvAgeInMonths: TextView? = null
    private var tvAgeInDays: TextView? = null
    private var tvAgeInHours: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    private var tvAgeInSeconds: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.tv_birthdate)
        tvAgeInYears = findViewById(R.id.tv_in_years)
//        tvAgeInMonths = findViewById(R.id.tv_in_months)
        tvAgeInDays = findViewById(R.id.tv_in_days)
        tvAgeInHours = findViewById(R.id.tv_in_hours)
        tvAgeInMinutes = findViewById(R.id.tv_in_minutes)
        tvAgeInSeconds = findViewById(R.id.tv_in_seconds)
        val btnDatePicker: Button = findViewById(R.id.set_date)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Date picked", Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth-${selectedMonth + 1}-${selectedYear}"
                tvSelectedDate?.text = selectedDate

                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
                val theDate = dateFormat.parse(selectedDate)
                theDate?.let {
                    val selectedDateInYears = theDate.year
//                    val selectedDateInMonths = theDate.month
                    val selectedDateInDays = theDate.time / (3600000 * 24)
                    val selectedDateInHours = theDate.time / 3600000
                    val selectedDateInMinutes = theDate.time / 60000
                    val selectedDateInSeconds = theDate.time / 1000

                    val currentDate =
                        dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInYears = currentDate.year
//                        val currentDateInMonths = currentDate.month
                        val currentDateInDays = currentDate.time / (3600000 * 24)
                        val currentDateInHours = currentDate.time / 3600000
                        val currentDateInMinutes = currentDate.time / 60000
                        val currentDateInSeconds = currentDate.time / 1000
                        val differenceInYears = currentDateInYears - selectedDateInYears
//                        val differenceInMonths = currentDateInMonths - selectedDateInMonths
                        val differenceInDays = currentDateInDays - selectedDateInDays
                        val differenceInHours = currentDateInHours - selectedDateInHours
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        val differenceInSeconds = currentDateInSeconds - selectedDateInSeconds

                        tvAgeInYears?.text = differenceInYears.toString()
//                        tvAgeInMonths?.text = differenceInMonths.toString()
                        tvAgeInDays?.text = differenceInDays.toString()
                        tvAgeInHours?.text = differenceInHours.toString()
                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                        tvAgeInSeconds?.text = differenceInSeconds.toString()
                    }
                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}