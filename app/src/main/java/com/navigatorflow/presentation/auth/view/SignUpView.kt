package com.navigatorflow.presentation.auth.view

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.navigatorflow.domain.screen.auth.SignInScreen
import com.navigatorflow.domain.screen.auth.SignUpScreen
import com.navigatorflow.presentation.main.MainActivity
import flow.Flow
import kotlinx.android.synthetic.main.view_intro.view.*
import kotlinx.android.synthetic.main.view_sign_up.view.*

class SignUpView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        backButton.setOnClickListener({
            Flow.get(this).goBack()
        })
        signUpButton.setOnClickListener({
            context.startActivity(Intent(context, MainActivity::class.java))
        })
    }
}