package com.faremanagement.groundupinvesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val intent = Intent(this, PermissionsActivity::class.java)

        val fullNameLayout : View = findViewById(R.id.fullName)
        val emailLayout : View = findViewById(R.id.getEmail)
        val passwordLayout : View = findViewById(R.id.getPassword)

        fullNameLayout.visibility = View.VISIBLE
        emailLayout.visibility = View.GONE
        passwordLayout.visibility = View.GONE

        val emailText : EditText = findViewById(R.id.email)
        val confirmEmailText : EditText = findViewById(R.id.confirmEmail)

        emailText.setOnFocusChangeListener { view, b ->
            run {
                if (!b) {
                    val emailError: TextView = findViewById(R.id.emailError)
                    if (!emailText.text.contains('@') || !emailText.text.contains('.')) {
                        emailError.text = "Please enter a valid email."
                        emailError.visibility = View.VISIBLE
                    } else {
                        emailError.visibility = View.GONE
                    }
                }
            }
        }

        confirmEmailText.setOnFocusChangeListener { view, b ->
            run {
                if (!b) {
                    val confirmEmailError : TextView = findViewById(R.id.confirmEmailError)
                    if (emailText.text.toString() != confirmEmailText.text.toString()) {
                        confirmEmailError.text = "Emails must match."
                        confirmEmailError.visibility = View.VISIBLE
                    } else {
                        confirmEmailError.visibility = View.GONE
                    }
                }
            }
        }

        val passwordView : EditText = findViewById(R.id.password)
        val confirmPasswordView : EditText = findViewById(R.id.confirmPassword)
        val showPassword : ImageButton = findViewById(R.id.showPassword)
        val showConfirmPassword : ImageButton = findViewById(R.id.showConfirmPassword)

        showPassword.setOnClickListener {
            if (passwordView.transformationMethod == PasswordTransformationMethod.getInstance()) {
                passwordView.transformationMethod = HideReturnsTransformationMethod.getInstance()
                passwordView.setSelection(passwordView.text.length)
            } else {
                passwordView.transformationMethod = PasswordTransformationMethod.getInstance()
                passwordView.setSelection(passwordView.text.length)
            }
        }
        showConfirmPassword.setOnClickListener {
            if (confirmPasswordView.transformationMethod == PasswordTransformationMethod.getInstance()) {
                confirmPasswordView.transformationMethod = HideReturnsTransformationMethod.getInstance()
                confirmPasswordView.setSelection(confirmPasswordView.text.length)
            } else {
                confirmPasswordView.transformationMethod = PasswordTransformationMethod.getInstance()
                confirmPasswordView.setSelection(confirmPasswordView.text.length)
            }
        }

        passwordView.setOnFocusChangeListener { view, b ->
            run {
                if (!b) {
                    val passwordError: TextView = findViewById(R.id.passwordError)
                    if (passwordView.text.length < 8) {
                        passwordError.text = "Password must contain at least 8 characters."
                        passwordError.visibility = View.VISIBLE
                    } else if (!passwordView.text.contains(Regex("[0-9]+"))) {
                        passwordError.text = "Password must contain at least one number."
                        passwordError.visibility = View.VISIBLE
                    } else if (!passwordView.text.contains(Regex("[a-z]+"))) {
                        passwordError.text = "Password must contain at least one letter."
                        passwordError.visibility = View.VISIBLE
                    } else {
                        passwordError.visibility = View.GONE
                    }
                }
            }
        }

        confirmPasswordView.setOnFocusChangeListener { view, b ->
            run {
                if (!b) {
                    val confirmPasswordError: TextView = findViewById(R.id.confirmPasswordError)
                    if (confirmPasswordView.text.toString() != passwordView.text.toString()) {
                        confirmPasswordError.text = "Passwords must match."
                        confirmPasswordError.visibility = View.VISIBLE
                    } else {
                        confirmPasswordError.visibility = View.GONE
                    }
                }
            }
        }

        val nameNextButton : Button = findViewById(R.id.nameNextButton)
        val emailNextButton : Button = findViewById(R.id.emailNextButton)
        val passwordNextButton : Button = findViewById(R.id.passwordNextButton)

        nameNextButton.setOnClickListener {
            val firstNameText : EditText = findViewById(R.id.getFirstName)
            val lastNameText : EditText = findViewById(R.id.getLastName)

            if (firstNameText.text.isNotEmpty() && lastNameText.text.isNotEmpty()) {
                intent.putExtra("firstName", firstNameText.text.toString())
                intent.putExtra("lastName", lastNameText.text.toString())
                fullNameLayout.visibility = View.GONE
                emailLayout.visibility = View.VISIBLE
            }
        }

        emailNextButton.setOnClickListener {
            if (emailText.text.isNotEmpty()) {
                if (emailText.text.contains('@') && emailText.text.contains('.')) {
                    if (emailText.text.toString() == confirmEmailText.text.toString()) {
                        intent.putExtra("email", emailText.text.toString())
                        emailLayout.visibility = View.GONE
                        passwordLayout.visibility = View.VISIBLE
                    }
                }
            }
        }

        passwordNextButton.setOnClickListener {
            if (passwordView.text.length >= 8) {
                if (passwordView.text.contains(Regex("[0-9]+"))
                    && passwordView.text.contains(Regex("[a-z]+"))) {
                    if (passwordView.text.toString() == confirmPasswordView.text.toString()) {
                        val intent = Intent(this, PermissionsActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        val fullNameLayout : View = findViewById(R.id.fullName)
        val emailLayout : View = findViewById(R.id.getEmail)
        val passwordLayout : View = findViewById(R.id.getPassword)

        when {
            fullNameLayout.visibility == View.VISIBLE -> {
                super.onBackPressed()
            }
            emailLayout.visibility == View.VISIBLE -> {
                emailLayout.visibility = View.GONE
                fullNameLayout.visibility = View.VISIBLE
            }
            passwordLayout.visibility == View.VISIBLE -> {
                passwordLayout.visibility = View.GONE
                emailLayout.visibility = View.VISIBLE
            }
        }
    }
}

//TODO: EditText Style
// Hide keyboard when EditText not in focus
// Fix cursor icon
// Back button in permissionsAcivity

