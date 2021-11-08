package kst.app.fcdelivery.presentation

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT
}