package machine

enum class TypesOfCoffee(val water: Int, val milk: Int, val beans: Int, val price: Int) {
    ESPRESSO( 250, 0, 16, 4),
    LATTE( 350, 75, 20, 7),
    CAPPUCCINO( 200, 100, 12, 6)
}

fun main() {
    CoffeeMachine(400,540,120,9,550).menu()
}

class CoffeeMachine(
    private var water: Int,
    private var milk: Int ,
    private var beans: Int ,
    private var cups: Int ,
    private var money: Int
) {

    fun menu() {
        loop@ do {
            println("Write action (buy, fill, take, remaining, exit): ")
            val action = readln()
            when (action) {
                "remaining" -> remaining()
                "buy" ->  {
                    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                    when (readln()) {
                        "1" -> makeCoffee(TypesOfCoffee.ESPRESSO)
                        "2" -> makeCoffee(TypesOfCoffee.LATTE)
                        "3" -> makeCoffee(TypesOfCoffee.CAPPUCCINO)
                        "back" -> continue@loop
                    }
                }
                "fill" -> {
                    println("Write how many ml of water you want to add:")
                    val addWater = readln().toInt()
                    println("Write how many ml of milk you want to add:")
                    val addMilk = readln().toInt()
                    println("Write how many grams of coffee beans you want to add:")
                    val addCoffee = readln().toInt()
                    println("Write how many disposable cups you want to add:")
                    val addCups = readln().toInt()
                    water += addWater
                    beans += addCoffee
                    milk += addMilk
                    cups += addCups
                }
                "take" -> {
                    println("I gave you $$money")
                    money = 0
                }

            }
        } while (action != "exit")
    }

    private fun remaining() {
        println("""The coffee machine has:"
        |$water ml of water
        |$milk ml of milk
        |$beans g of coffee beans
        |$cups disposable cups
        |$$money of money""".trimMargin())
    }

    private fun makeCoffee(drink: TypesOfCoffee) {
        if (everythingIsLost(drink)) {
            money += drink.price
            water -= drink.water
            milk -= drink.milk
            beans -= drink.beans
            cups--
        }
    }

    private fun everythingIsLost(drink: TypesOfCoffee): Boolean {
        var enoughComponents = false
        if (water - drink.water <= 0) {
            println("Sorry, not enough water!")
        } else if (milk - drink.milk <= 0) {
            println("Sorry, not enough milk!")
        } else if (beans - drink.beans <= 0) {
            println("Sorry, not enough beans!")
        } else {
            enoughComponents = true
            println("I have enough resources, making you a coffee!")
            println("Take your coffee!")
        }
        return enoughComponents
    }

}