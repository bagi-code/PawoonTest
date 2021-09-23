package com.robby.pawoontest.utils

import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

object Helpers {

    fun Throwable.getErrorBodyMessage(): String {
        return if (this is HttpException) {
            val errorCode = this.code()
            if (errorCode == 405) {
                "Method yang digunakan salah"
            } else if (errorCode == 503) {
                "Error Server"
            } else {
                "Permintaan anda belum berhasil di proses. Silakan coba kembali"
            }

        } else if (this is ConnectException || this is UnknownHostException) {
            "Maaf Anda sedang Offline. Silakan coba kembali"

        } else {
            return if (this.message == null)
                "Permintaan anda belum berhasil di proses. Silakan coba kembali"
            else if (this.message.equals(""))
                ""
            else
                this.message!!

        }
    }



}