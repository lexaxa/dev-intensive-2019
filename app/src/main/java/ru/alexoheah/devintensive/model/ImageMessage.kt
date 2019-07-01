package ru.alexoheah.devintensive.model

import ru.alexoheah.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage (id, from, chat, isIncoming, date) {

    override fun formatMessage(): String {
        return "id:$id " +
        "${from!!.lastName} ${if (isIncoming) "получил" else "отправил"} " +
        "изображение \"$image\" ${date.humanizeDiff(Date())}"
    }
}
