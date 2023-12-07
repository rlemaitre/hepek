package ba.sake.hepek.mermaid

import ba.sake.hepek.scalatags.all.*

object MermaidHelpers extends MermaidHelpers

trait MermaidHelpers {

  // maybe looks stupid, user could type this by himself,
  // but if we want to e.g. do server-side rendering, this should be handy
  def mermaid(str: String): Frag =
    div(cls := "mermaid")(str)
}
