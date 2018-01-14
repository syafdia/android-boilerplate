package com.github.syafdia.androidboilerplate.feature.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.github.syafdia.androidboilerplate.feature.BaseActivity
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.databinding.ActivityDashboardBinding
import com.github.syafdia.androidboilerplate.databinding.NavHeaderDashboardBinding
import com.github.syafdia.androidboilerplate.feature.login.LoginActivity
import com.github.syafdia.androidboilerplate.feature.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject
import android.content.Intent


class DashboardActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, DashboardNavigator {

    override val viewId = R.layout.activity_dashboard

    @Inject
    lateinit var viewModelFactory: DashboardViewModelFactory

    lateinit var viewModel: DashboardViewModel

    lateinit var alertDialogLogOut: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Model
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(DashboardViewModel::class.java)

        viewModel.navigator = this

        // Data Binding
        val viewDataBinding = DataBindingUtil.setContentView<ActivityDashboardBinding>(this, viewId)
        val navHeaderDataBinding = NavHeaderDashboardBinding.bind(
                viewDataBinding.navViewDashboard.getHeaderView(0))

        viewDataBinding.viewModel = viewModel
        navHeaderDataBinding.viewModel = viewModel

        initializeUi()
    }

    override fun onResume() {
        super.onResume()

        navView_dashboard.setCheckedItem(R.id.item_dashboard_navDashboard)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_dashboard_navFeature1 -> {
                // TODO
            }
            R.id.item_dashboard_navFeature2 -> {
                // TODO
            }
            R.id.item_dashboard_navSetting -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
            R.id.item_dashboard_navLogout -> {
                alertDialogLogOut.show()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)
        finish()
    }

    private fun initializeUi() {
        setSupportActionBar(toolbar_dashboard)

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar_dashboard,
                R.string.dashboard_navDrawerOpen,
                R.string.dashboard_navDrawerClose
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navView_dashboard.setNavigationItemSelectedListener(this)

        alertDialogLogOut = AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(R.string.dashboard_confirmLogOut)
                .setNegativeButton(getString(R.string.all_no).toUpperCase()) { dialog, _ -> dialog?.cancel() }
                .setPositiveButton(getString(R.string.all_yes).toUpperCase()) { dialog, _ -> run {
                    viewModel.logout()
                    dialog.cancel()
                }}
                .create()
    }
}
