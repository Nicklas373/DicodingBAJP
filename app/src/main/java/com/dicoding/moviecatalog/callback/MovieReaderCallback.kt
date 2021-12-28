package com.dicoding.moviecatalog.callback

interface MovieReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}