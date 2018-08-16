package com.navigatorflow.presentation.auth

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.navigatorflow.R
import com.navigatorflow.domain.flow.dispatcher.AuthDispatcher
import com.navigatorflow.domain.flow.BasicKeyParceler
import com.navigatorflow.domain.screen.auth.IntroScreen
import flow.Flow

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override fun attachBaseContext(newBase: Context?) {
        val baseContext = Flow.configure(newBase!!, this)
                .dispatcher(AuthDispatcher(this))
                .defaultKey(IntroScreen())
                .keyParceler(BasicKeyParceler())
                .install()
        super.attachBaseContext(baseContext)
    }

    override fun onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed()
        }
    }
}