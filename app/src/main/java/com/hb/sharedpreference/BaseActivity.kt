package com.hb.sharedpreference

import android.app.ProgressDialog
import android.graphics.PorterDuff
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException

open class BaseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    override fun onStart() {
        super.onStart()
        val decor = getWindow().getDecorView()
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    override fun onResume() {
        Log.d("onResume", "onResume")
        super.onResume()
    }

    open fun showProgress() {
        if (progressDialog != null && progressDialog!!.isShowing()) {
            return
        }
        progressDialog = ProgressDialog(this)
        progressDialog!!.setCancelable(false)
        try {
            progressDialog!!.setMessage(
                "Please Wait..."
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        progressDialog!!.show()
        val progressbar =
            progressDialog!!.findViewById(android.R.id.progress) as ProgressBar
        progressbar.indeterminateDrawable.setColorFilter(
            resources.getColor(R.color.progress_bar_color),
            PorterDuff.Mode.SRC_IN
        )
    }

    open fun hideProgress() {
        try {
            if (progressDialog != null) {
                if (progressDialog!!.isShowing) {
                    progressDialog!!.dismiss()
                }
                progressDialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    override fun onPause() {
        Log.d("onPause", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("onStop", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "onRestart")
    }
}
