package ba.sake.hepek.pure

import ba.sake.hepek.html._

trait PureDependencies extends PageDependencies {

  def pureModules: List[String] =
    List("pure", "buttons", "forms", "grids-responsive", "menus", "tables")

  def pureSettings: ComponentSettings =
    ComponentSettings("1.0.0", "purecss", DependencyProvider.unpkg)

  def pureDependencies = ComponentDependencies().withCssDependencies(
    Dependencies().withDeps(
      pureModules.map { moduleName =>
        Dependency(
          s"$moduleName-min.css",
          pureSettings.version,
          pureSettings.pkg,
          baseFolder = Option("build/")
        )
      }
    )
  )

  override def components = super.components.appended(pureSettings -> pureDependencies)
}
