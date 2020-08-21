package converter

import java.lang.Exception

class InputParser {

    fun parseTemp(input: String) {
        try {
            checkInput(input)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
        val split = input.split(" ")
        val initValue = split[0].toDouble()
        var indexFrom = 0
        if (split.contains("degree") || split.contains("degrees") || split.contains("DEGREES") || split.contains("DEGREE")) {
            if (split[1].toLowerCase().matches("degrees?".toRegex())) {
                indexFrom = 1
            }
        }

        var indexTo = split.indexOfFirst { elem -> elem.matches("(to|in)".toRegex()) }
        if (split[indexTo + 1].toLowerCase().matches("degrees?".toRegex())) {
            indexTo++
        }
        val initUnitFrom = split[indexFrom + 1].toLowerCase()
        val initUnitTo = split[indexTo + 1].toLowerCase()

        val converter = TempConverter(initValue, initUnitFrom, initUnitTo)
        if (converter.unitTo == null && converter.unitFrom == null) {
            throw Exception("Conversion from ??? to ??? is impossible")
        }
        try {
            getConvertedValue(converter)
        } catch (e: Exception) {
            println(e.message)
        }


    }

    fun parseUnit(input: String) {
        try {
            checkInput(input)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
        val split = input.split(" ")
        val initValue = split[0].toDouble()
        val initUnitFrom = split[1].toLowerCase()
        val initUnitTo = split[3].toLowerCase()
        val distanceConverter = DistanceConverter(initValue, initUnitFrom, initUnitTo)
        val weightConverter = WeightConverter(initValue, initUnitFrom, initUnitTo)
        if ((distanceConverter.unitTo == null && distanceConverter.unitFrom == null) || (weightConverter.unitTo == null && weightConverter.unitFrom == null)) {
            throw Exception("Conversion from ??? to ??? is impossible")
        }

        when {
            distanceConverter.unitsOfDistance.contains(initUnitFrom.toLowerCase()) ||
                    distanceConverter.unitsOfDistance.contains(initUnitTo.toLowerCase()) && !distanceConverter.unitsOfWeights.contains(
                initUnitTo.toLowerCase()
            ) -> {

                try {
                    getConvertedValue(distanceConverter)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            distanceConverter.unitsOfWeights.contains(initUnitFrom.toLowerCase()) ||
                    distanceConverter.unitsOfWeights.contains(initUnitTo.toLowerCase()) && !distanceConverter.unitsOfDistance.contains(
                initUnitTo.toLowerCase()
            ) -> {

                try {
                    getConvertedValue(weightConverter)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }

    private fun getConvertedValue(converter: Converter<Units>) {
        try {
            val result = converter.convert()
            val initUnitFrom = if (converter.value != 1.0) {
                converter.unitFrom!!.plural
            } else {
                converter.unitFrom!!.singular
            }
            val initUnitTo = if (result != 1.0) {
                converter.unitTo!!.plural
            } else {
                converter.unitTo!!.singular
            }
            println("${converter.value} $initUnitFrom is $result $initUnitTo")
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    private fun checkInput(input: String) {
        val regex =
            "^-?(\\d)+(\\.\\d+)?\\s(\\b[a-z]+\\b|degrees?\\s\\b[a-z]+\\b)\\s([a-z]*to|in)\\s(\\b.+\\b|degrees?\\s\\b.+\\b)".toRegex()
        if (!input.toLowerCase().matches(regex)) {
            throw Exception("Parse error")
        }
    }
}