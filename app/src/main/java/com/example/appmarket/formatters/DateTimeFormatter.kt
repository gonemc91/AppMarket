package com.example.appmarket.formatters

interface DateTimeFormatter {

    /**
     * Covert temestamp in milliseconds to a user-friendly data-tame string
     */

    fun formatDateTime(millis: Long): String

}