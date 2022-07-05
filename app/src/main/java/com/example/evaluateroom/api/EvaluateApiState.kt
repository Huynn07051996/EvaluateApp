package com.example.evaluateroom.api

data class EvaluateApiState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): EvaluateApiState<T> {
            return EvaluateApiState(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String?): EvaluateApiState<T> {
            return EvaluateApiState(Status.ERROR, null, message)
        }

        fun <T> loading(): EvaluateApiState<T> {
            return EvaluateApiState(Status.LOADING, null, null)
        }
    }
}

// An enum to store the current state of api call
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}