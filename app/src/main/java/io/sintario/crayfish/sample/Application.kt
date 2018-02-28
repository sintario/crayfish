package io.sintario.crayfish.sample

import io.sintario.crayfish.Crayfish
import android.app.Application as AndroidApplication

class Application: AndroidApplication() {
    override fun onCreate() {
        super.onCreate()
        Crayfish.initialize(this)
    }
}