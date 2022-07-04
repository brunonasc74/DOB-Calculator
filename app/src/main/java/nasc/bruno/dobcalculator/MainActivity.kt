package nasc.bruno.dobcalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate: TextView? = null
    private var ageInMinutes: TextView? = null
    private var ageInHours: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.button)
        selectedDate = findViewById(R.id.selectedDate)
        ageInMinutes = findViewById(R.id.ageInMinutes)
        ageInHours = findViewById(R.id.ageInHours)

        btn.setOnClickListener { clickDate() }
    }

    private fun clickDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, vYear, vMonth, vDay ->
            val dateString = "$vDay/${vMonth + 1}/$vYear"
            selectedDate?.text = dateString

            val date = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val selectedDateInMinutes = (date.parse(dateString)?.time ?: 0) / 60000
            val currentDateInMinutes =  (date.parse(date.format(System.currentTimeMillis()))?.time ?: 0) / 60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            ageInMinutes?.text = differenceInMinutes.toString()

            val differenceInHours = differenceInMinutes / 60
            ageInHours?.text = differenceInHours.toString()
        }, year, month, day)
        dpd.datePicker.maxDate = (System.currentTimeMillis() - 86400000); dpd.show()
    }
}