import common.sets.Set

package object types {
  type CartesianProduct[A, B] = Set[(A, B)]
}
