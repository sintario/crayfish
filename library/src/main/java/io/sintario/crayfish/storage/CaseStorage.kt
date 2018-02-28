package io.sintario.crayfish.storage

import io.realm.Realm
import io.realm.RealmConfiguration
import io.sintario.crayfish.model.Case


class CaseStorage(private val configuration: RealmConfiguration) {
    val realm: Realm
      get() = Realm.getInstance(configuration)

    private fun transactionSync(transaction: (Realm) -> Unit) {
        realm.use { it.executeTransaction(transaction) }
    }

    private fun transactionAsync(transaction: (Realm) -> Unit) {
        realm.use { it.executeTransactionAsync(transaction) }
    }

    fun updateOrInsert(case: Case) {
        // TODO: stub
    }

    fun delete(case: Case) {
        // TODO: stub
    }

    fun delete(caseName: String) {
        // TODO: stub
    }

    fun retrieve(caseName: String, receiver: (Case?) -> Unit) {
        realm.use {
            val case = Case.find(it, caseName)
            receiver(case)
        }
    }
}
