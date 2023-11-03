package ba.sake.hepek.bulma

import ba.sake.hepek.html._

trait BulmaDependencies extends PageDependencies {
  val bulmaFilename: String = "bulma"

  def bulmaSettings: ComponentSettings =
    ComponentSettings("0.8.2", bulmaFilename, DependencyProvider.cdnjs)

  def bulmaDependencies = ComponentDependencies().withCssDependencies(
    Dependencies().withDeps(
      Dependency(s"css/$bulmaFilename.min.css", bulmaSettings.version, bulmaSettings.pkg)
    )
  )

  override def components = super.components.appended(bulmaSettings -> bulmaDependencies)
}
