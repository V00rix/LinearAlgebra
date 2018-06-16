package common.sets

/**
  * Real numbers
  */
class Real(private val value: Double) {
  def +(that: Real): Real = {
    new Real(this.value + that.value)
  }
}
