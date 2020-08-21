package converter

class WeightConverter(value: Double, from: String, to: String) : Converter<Weight>(value, from, to) {

    override fun convert(): Double {
        if (unitFrom is Weight && unitTo is Weight) {
            if (value < 0) {
                throw Exception("Weight shouldn't be negative")
            } else if ((unitFrom as Weight).singular == (unitFrom as Weight).singular) {
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