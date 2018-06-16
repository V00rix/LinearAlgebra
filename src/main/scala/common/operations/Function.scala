package common.operations

class Function[Domain, Codomain](definition: Domain => Codomain) {
  def apply(definition: Domain => Codomain): Function = new Function(definition)() {

  }
}
