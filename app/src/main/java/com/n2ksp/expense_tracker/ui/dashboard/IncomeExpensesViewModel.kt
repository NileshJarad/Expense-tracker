package com.n2ksp.expense_tracker.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.n2ksp.expense_tracker.data.model.CategoryInfoModelCreator
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.data.room.IncomeExpenseDBModel
import com.n2ksp.expense_tracker.utils.DateUtils
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList


class IncomeExpensesViewModel(application: Application) : AndroidViewModel(application) {

    var compositeDisposable = CompositeDisposable()
    private var repository = IncomeExpensesRepository(application)

    lateinit var list: MutableLiveData<ArrayList<IncomeExpenseModel>>


    fun addExpense(incomeExpense: IncomeExpenseModel) {
        val disposable: Disposable = Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.insert(incomeExpense = IncomeExpenseDBModel().apply {
                    categoryId = incomeExpense.categoryInfoModel.categoryId
                    amount = incomeExpense.amount
                    memo = incomeExpense.memo
                    date = Date(incomeExpense.date)
                })
            }

        compositeDisposable.add(disposable)

    }


    fun getListForDay(day: Int, month: Int) {
        val dates = DateUtils.getDatesForDay(day = day, month = month)
        val disposable: Disposable = Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe { repositoryInner ->

                val tempList = ArrayList<IncomeExpenseModel>()

                var data = repositoryInner.getAllEntriesForDate(dates.first.time, dates.second.time)

                data.forEach {
                    tempList.add(
                        IncomeExpenseModel(
                            categoryInfoModel = CategoryInfoModelCreator.mapToCategoryInfoModel(
                                repository.getSingleCategory(
                                    it.categoryId
                                )
                            ),
                            memo = it.memo ?: "",
                            amount = it.amount,
                            date = it.date.time
                        )
                    )
                }

                list.value = tempList
            }

        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}