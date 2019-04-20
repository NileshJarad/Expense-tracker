package com.n2ksp.expense_tracker.ui.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.n2ksp.expense_tracker.di.component.DaggerDashboardFragmentComponent
import com.n2ksp.expense_tracker.di.module.DashboardFragmentModule
import javax.inject.Inject


class DashboardFragment : Fragment() {


    @Inject
    lateinit var view: DashboardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            //            DashboardView(it as AppCompatActivity)
            DaggerDashboardFragmentComponent.builder()
                .dashboardFragmentModule(DashboardFragmentModule(activity = it as AppCompatActivity))
                .build()
                .inject(this)
        }
        return view
    }


}
