package br.com.zup.nimbus.compose.layout.utils

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    val isLeft: Boolean get() = this is Left<L>
    val isRight: Boolean get() = this is Right<R>
}
