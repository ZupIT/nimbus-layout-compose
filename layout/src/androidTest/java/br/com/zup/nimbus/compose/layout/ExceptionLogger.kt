package br.com.zup.nimbus.compose.layout

import br.com.zup.nimbus.core.log.LogLevel
import br.com.zup.nimbus.core.log.Logger

class ExceptionLogger: Logger {
    override fun disable() {
        throw Error("Can't disable logging during testing.")
    }

    override fun enable() {}

    override fun error(message: String) {
        log(message, LogLevel.Error)
    }

    override fun info(message: String) {
        log(message, LogLevel.Info)
    }

    override fun log(message: String, level: LogLevel) {
        println("NIMBUS LOG [$level]: $message")
        if (level == LogLevel.Error) throw Error(message)
    }

    override fun warn(message: String) {
        log(message, LogLevel.Warning)
    }
}