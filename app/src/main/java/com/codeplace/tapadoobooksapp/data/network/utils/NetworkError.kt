package com.codeplace.tapadoobooksapp.data.network.utils

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION,
    UNABLE_TO_CONNECT,
    UNKNOWN;
}