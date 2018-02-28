package io.sintario.crayfish.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun reloadTest() {
        ColorCaseResolver.resolve { textView.setTextColor(resources.getColor(it.resId)) }
    }
}
