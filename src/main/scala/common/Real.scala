package common

/**
  * Real number
  */
class Real(val value: Double) {

  def +(that: Real): Real = {
    new Real(this.value + that.value)
  }
}
