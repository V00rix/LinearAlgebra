package common.exceptions

class BadCompositionException(private val message: String = "") extends BadRelationException(message) {

}
