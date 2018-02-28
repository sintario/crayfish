package io.sintario.crayfish.sample

import android.support.annotation.StringRes
import io.sintario.crayfish.Crayfish


object MessageCaseResolver {

    fun resolve(answer: (Message) -> Unit) {
        Crayfish.nest.retrieve(SampleCase.MESSAGE.caseName) {
            it ?: return@retrieve
            @StringRes val resId = when (it.variant) {
                1 -> R.string.case1
                2 -> R.string.case2
                3 -> R.string.case3
                4 -> R.string.case4
                else -> R.string.app_name
            }
            answer(Message(resId, it.toString()))
        }
    }

    data class Message(@StringRes val resId: Int = 0, val json: String = "")
}