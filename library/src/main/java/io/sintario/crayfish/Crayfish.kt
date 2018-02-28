package io.sintario.crayfish

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import io.sintario.crayfish.model.CaseModule
import io.sintario.crayfish.storage.CaseStorage

object Crayfish {
    private const val DB_NAME = "io.sintario.crayfish"

    /**
     * Call this in [android.app.Application.onCreate] to spawnStorage
     */
    fun spawnStorage(context: Context): CaseStorage {
        Realm.init(context)
        val configuration = configureRealm()
        return CaseStorage(configuration)
    }

    private fun configureRealm(): RealmConfiguration = RealmConfiguration.Builder().apply {
        name(DB_NAME)
        modules(CaseModule())
        deleteRealmIfMigrationNeeded()
    }.build()
}