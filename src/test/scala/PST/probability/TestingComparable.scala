package PST.probability

trait TestingComparable {
  this: Delta =>

  var expected = 0f
  var actual = 0f
  def test()(implicit delta: Float): Unit = assert(Math.abs(expected - actual) <= delta)
  def logExpected(): Unit = println(s"Expected probability is $expected")
  def logActual(): Unit = println(s"Actual probability is $actual")

}
