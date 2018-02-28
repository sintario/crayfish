package io.sintario.crayfish.sample

import io.reactivex.Flowable

interface ApiClient {
    fun fetch(caseName: String): Flowable<CaseEntity>
    fun fetchAll(caseNames: List<String>): Flowable<List<CaseEntity>>
}