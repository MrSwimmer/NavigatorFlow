package com.navigatorflow.presentation.auth.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.navigatorflow.domain.screen.auth.SignInScreen
import com.navigatorflow.domain.screen.auth.SignUpScreen
import flow.Direction
import flow.Flow
import kotlinx.android.synthetic.main.view_intro.view.*

class IntroView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        helloButton.setOnClickListener({
            Flow.get(this).replaceTop(SignInScreen(), Direction.REPLACE)
        })
    }
}