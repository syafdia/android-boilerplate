package com.github.syafdia.androidboilerplate.feature.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.feature.BaseActivity
import com.github.syafdia.androidboilerplate.feature.dashboard.DashboardActivity
import com.github.syafdia.androidboilerplate.feature.login.LoginActivity
import com.github.syafdia.androidboilerplate.util.ext.showToast
import javax.inject.Inject


class SplashActivity : BaseActivity(), SplashNavigator {

    companion object {

        const val REQ_CODE_OVERLAY_PERMISSION = 9000

        const val REQ_CODE_GET_ALL_PERMISSIONS = 9001

        private val PERMISSIONS = listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE,

                Manifest.permission.ACCESS_FINE_LOCATION
        ).toTypedArray()
    }

    override val viewId = R.layout.activity_splash

    @Inject
    lateinit var splashViewModel: SplashViewModel

    private var retryMultiplePermissionsRequest = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.navigator = this

        if (!isOverlayPermissionGranted()) {
            requestOverlayPermission()
            return
        }

        if (!isMultiplePermissionsGranted()) {
            requestMultiplePermissions()
            return
        }

        splashViewModel.onGrantedPermissions()
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {

        when (requestCode) {
            REQ_CODE_GET_ALL_PERMISSIONS -> {
                if (isMultiplePermissionsGranted()) {
                    splashViewModel.onGrantedPermissions()
                    return
                }

                requestMultiplePermissions()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_CODE_OVERLAY_PERMISSION -> {
                if (isOverlayPermissionGranted()) {
                    requestMultiplePermissions()
                    return
                }

                showRequestPermissionsFailed()
            }
        }
    }

    override fun openDashboardActivity() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    override fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun requestOverlayPermission() {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + packageName))
        startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION)
    }

    private fun isOverlayPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(this)
        } else {
            true
        }
    }

    private fun requestMultiplePermissions() {

        if (isMultiplePermissionsGranted()) {
            splashViewModel.onGrantedPermissions()
            return
        }

        if (retryMultiplePermissionsRequest == 0) {
            showRequestPermissionsFailed()
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            retryMultiplePermissionsRequest--
            requestPermissions(PERMISSIONS, REQ_CODE_GET_ALL_PERMISSIONS)
        } else {
            retryMultiplePermissionsRequest = 0
        }
    }

    private fun isMultiplePermissionsGranted(): Boolean {
        return PERMISSIONS.none {
                    ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
                }
    }

    private fun showRequestPermissionsFailed() {
        showToast(getString(R.string.splash_permissionRequestsIgnored), Toast.LENGTH_LONG)
    }
}
