package common

import common.operations.{CartesianProduct, Function}
import common.sets.Real
import common.spaces.LinearSpace
import org.scalatest.FlatSpec

class BaseSpec extends FlatSpec {

  def x(cartesianProduct: CartesianProduct[Real, Real]): Real = cartesianProduct.a + cartesianProduct.b

  var space = new LinearSpace[Real]

  c = new CartesianProduct[Real, Real] {
    override var a: Real = _
    override var b: Real = _
  }


  var additionReal = new Function[CartesianProduct[Real, Real], Real](x)
}
