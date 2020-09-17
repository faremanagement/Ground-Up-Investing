package com.faremanagement.groundupinvesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class InitialInvestmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_investment)

        val introLayout : View = findViewById(R.id.introLayout)
        val initialInvestmentLayout : View = findViewById(R.id.initialInvestmentLayout)
        val autoInvestLayout : View = findViewById(R.id.autoInvestLayout)
        val bankInfoLayout : View = findViewById(R.id.bankInfoLayout)
        val manualBankInfoLayout : View = findViewById(R.id.manualBankInfoLayout)
        val agreementLayout : View = findViewById(R.id.agreementLayout)

        introLayout.visibility = View.VISIBLE
        initialInvestmentLayout.visibility = View.GONE
        autoInvestLayout.visibility = View.GONE
        bankInfoLayout.visibility = View.GONE
        manualBankInfoLayout.visibility = View.GONE
        agreementLayout.visibility = View.GONE

        val firstName = intent.getStringExtra("firstName")
        val nameText : TextView = findViewById(R.id.nameText)
        if (firstName != null) {
            nameText.text = "$firstName, investing is a few screens away!"
        } else {
            nameText.text = "Investing is a few screens away!"
        }

        val finishButton : Button = findViewById(R.id.finishButton)
        finishButton.setOnClickListener {
            introLayout.visibility = View.GONE
            initialInvestmentLayout.visibility = View.VISIBLE
        }

        val nextButton : Button = findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            //TODO: check for invalid input
            initialInvestmentLayout.visibility = View.GONE
            autoInvestLayout.visibility = View.VISIBLE
        }

        val setUpButton : Button = findViewById(R.id.setUpButton)
        setUpButton.setOnClickListener {
            //TODO: do something here
        }

        val notNowText : TextView = findViewById(R.id.skipAutoInvest)
        notNowText.setOnClickListener {
            autoInvestLayout.visibility = View.GONE
            bankInfoLayout.visibility = View.VISIBLE
        }

        val logInButton : Button = findViewById(R.id.logIn)
        logInButton.setOnClickListener {
            //TODO: do something here
        }

        val logInManually : TextView = findViewById(R.id.logInManually)
        logInManually.setOnClickListener {
            bankInfoLayout.visibility = View.GONE
            manualBankInfoLayout.visibility = View.VISIBLE
        }

        val nextButton2 : Button = findViewById(R.id.nextButton2)
        nextButton2.setOnClickListener {
            //TODO: make sure box is checked
            manualBankInfoLayout.visibility = View.GONE
            agreementLayout.visibility = View.VISIBLE
        }

        val completeSignUp : Button = findViewById(R.id.completeSignUp)
        completeSignUp.setOnClickListener {
            nameText.text = "Welcome to Ground Up!"
            finishButton.text = "Go to Account"
            val initialContributionText : TextView = findViewById(R.id.initialContributionText)
            initialContributionText.text = "Your initial contribution should appear in your account within 5 days."
            agreementLayout.visibility = View.GONE
            introLayout.visibility = View.VISIBLE
            //TODO: have finishButton now lead to the account page
        }

        val moneyText : EditText = findViewById(R.id.moneyText)
        moneyText.addTextChangedListener(MoneyMask())
    }
}