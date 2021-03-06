package com.n2ksp.expense_tracker.di.component

import com.n2ksp.expense_tracker.di.module.AddIncomeExpenseAcitivtyModule
import com.n2ksp.expense_tracker.ui.income_expense.add_update.AddIncomeExpenseActivity
import dagger.Component

@Component(modules = [AddIncomeExpenseAcitivtyModule::class])
interface AddIncomeExpenseAcitivtyComponent {
    fun inject(activity: AddIncomeExpenseActivity)
}