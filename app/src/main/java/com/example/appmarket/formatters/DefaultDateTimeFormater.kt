package com.example.appmarket.formatters

import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DefaultDateTimeFormater @Inject constructor(): DateTimeFormatter{

    private val foramater = SimpleDateFormat.getDateTimeInstance()

    override fun formatDateTime(millis: Long): String {
        return foramater.format(Date(millis))
    }
}