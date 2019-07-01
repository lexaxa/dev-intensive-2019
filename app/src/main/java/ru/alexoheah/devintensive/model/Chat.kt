package ru.alexoheah.devintensive.model

class Chat (
    val id: String,
    val members: MutableList<User> = mutableListOf(),
    val messages: MutableList<BaseMessage> = mutableListOf()
){

}
