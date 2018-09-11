package com.inc.silence.weather.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.inc.silence.weather.R
import com.inc.silence.weather.extension.inTansaction
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
                R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    abstract fun fragment() : BaseFragment

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.inTansaction { add(
                    R.id.fragmentContainer, fragment()
            ) }
}