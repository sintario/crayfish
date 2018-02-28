package io.sintario.crayfish

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import io.sintario.crayfish.model.CaseModule

object Crayfish {
    private const val DB_NAME = "io.sintario.crayfish"

    /**
     * Call this in [android.app.Application.onCreate] to initialize
     */
    fun initialize(context: Context) {
        Realm.init(context)
        configureRealm()
    }

    private fun configureRealm() {
        RealmConfiguration.Builder().apply {
            name(DB_NAME)
            modules(CaseModule())
            deleteRealmIfMigrationNeeded()
        }.build()
    }
}