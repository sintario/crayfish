package io.sintario.crayfish.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Case(
        @PrimaryKey val name: String,
        val variant: Int,
        val metadata: String? = null
) : RealmObject()
