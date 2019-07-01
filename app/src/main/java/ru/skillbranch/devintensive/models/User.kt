package ru.skillbranch.devintensive.models

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
        fun makeUser(fullName: String) : User{
            lastId ++

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
    class Builder {
        var id: String = ""
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = Date()
        var isOnline: Boolean = false

        fun id(id :String) : Builder{
            this.id = id
            return this
        }
        fun firstName( firstName: String?) : Builder{
            this.firstName = firstName
            return this
        }
        fun lastName( value: String?) :Builder{
            lastName = value
            return this
        }
        fun avatar( value: String?) :Builder{
            avatar = value
            return this
        }
        fun rating( value: Int) :Builder{
            rating = value
            return this
        }
        fun respect( value: Int) :Builder{
            respect = value
            return this
        }
        fun lastVisit( value: Date) :Builder{
            lastVisit = value
            return this
        }
        fun isOnline( value: Boolean) :Builder{
            isOnline = value
            return this
        }
        fun build():User{
            return User(this)
        }
    }
}
