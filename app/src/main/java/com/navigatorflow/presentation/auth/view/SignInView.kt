package com.navigatorflow.presentation.auth.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat.startActivity
import android.util.AttributeSet
import com.navigatorflow.domain.screen.auth.IntroScreen
import com.navigatorflow.domain.screen.auth.SignUpScreen
import com.navigatorflow.presentation.main.MainActivity
import flow.Flow
import kotlinx.android.synthetic.main.view_sign_in.view.*

class SignInView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        signInButton.setOnClickListener({
            context.startActivity(Intent(context, MainActivity::class.java))
        })
        gotoRegButton.setOnClickListener({
            Flow.get(this).set(SignUpScreen())
        })
    }
}