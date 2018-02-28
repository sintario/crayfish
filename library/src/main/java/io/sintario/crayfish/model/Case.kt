package io.sintario.crayfish.model

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.json.JSONException
import org.json.JSONObject

open class Case(
        @PrimaryKey var name: String = "",
        var variant: Int = DEFAULT_VARIANT,
        var metadata: String? = null
) : RealmObject() {
    constructor(name: String, variant: Int, metadata: JSONObject) : this(name, variant, metadata.toString())

    val metadataAsJSONObject: JSONObject?
        get() = try {
            JSONObject(metadata)
        } catch (e: JSONException) {
            null
        }

    companion object {
        const val DEFAULT_VARIANT = -1

        private const val PK_NAME = "name"

        @JvmStatic
        fun find(realm: Realm, caseName: String): Case? = realm.where(Case::class.java).equalTo(PK_NAME, caseName).findFirst()

        @JvmStatic
        fun findAll(realm: Realm, caseNames: List<String>): List<Case> {
            val nameArray = caseNames.toTypedArray()
            return realm.where(Case::class.java).`in`(PK_NAME, nameArray).findAll()
        }
    }
}
