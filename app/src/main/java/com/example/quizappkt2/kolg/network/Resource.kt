package com.example.quizappkt2.kolg.network

class Resource <out T> (val status: Status, val data: T?, val message: String?, val code: Int?){

    companion object {


        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }


        fun <T> success(data: T?, code: Int): Resource<T> {
            return Resource(Status.SUCCESS, data, null, code)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, null , null)
        }
    }
}