package io.sintario.crayfish

import android.content.Context

object Crayfish {

    lateinit var nest: CrayfishNest
        private set

    /**
     * Call this in [android.app.Application.onCreate] to create [CrayfishNest].
     */
    fun startNesting(context: Context, configure: (CrayfishNest.Builder) -> Unit = {}) {
        nest = CrayfishNest(context, configure)
    }
}