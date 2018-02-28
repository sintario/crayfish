package io.sintario.crayfish

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import io.sintario.crayfish.model.Case
import io.sintario.crayfish.model.CaseModule


class CrayfishNest(context: Context, configure: (Builder) -> Unit = {}) {
    var realmConfiguration: RealmConfiguration

    init {
        Realm.init(context)
        val builder = Builder().apply(configure)
        realmConfiguration = RealmConfiguration.Builder().apply {
            name(builder.filename)
            modules(CaseModule())
            schemaVersion(builder.schemaVersion)
            deleteRealmIfMigrationNeeded()
        }.build()
    }

    companion object {
        private const val DB_NAME = "io.sintario.crayfish"
        private const val SCHEMA_VERSION = 1L
    }

    class Builder {
        var filename: String = DB_NAME
        var schemaVersion: Long = SCHEMA_VERSION
    }

    val realm: Realm
        get() = Realm.getInstance(realmConfiguration)

    fun updateOrInsert(case: Case, synchronously: Boolean = true) {
        updateOrInsert(listOf(case), synchronously)
    }

    fun updateOrInsert(cases: List<Case>, synchronously: Boolean = true) {
        if (synchronously) {
            transactionSync { it.insertOrUpdate(cases) }
        } else {
            transactionAsync { it.insertOrUpdate(cases) }
        }
    }

    fun delete(case: Case, synchronously: Boolean = true) {
        val caseName = case.name
        delete(caseName, synchronously)
    }

    fun delete(caseName: String, synchronously: Boolean = true) {
        fun deleteFrom(realm: Realm) = Case.find(realm, caseName)?.deleteFromRealm()

        if (synchronously) {
            transactionSync { deleteFrom(it) }
        } else {
            transactionAsync { deleteFrom(it) }
        }
    }

    fun retrieve(caseName: String, receiver: (Case?) -> Unit) {
        realm.use { instantRealm ->
            val managedCase = Case.find(instantRealm, caseName)
            val unmanagedCase = managedCase?.let { instantRealm.copyFromRealm(managedCase) }
            receiver(unmanagedCase)
        }
    }

    fun retrieve(caseNames: List<String>, receiver: (List<Case>) -> Unit) {
        realm.use { instantRealm ->
            val managedCases = Case.findAll(instantRealm, caseNames)
            val unmanagedCases = instantRealm.copyFromRealm(managedCases)
            receiver(unmanagedCases)
        }
    }

    private fun transactionSync(transaction: (Realm) -> Unit) {
        realm.use { it.executeTransaction(transaction) }
    }

    private fun transactionAsync(transaction: (Realm) -> Unit) {
        realm.use { it.executeTransactionAsync(transaction) }
    }
}
