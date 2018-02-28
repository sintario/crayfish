package io.sintario.crayfish.sample

import io.sintario.crayfish.model.Case
import org.json.JSONObject

class CaseEntity(
        val name: String = "",
        var variant: Int = Case.DEFAULT_VARIANT,
        var metadata: JSONObject? = null
) {
    fun toCase() = Case(name, variant, metadata?.toString())
}
