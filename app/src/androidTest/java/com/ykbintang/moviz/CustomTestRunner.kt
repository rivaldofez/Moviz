package com.ykbintang.moviz

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner
import android.content.Context
import dagger.hilt.android.testing.HiltTestApplication


class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}