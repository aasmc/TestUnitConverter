package converter


class TempConverter(value: Double, from: String, to: String) : Converter<Temperature>(value, from, to) {

    private fun celsiusToFahrenheit(value: Double): Double {
        return value * 9 / 5 + 32
    }

    private fun fahrenheitToCelsius(value: Double): Double {
        return (value - 32) * 5 / 9
    }

    private fun kelvinToCelsius(value: Double): Double {
        return value - 273.15
    }

    private fun celsiusToKelvin(value: Double): Double {
        return value + 273.15
    }

    private fun fahrenheitToKelvin(value: Double): Double {
        return (value + 459.67) * 5 / 9
    }

    private fun kelvinToFahrenheit(value: Double): Double {
        return value * 9 / 5 - 459.67
    }

    override fun convert(): Double {
        if (unitFrom is Temperature && unitTo is Temperature) {
            if ((unitFrom as Temperature).singular == (unitTo as Temperature).singular) {
                return value
            }
            var result = 0.0
            when {
                "degree Celsius" == unitFrom!!.singular && "degree Fahrenheit" == unitTo!!.singular -> {
                    result = celsiusToFahrenheit(value)
                }
                "degree Celsius" == unitFrom!!.singular && "kelvin" == unitTo!!.singular -> {
                    result = celsiusToKelvin(value)
                }
                "degree Fahrenheit" == unitFrom!!.singular && "kelvin" == unitTo!!.singular -> {
                    result = fahrenheitToKelvin(value)
                }
                "degree Fahrenheit" == unitFrom!!.singular && "degree Celsius" == unitTo!!.singular -> {
                    result = fahrenheitToCelsius(value)
                }
                "kelvin" == unitFrom!!.singular && "degree Celsius" == unitTo!!.singular -> {
                    result = kelvinToCelsius(value)
                }
                "kelvin" == unitFrom!!.singular && "degree Fahrenheit" == unitTo!!.singular -> {
                    result = kelvinToFahrenheit(value)
                }
            }
            return result
        }
        else if (unitFrom == null) {
            throw Exception("Conversion from ??? to ${unitTo!!.plural} is impossible")
        } else if (unitTo == null) {
            throw Exception("Conversion from ${unitFrom!!.plural} to ??? is impossible")
        } else if (unitTo != null && unitFrom != null){
            throw Exception("Conversion from ${unitFrom!!.plural} to ${unitTo!!.plural} is impossible")
        } else {
            throw Exception("Conversion from ??? to ??? is impossible")
        }
    }
}