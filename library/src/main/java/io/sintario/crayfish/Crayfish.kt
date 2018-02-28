package io.sintario.crayfish

import android.content.Context

object Crayfish {

    /**
     * Call this in [android.app.Application.onCreate] to create [CrayfishNest].
     */
    fun startNesting(context: Context, configure: (CrayfishNest.Builder) -> Unit = {}) = CrayfishNest(context, configure)
}