package converter

sealed class Units(open val singular: String, open val plural: String, open val factor: Double)

data class Weight(override val singular: String, override val plural: String, override val factor: Double): Units(singular, plural, factor)
data class Distance(override val singular: String, override val plural: String, override val factor: Double): Units(singular, plural, factor)
data class Temperature(override val singular: String, override val plural: String, override val factor: Double): Units(singular, plural, 0.0)

