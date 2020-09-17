package com.faremanagement.groundupinvesting

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class PermissionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        // if phone has fingerprint capability
        val fingerprintDialogue : View = findViewById(R.id.fingerprintDialogue)
        val notificationsDialogue : View = findViewById(R.id.notificationsDialogue)
        val emailVerificationDialogue : View = findViewById(R.id.emailVerificationDialogue)
        fingerprintDialogue.visibility = View.VISIBLE
        notificationsDialogue.visibility = View.GONE
        emailVerificationDialogue.visibility = View.GONE

        val enableFingerprint : Button = findViewById(R.id.fingerprint)
        val skipFingerprint : TextView = findViewById(R.id.skipFingerprint)

        enableFingerprint.setOnClickListener {
            //TODO: do something here
        }
        skipFingerprint.setOnClickListener {
            fingerprintDialogue.visibility = View.GONE
            notificationsDialogue.visibility = View.VISIBLE
        }

        val enableNotifications : Button = findViewById(R.id.notifications)
        val skipNotifications : TextView = findViewById(R.id.skipNotifs)

        enableNotifications.setOnClickListener {
            //TODO: do something here
        }
        skipNotifications.setOnClickListener {
            notificationsDialogue.visibility = View.GONE
            emailVerificationDialogue.visibility = View.VISIBLE
        }

        val resendEmail : TextView = findViewById(R.id.resendEmail)
        val wrongEmail : TextView = findViewById(R.id.wrongEmail)

        resendEmail.setOnClickListener {
            //TODO: do something here
        }
        wrongEmail.setOnClickListener {
            //TODO: do something here
            val newIntent = Intent(intent)
            newIntent.component = ComponentName(this, VerificationActivity::class.java)
            startActivity(newIntent)
        }

        //TODO:
        // override back button
        // make the keyboard disappear when an EditText is out of focus
    }
}
