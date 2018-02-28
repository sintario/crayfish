package io.sintario.crayfish.sample

import android.app.Activity
import android.app.Application
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.sintario.crayfish.Crayfish

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Crayfish.startNesting(this)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            val apiClient: ApiClient = TestApiClient()

            val disposables = CompositeDisposable()

            override fun onActivityPaused(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityResumed(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityStarted(activity: Activity?) {
                apiClient.fetchAll(SampleCase.values().map(SampleCase::caseName))
                        .doOnNext {
                            Crayfish.nest.updateOrInsert(it.map(CaseEntity::toCase))
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            when (activity) {
                                is MainActivity -> activity.reloadTest()
                            }
                        }.subscribe().let { disposables.add(it) }
            }

            override fun onActivityDestroyed(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityStopped(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}