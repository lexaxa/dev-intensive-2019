package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String) : Pair<String, Triple<Int, Int, Int>>{
        println(answer +"="+question)
        when(question){
            Question.NAME -> {
                if (answer.isNotEmpty() && answer.matches("^[^A-ZА-Я].*?".toRegex())){
                    return "Имя должно начинаться с заглавной буквы\n${question.question}" to status.color
                }
            }
            Question.PROFESSION -> {
                if (answer.isNotEmpty() && answer.matches("^[^а-яa-z].*?".toRegex())){
                    return "Профессия должна начинаться со строчной буквы\n${question.question}" to status.color
                }
            }
            Question.MATERIAL -> {
                if (answer.isNotEmpty() && answer.matches(".*?(\\d).*".toRegex())){
                    //^[а-яa-zA-ZА-Я]+$
                    return "Материал не должен содержать цифр\n${question.question}" to status.color
                }
            }
            Question.BDAY -> {
                if (answer.isNotEmpty() && !answer.matches("^\\d+$".toRegex())){
                    return "Год моего рождения должен содержать только цифры\n${question.question}" to status.color
                }
            }
            Question.SERIAL -> {
                if (answer.isNotEmpty() && !answer.matches("^[\\d]{7}$".toRegex())){
                    return "Серийный номер содержит только цифры, и их 7\n${question.question}" to status.color
                }
            }
            Question.IDLE -> {
                return question.question to status.color
            }
        }
        return if(question.answer.contains(answer.toLowerCase())){
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }else{
            status = status.nextStatus()
            "Это неправильный ответ\n${question.question}" to status.color
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>){
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>){
        NAME("Как меня зовут?", listOf("бендер", "bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}