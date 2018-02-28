package io.sintario.crayfish.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Case(
        @PrimaryKey var name: String = "",
        var variant: Int = DEFAULT_VARIANT,
        var metadata: String? = null
) : RealmObject() {
    companion object {
        const val DEFAULT_VARIANT = -1
    }
}
