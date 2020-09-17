package com.faremanagement.groundupinvesting

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import java.util.*

class VerificationActivity : AppCompatActivity() {

    private val SPINNER_DEFAULT = "United States"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val verificationLayout : View = findViewById(R.id.verificationLayout)
        val countryLayout : View = findViewById(R.id.countryLayout)
        val addressLayout : View = findViewById(R.id.addressLayout)
        val confirmAddressLayout : View = findViewById(R.id.confirmAddressLayout)
        val phoneNumberLayout : View = findViewById(R.id.phoneNumberLayout)
        val DOBLayout : View = findViewById(R.id.DOBLayout)
        val socialSecurityLayout : View = findViewById(R.id.socialSecurityLayout)

        verificationLayout.visibility = View.VISIBLE
        countryLayout.visibility = View.GONE
        addressLayout.visibility = View.GONE
        confirmAddressLayout.visibility = View.GONE
        phoneNumberLayout.visibility = View.GONE
        DOBLayout.visibility = View.GONE
        socialSecurityLayout.visibility = View.GONE

        val nextButton : Button = findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            when {
                verificationLayout.visibility == View.VISIBLE -> {
                    verificationLayout.visibility = View.GONE
                    countryLayout.visibility = View.VISIBLE
                }
                countryLayout.visibility == View.VISIBLE -> {
                    countryLayout.visibility = View.GONE
                    addressLayout.visibility = View.VISIBLE
                }
                addressLayout.visibility == View.VISIBLE -> {
                    addressLayout.visibility = View.GONE
                    phoneNumberLayout.visibility = View.VISIBLE
                }
                confirmAddressLayout.visibility == View.VISIBLE -> {
                    confirmAddressLayout.visibility = View.GONE
                    phoneNumberLayout.visibility = View.VISIBLE
                }
                phoneNumberLayout.visibility == View.VISIBLE -> {
                    phoneNumberLayout.visibility = View.GONE
                    DOBLayout.visibility = View.VISIBLE
                }
                DOBLayout.visibility == View.VISIBLE -> {
                    DOBLayout.visibility = View.GONE
                    socialSecurityLayout.visibility = View.VISIBLE
                }
                socialSecurityLayout.visibility == View.VISIBLE -> {
                    val newIntent = Intent(intent)
                    newIntent.component = ComponentName(this, InitialInvestmentActivity::class.java)
                    startActivity(newIntent)
                }
            }
        }

        val citizenshipSpinner : Spinner = findViewById(R.id.citizenshipSpinner)
        val residencySpinner : Spinner = findViewById(R.id.residencySpinner)
        val countries = mutableListOf<String>()

        val countryCodes = Locale.getISOCountries()
        for (countryCode in countryCodes) {
            val locale = Locale("", countryCode)
            val countryName = locale.displayCountry
            if (!countries.contains(countryName) && countryName.isNotEmpty()) {
                countries.add(countryName)
            }
        }

        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, countries)
        citizenshipSpinner.adapter = adapter
        citizenshipSpinner.setSelection(adapter.getPosition(SPINNER_DEFAULT))
        residencySpinner.adapter = adapter
        residencySpinner.setSelection(adapter.getPosition(SPINNER_DEFAULT))

        //Add Input Masks for DOB, Phone, and SSN
        val socialEditText : EditText = findViewById(R.id.socialEditText)
        socialEditText.addTextChangedListener(SsnMask())
        val DOBEditText : EditText = findViewById(R.id.DOBEditText)
        DOBEditText.addTextChangedListener(DateMask())
        val phoneNumberEditText : EditText = findViewById(R.id.phoneNumberEditText)
        phoneNumberEditText.addTextChangedListener(PhoneNumberMask())

        val statesSpinner : Spinner = findViewById(R.id.statesSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.states,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            statesSpinner.adapter = adapter
        }
    }
}
