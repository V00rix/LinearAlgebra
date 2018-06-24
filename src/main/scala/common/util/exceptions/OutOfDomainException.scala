package common.util.exceptions

case class OutOfDomainException(private val message: String = "") extends Exception(message) {

}
