package common.exceptions

case class OutOfDomainException(private val message: String = "") extends Exception(message) {

}
