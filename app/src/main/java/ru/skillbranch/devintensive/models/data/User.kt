package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    constructor(builder: Builder) :this(
        id = builder.id,
        firstName = builder.firstName,
        lastName = builder.lastName,
        avatar = builder.avatar,
        rating = builder.rating,
        respect = builder.respect,
        lastVisit = builder.lastVisit,
        isOnline = builder.isOnline
    )

    init {
        println("It's Alive!\n" +
                "${
                if (lastName === "Doe") "His name id $firstName $lastName"
                else "And his name is $firstName $lastName!!!"}\n")
    }

    companion object Factory{
        private var lastId : Int = -1
        fun makeUser(fullName: String) : User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    /**
     * Реализуй паттерн Builder для класса User.
     * User.Builder().id(s)
     * .firstName(s)
     * .lastName(s)
     * .avatar(s)
     * .rating(n)
     * .respect(n)
     * .lastVisit(d)
     * .isOnline(b)
     * .build() должен вернуть объект User
     */
    class Builder (
        var id: String = "",
        var firstName: String = "",
        var lastName: String = "",
        var avatar: String = "",
        var rating: Int = 0,
        var respect: Int = 0,
        var lastVisit: Date? = Date(),
        var isOnline: Boolean = false
    ){
        fun id(id :String) = apply {
            this.id = id
        }
        fun firstName( firstName: String) = apply {
            this.firstName = firstName
            return this
        }
        fun lastName( value: String) = apply {
            lastName = value
            return this
        }
        fun avatar( value: String) = apply {
            avatar = value
            return this
        }
        fun rating( value: Int) = apply {
            rating = value
            return this
        }
        fun respect( value: Int) = apply {
            respect = value
            return this
        }
        fun lastVisit( value: Date) = apply {
            lastVisit = value
            return this
        }
        fun isOnline( value: Boolean) = apply {
            isOnline = value
            return this
        }
        fun build() = User(this)

    }
}
