package com.hb.sharedpreference

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hb.securesharedpref.SecuredSharedPreference
import com.hb.sharedpreference.databinding.ActivityAesSampleBinding


class AESSampleActivity : BaseActivity() {

    lateinit var binding: ActivityAesSampleBinding

    lateinit var securePref: SecuredSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_aes_sample)

        securePref = SecuredSharedPreference(this, true, "app_pref")

        securePref.putString("first_name", "Mark")
        securePref.putBoolean("is_active", true)
        securePref.putInt("age", 28)
        securePref.putFloat("rating", 1.5f)
        securePref.putDouble("fee", 1500.00)
        securePref.putStringSet("categories", setOf("Dentist", "Dermatologist"))
        securePref.saveObject("patient", User("Android"))
        securePref.saveObjectsList("patients", listOf(User("Android"), User("Ios")))

        val firstName = securePref.getString("first_name", "")
        val isActive = securePref.getBoolean("is_active", false)
        val age = securePref.getInt("age", 0)
        val rating = securePref.getFloat("rating", 0.0f)
        val fee = securePref.getDouble("fee", 0.0)
        val categories = securePref.getStringSet("categories", null)
        val patient = securePref.getObject<User>("patient", null)
        val patients = securePref.getObjectsList<User>("patients", null)


        securePref.remove("patients")
        securePref.clear()
        val isContainPatients = securePref.containsKey("patients")

    }
}
