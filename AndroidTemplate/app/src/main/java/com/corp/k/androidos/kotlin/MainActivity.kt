package com.corp.k.androidos.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.Toast
import com.corp.k.androidos.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val floatingActionButton = findViewById<FloatingActionButton> (R.id.floatingActionButton);
        floatingActionButton.hide();
        floatingActionButton.show();
        floatingActionButton.setOnClickListener({
            Toast.makeText(applicationContext, "Touch", Toast.LENGTH_SHORT).show()
        })
//         Example of a call to a native method
//        sample_text.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
