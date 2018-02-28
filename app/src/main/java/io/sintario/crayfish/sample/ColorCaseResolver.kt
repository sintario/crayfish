package io.sintario.crayfish.sample

import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import io.sintario.crayfish.Crayfish

object ColorCaseResolver {
    fun resolve(answer: (Color) -> Unit) {
        Crayfish.nest.retrieve(SampleCase.Color.caseName) {
            it ?: return@retrieve
            @ColorRes val colorId = when (it.variant) {
                1 -> R.color.magenta
                2 -> R.color.blue
                3 -> R.color.red
                4 -> R.color.green
                else -> R.color.primary_material_dark
            }
            answer(Color(colorId))
        }
    }

    data class Color(@ColorRes val resId: Int = 0)
}