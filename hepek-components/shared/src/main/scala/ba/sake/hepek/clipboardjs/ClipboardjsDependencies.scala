package ba.sake.hepek.clipboardjs

import ba.sake.hepek.html._

trait ClipboardjsDependencies extends PageDependencies {

  def clipboardjsSettings: ComponentSettings =
    ComponentSettings("1.7.1", "clipboard.js")

  def clipboardjsDependencies: ComponentDependencies =
    ComponentDependencies.default.withJsDependencies(
      Dependencies.default.withDeps(
        Dependency("clipboard.min.js", clipboardjsSettings.version, clipboardjsSettings.pkg)
      )
    )

  override def components =
    super.components.appended(clipboardjsSettings -> clipboardjsDependencies)
}
