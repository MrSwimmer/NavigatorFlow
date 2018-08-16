package com.navigatorflow.presentation.main.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import com.navigatorflow.domain.screen.auth.SignInScreen
import com.navigatorflow.domain.screen.auth.SignUpScreen
import com.navigatorflow.domain.screen.main.PostScreen
import flow.Flow
import kotlinx.android.synthetic.main.view_news.view.*

class NewsView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        postButton.setOnClickListener({
            Flow.get(this).set(PostScreen())
        })
    }
}