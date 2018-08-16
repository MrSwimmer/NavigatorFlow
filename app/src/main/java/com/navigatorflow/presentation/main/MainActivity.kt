package com.navigatorflow.presentation.main

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import com.navigatorflow.R
import com.navigatorflow.domain.flow.BasicKeyParceler
import com.navigatorflow.domain.flow.dispatcher.AuthDispatcher
import com.navigatorflow.domain.flow.dispatcher.MainDispatcher
import com.navigatorflow.domain.screen.auth.IntroScreen
import com.navigatorflow.domain.screen.main.NewsScreen
import com.navigatorflow.domain.screen.main.SettingsScreen
import flow.Direction
import flow.Dispatcher
import flow.Flow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDrawerContent()
        setSupportActionBar(toolbar)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        val header = navView.getHeaderView(0)
        header.shareButton.setOnClickListener({})
    }

    private fun setupDrawerContent() {
        navView.setCheckedItem(0)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_news -> Flow.get(this).replaceHistory(NewsScreen(), Direction.REPLACE)
                R.id.nav_settings -> Flow.get(this).replaceHistory(SettingsScreen(), Direction.REPLACE)
                else -> Flow.get(this).replaceHistory(NewsScreen(), Direction.REPLACE)
            }
            menuItem.isChecked = true
            title = menuItem.title
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        val baseContext = Flow.configure(newBase!!, this)
                .dispatcher(MainDispatcher(this))
                .defaultKey(NewsScreen())
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
