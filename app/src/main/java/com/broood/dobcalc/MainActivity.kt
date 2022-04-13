package com.broood.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePick: Button = findViewById(R.id.datepic)
        btnDatePick.setOnClickListener {
            clickDatePicker()
        }
        val minutes: TextView = findViewById(R.id.minutess)
        //Setting the birthdate default as my(@notbrood's) birthday xD
        val sdfTemp = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        minutes.text = (sdfTemp.parse(sdfTemp.format(System.currentTimeMillis()))!!.time/60000-sdfTemp.parse("12/08/2003")!!.time/60000).toString()
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dateE: TextView = findViewById(R.id.textView5)
        val minutes: TextView = findViewById(R.id.minutess)
        val dpd = DatePickerDialog(this, { _, SelectedYear, SelectedMonth, DayOfMonth ->

            val selectedDate = "$DayOfMonth/${SelectedMonth+1}/$SelectedYear"
            dateE.text = selectedDate //changes the date string.
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) //setting function as variable to make it easy

            //gives out theDate in format Sat Apr 13 00:00:00 GMT+05:30 1968
            val theDate = sdf.parse(selectedDate)

            //the time function returns time in milliseconds from 1 january, 1970 of the date given.
            val timeInMinutes = theDate!!.time/60000

            //gives out currentDate in format Sat Apr 13 00:00:00 GMT+05:30 1968
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            //the time function returns time in milliseconds from 1 january, 1970 of the current date given.
            val dateTillNow = currentDate!!.time/60000

            //subtracts so as to find relative time
            val finalTime = dateTillNow - timeInMinutes

            //converts the finalTime to String
            minutes.text = finalTime.toString()

        }, year, month, day)
        dpd.datePicker.maxDate=System.currentTimeMillis() - 86400 //sets the max date in the picker as current date
        dpd.show()
    }
}
