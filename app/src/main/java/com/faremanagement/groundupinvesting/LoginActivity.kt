package com.faremanagement.groundupinvesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

/** Initial and Login Activity. */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val welcome : View = findViewById(R.id.welcomeScreen)
        val login : View = findViewById(R.id.login)
        welcome.bringToFront()
        welcome.visibility = View.VISIBLE
        login.visibility = View.GONE

        //Process something here?

        login.visibility = View.VISIBLE
        welcome.visibility = View.GONE


        val loginButton : Button = findViewById(R.id.goLogin)
        val signUpButton : Button = findViewById(R.id.signUp)
        val emailView : EditText = findViewById(R.id.getLastName)
        val passwordView : EditText = findViewById(R.id.getPassword)
        val showPassword : ImageButton = findViewById(R.id.showPassword)

        showPassword.setOnClickListener {
            if (passwordView.transformationMethod == PasswordTransformationMethod.getInstance()) {
                passwordView.transformationMethod = HideReturnsTransformationMethod.getInstance()
                passwordView.setSelection(passwordView.text.length)
            } else {
                passwordView.transformationMethod = PasswordTransformationMethod.getInstance()
                passwordView.setSelection(passwordView.text.length)
            }
        }

        loginButton.setOnClickListener {
            val email = emailView.text
            val password = passwordView.text

            if (email.isNotEmpty() && password.isNotEmpty()) {
                //Check email & password in database
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        signUpButton.setOnClickListener {
            /*val intent = Intent(this, VerificationActivity::class.java)
            startActivity(intent)*/
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}
