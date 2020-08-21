package converter

class DistanceConverter(value: Double, from: String, to: String) : Converter<Distance>(value, from, to) {

    override fun convert(): Double {
        if (unitFrom is Distance && unitTo is Distance) {
            if (value < 0) {
                throw Exception("Length shouldn't be negative")
            } else if ((unitFrom as Distance).singular == (unitTo as Distance).singular) {
                return value
            }
            var result = value * unitFrom!!.factor
            result /= unitTo!!.factor
            return result
        } else if (unitFrom == null) {
            throw Exception("Conversion from ??? to ${unitTo!!.plural} is impossible")
        } else if (unitTo == null) {
            throw Exception("Conversion from ${unitFrom!!.plural} to ??? is impossible")
        } else if (unitTo != null && unitFrom != null) {
            throw Exception("Conversion from ${unitFrom!!.plural} to ${unitTo!!.plural} is impossible")
        } else {
            throw Exception("Conversion from ??? to ??? is impossible")
        }
    }

}