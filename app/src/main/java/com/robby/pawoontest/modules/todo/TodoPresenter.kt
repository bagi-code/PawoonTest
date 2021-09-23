package com.robby.pawoontest.modules.todo

import com.robby.pawoontest.domain.TodoDao
import com.robby.pawoontest.domain.model.TodoModel
import com.robby.pawoontest.network.HttpClient
import com.robby.pawoontest.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TodoPresenter (private val view: TodoContract.View, private val todoDao: TodoDao) : TodoContract.Presenter {
    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getTodoList() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.todoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    view.onTodoSuccess(it.orNullArrayListClean({it.clean()}, TodoModel()))
                },
                {
                    view.dismissLoading()
                    view.onTodoFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun getTodoListOffline() {
        view.showLoading()
        val disposable = io.reactivex.Observable.fromCallable {todoDao.getTodoAll()}
                .subscribeOn(Schedulers.computation())
                .subscribe(
                    {
                        view.dismissLoading()
                        view.onTodoOffline(it)
                    }
                )
        mCompositeDisposable!!.add(disposable)
    }

    override fun insertTodoAll(todoModel: List<TodoModel>) {
        mCompositeDisposable!!.add(io.reactivex.Observable.fromCallable {todoDao.insertTodoAll(todoModel)}
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }

    override fun insertTodo(todoModel: TodoModel){
        mCompositeDisposable!!.add(io.reactivex.Observable.fromCallable {todoDao.insertTodo(todoModel)}
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }

    override fun deleteTodo(todoModel: TodoModel) {
        mCompositeDisposable!!.add(io.reactivex.Observable.fromCallable {todoDao.deleteTodo(todoModel)}
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }

    override fun updateTodo(todoModel: TodoModel) {
        mCompositeDisposable!!.add(io.reactivex.Observable.fromCallable {todoDao.updateTodo(todoModel)}
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

    inline fun <T, reified R> ArrayList<T>.orNullArrayListClean(transform: (T) -> R, targetClass: R): ArrayList<R> {
        val arrayListOf = arrayListOf<R>()
        this.forEach { arrayListOf.add(it?.let { transform(it) } ?: targetClass) }
        return arrayListOf
    }
}