package com.n2ksp.expense_tracker.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.base.ETBaseActivity

class MainActivity : ETBaseActivity() {

    private lateinit var mainView: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = MainView(this)
        setContentView(mainView)
        val navController = Navigation.findNavController(this, R.id.navHostFragment)

        mainView.syncNavControllerWithView(navController)
    }

    override fun onBackPressed() {
        if (!mainView.closeNavigationViewIfItsOpen()) {
            super.onBackPressed()
        }
    }

    //
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}