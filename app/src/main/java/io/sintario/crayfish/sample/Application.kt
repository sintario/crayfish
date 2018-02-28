package io.sintario.crayfish.sample

import io.sintario.crayfish.Crayfish
import io.sintario.crayfish.CrayfishNest
import android.app.Application as AndroidApplication

class Application: AndroidApplication() {
    private lateinit var crayfishNest: CrayfishNest

    override fun onCreate() {
        super.onCreate()
        crayfishNest = Crayfish.startNesting(this)
    }
}