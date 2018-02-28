package io.sintario.crayfish.storage

import io.realm.Realm
import io.realm.RealmConfiguration


class CaseStorage(private val configuration: RealmConfiguration) {
    val realm: Realm
      get() = Realm.getInstance(configuration)

    fun transactionSync(transaction: (Realm) -> Unit) {
        realm.use { it.executeTransaction(transaction) }
    }

    fun transactionAsync(transaction: (Realm) -> Unit) {
        realm.use { it.executeTransactionAsync(transaction) }
    }
}
