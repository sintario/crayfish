package io.sintario.crayfish.sample

import io.reactivex.Flowable
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class TestApiClient {
    fun fetch(caseName: String): Flowable<CaseEntity> = Flowable.fromCallable { RandomCaseFactory.generateCase(caseName) }

    fun fetchAll(caseNames: List<String>): Flowable<List<CaseEntity>> = Flowable.fromCallable {
        caseNames.map(RandomCaseFactory::generateCase)
    }

    object RandomCaseFactory {
        fun generateCase(caseName: String) = CaseEntity(caseName).apply {
            variant = (1..4).shuffled().first()
            metadata = generateMetadata()

        }

        private fun generateMetadata(): JSONObject? = when (Random().nextInt() % 3) {
            0 -> null
            1 -> JSONObject().apply {
                val array = JSONArray().apply {
                    put(1)
                    put(10)
                    put(0)
                }
                put("array", array)
            }
            else -> JSONObject().apply {
                put("foo", 1)
                put("bar", "bot")
            }
        }
    }
}
}