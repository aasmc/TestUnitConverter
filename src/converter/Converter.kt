package converter

abstract class Converter<out T : Units>(val value: Double, val from: String, val to: String) {
    var unitFrom: Units? = initUnit(from)
    var unitTo: Units? = initUnit(to)
    abstract fun convert(): Double

    val unitsOfDistance = listOf(
        "meter", "m", "meters",
        "km", "kilometer", "kilometers",
        "cm", "centimeter", "centimeters",
        "mm", "millimeter", "millimeters",
        "mi", "mile", "miles",
        "ya", "yard", "yards",
        "ft", "foot", "feet",
        "in", "inch", "inches"
    )

    val unitsOfWeights = listOf(
        "g", "gram", "grams",
        "kg", "kilogram", "kilograms",
        "mg", "milligram", "milligrams",
        "lb", "pound", "pounds",
        "oz", "ounce", "ounces"
    )

    val unitsOfTemperature = listOf(
        "degree Celsius", "degrees Celsius", "celsius", "dc", "c",
        "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "f",
        "kelvin", "kelvins", "k"
    )

    private fun initUnit(unitName: String): Units? {
        when (unitName.toLowerCase()) {
            "celsius", "dc", "c" -> {
                return Temperature("degree Celsius", "degrees Celsius", 0.0)
            }
            "fahrenheit", "df", "f" -> {
                return Temperature("degree Fahrenheit", "degrees Fahrenheit", 0.0)
            }
            "kelvin", "kelvins", "k" -> {
                return Temperature("kelvin", "kelvins", 0.0)
            }
            "g", "gram", "grams" -> {
                return Weight("gram", "grams", 1.0)
            }
            "kg", "kilogram", "kilograms" -> {
                return Weight( "kilogram", "kilograms", 1000.0)
            }
            "mg", "milligram", "milligrams" -> {
                return Weight( "milligram", "milligrams", 0.001)
            }
            "lb", "pound", "pounds" -> {
                return Weight( "pound", "pounds", 453.592)
            }
            "oz", "ounce", "ounces" -> {
                return Weight( "ounce", "ounces", 28.3495)
            }
            "m", "meter", "meters" -> {
                return Distance( "meter", "meters", 1.0)
            }
            "km", "kilometer", "kilometers" -> {
                return Distance( "kilometer", "kilometers", 1000.0)
            }
            "cm", "centimeter", "centimeters" -> {
                return Distance( "centimeter", "centimeters", 0.01)
            }
            "mm", "millimeter", "millimeters" -> {
                return Distance( "millimeter", "millimeters", 0.001)
            }
            "mi", "mile", "miles" -> {
                return Distance( "mile", "miles", 1609.35)
            }
            "yd", "yard", "yards" -> {
                return Distance( "yard", "yards", 0.9144)
            }
            "ft", "foot", "feet" -> {
                return Distance( "foot", "feet", 0.3048)
            }
            "in", "inch", "inches" -> {
                return Distance( "inch", "inches", 0.0254)
            }
        }
        return null
    }
}