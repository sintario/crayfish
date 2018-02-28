package io.sintario.crayfish.sample

import android.app.Application
import io.sintario.crayfish.Crayfish

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Crayfish.startNesting(this)
    }
}