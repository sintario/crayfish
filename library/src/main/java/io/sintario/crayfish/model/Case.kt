package io.sintario.crayfish.model

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Case(
        @PrimaryKey var name: String = "",
        var variant: Int = DEFAULT_VARIANT,
        var metadata: String? = null
) : RealmObject() {
    companion object {
        private const val DEFAULT_VARIANT = -1

        @JvmStatic
        fun find(realm: Realm, caseName: String): Case? = realm.where(Case::class.java).equalTo("name", caseName).findFirst()
    }
}
