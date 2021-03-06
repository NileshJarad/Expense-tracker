package com.n2ksp.expense_tracker.ui.income_expense

import android.app.Application
import com.n2ksp.expense_tracker.data.room.*

class IncomeExpensesRepository(application: Application) {
    private var incomeExpenseDao: IncomeExpenseDao = AppDatabase.getAppDatabase(application).incomeExpenseDao()
    private var categoryDao: CategoryDao = AppDatabase.getAppDatabase(application).categoryDao()

    fun insert(incomeExpense: IncomeExpenseDBModel) {
        incomeExpenseDao.insert(incomeExpense)
    }

    fun update(incomeExpense: IncomeExpenseDBModel) {
        incomeExpenseDao.update(incomeExpense)
    }

    fun delete(incomeExpense: IncomeExpenseDBModel) {
        incomeExpenseDao.delete(incomeExpense)
    }

    fun getAllEntriesForDate(startDate: Long, endDate: Long): List<IncomeExpenseDBModel> {
        return incomeExpenseDao.findEntriesForDate(startDate, endDate)
    }

    fun getAll(): List<IncomeExpenseDBModel> {
        return incomeExpenseDao.getAll()
    }

    fun getSingleCategory(id: Int): CategoryDBModel {
        return categoryDao.findById(id)
    }

    fun getTotalForMonthUsingType(timeStartMonth: Long, timeEndMonth: Long, type: String): Float {
        return incomeExpenseDao.getTotalForMonthByType(timeStartMonth, timeEndMonth, type)
    }

    fun getEntry(id: Int): IncomeExpenseDBModel {
        return  incomeExpenseDao.getEntry(id)
    }

}
