
package com.example.firstandroidui

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RadioActivity : AppCompatActivity() {
    private val radioBtnPartTime: MaterialRadioButton by lazy {
        findViewById(R.id.radio_part_time)
    }
    private val radioBtnFreelance: MaterialRadioButton by lazy {
        findViewById(R.id.radio_freelance)
    }
    private val toggleButtonCanApply: ToggleButton by lazy {
        findViewById(R.id.toggleButton)
    }
    private val switchCanApply: Switch by lazy {
        findViewById(R.id.switch_is_open_to_work)
    }
    private val fabSubmit: FloatingActionButton by lazy {
        findViewById(R.id.fab_submit)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio)

        setupRadioButtonClicked()
        fabSubmit.setOnClickListener {
            val message = if (validateUserPreference()) "User has selected Preference" else "Please Select Preference"
            Toast.makeText(this, message, Toast.LENGTH_SHORT ).show()
        }

        toggleButtonCanApply.setOnCheckedChangeListener { _, isChecked ->
            modifyRadioState(isChecked)
        }

        switchCanApply.setOnCheckedChangeListener { _, isChecked ->
            modifyRadioState(isChecked)
        }
    }

    private fun setupRadioButtonClicked() {
        radioBtnPartTime.setOnCheckedChangeListener { _,isChecked ->
            radioBtnFreelance.isChecked = !isChecked
            println("RadioButton1 clicked")
        }
        radioBtnFreelance.setOnCheckedChangeListener { _, isChecked ->
            radioBtnPartTime.isChecked = !isChecked
        }
    }

    private fun validateUserPreference() = (radioBtnPartTime.isChecked || radioBtnFreelance.isChecked)

    private fun modifyRadioState(isChecked: Boolean) {
        radioBtnPartTime.isEnabled = isChecked
        radioBtnFreelance.isEnabled = isChecked
        if (!isChecked) {
            radioBtnPartTime.isChecked = false
            radioBtnFreelance.isChecked = false
        }
    }
}
