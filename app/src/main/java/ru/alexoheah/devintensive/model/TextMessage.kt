package ru.alexoheah.devintensive.model

import ru.alexoheah.devintensive.extensions.format
import ru.alexoheah.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String?
): BaseMessage(id, from, chat, isIncoming, date){

    override fun formatMessage(): String {
        return "id:$id " +
                "${from!!.firstName} ${if (isIncoming) "получил" else "отправил"} " +
                "сообщение \"$text\" ${date.humanizeDiff(Date())}"
    }
}
