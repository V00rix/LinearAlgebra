//package common
//
//class LinearSpace[ScalarType, VectorType](add: LinearAdd[ScalarType, VectorType], multiply: LinearMultiply[ScalarType, VectorType]) {
//  val elements: Array[Vector] = Array()
//
//  def isValid(vectors: Set[Vector]): Boolean = {
//    vectors.forEach(_ => {
//      // TODO:
//    })
//    vectors.forTwo((v1, v2) => {
//      assert(v1 == v2)
//      assert(v2 == v1)
//      // functions should:
//      /*
//      x add zero === zero
//      x add (y add z) === (x add y) add z
//      exists y: x add y = zero (function?)
//
//      exists one : x multiply one = x
//      alpha * (betta * x)  = (alpha * betta) * x
//
//      alpha * (x + y) = alpha * x + alpha * y
//      (alpha + betta) * x = alpha * x + betta * x
//
//      how to check? spec
//
//      a (x + y) =? ax + ay =? ay + ax
//       */
//
//    })
//    true
//  }
//
//  abstract class Vector(val value: VectorType) {
//    def *:(scalar: ScalarType): LinearSpace[ScalarType, VectorType]#Vector = multiply.apply((scalar, this))
//
//    def +(vector: Vector): LinearSpace[ScalarType, VectorType]#Vector = add.apply((this, vector))
//
//    def create(value: VectorType): Vector = {
//      new Vector(value)
//    }
//
//    def ===[V <: Vector](that: V): Boolean = that.value == value
//  }
//
//}
