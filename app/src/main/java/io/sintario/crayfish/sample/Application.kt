package io.sintario.crayfish.sample

import io.sintario.crayfish.Crayfish
import io.sintario.crayfish.storage.CaseStorage
import android.app.Application as AndroidApplication

class Application: AndroidApplication() {
    private lateinit var caseStorage: CaseStorage

    override fun onCreate() {
        super.onCreate()
        caseStorage = Crayfish.spawnStorage(this)
    }
}