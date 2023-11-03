package ba.sake.hepek.bootstrap5.component.classes

import ba.sake.hepek.html.component.classes.BackgroundClasses
import ba.sake.hepek.scalatags.all._

trait BootstrapBackgroundClasses extends BackgroundClasses {
  override def bgPrimary = cls := "bg-primary"
  override def bgSuccess = cls := "bg-success"
  override def bgInfo    = cls := "bg-info"
  override def bgWarning = cls := "bg-warning"
  override def bgDanger  = cls := "bg-danger"
}
