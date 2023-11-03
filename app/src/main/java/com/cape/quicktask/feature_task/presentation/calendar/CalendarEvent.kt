package com.cape.quicktask.feature_task.presentation.calendar

sealed class CalendarEvent{
    class GetEventsByDay(val date: String): CalendarEvent()
}