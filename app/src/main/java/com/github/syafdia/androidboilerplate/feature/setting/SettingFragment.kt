package com.github.syafdia.androidboilerplate.feature.setting


import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.github.syafdia.androidboilerplate.R


class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}
