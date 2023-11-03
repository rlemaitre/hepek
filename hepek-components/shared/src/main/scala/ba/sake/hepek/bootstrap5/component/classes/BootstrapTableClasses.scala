package ba.sake.hepek.bootstrap5.component.classes

import ba.sake.hepek.html.component.classes.TableClasses
import ba.sake.hepek.scalatags.all._

trait BootstrapTableClasses extends TableClasses {
  override def tableClass      = cls := "table"
  override def tableStriped    = cls := "table-striped"
  override def tableBordered   = cls := "table-bordered"
  override def tableHoverable  = cls := "table-hover"
  override def tableResponsive = cls := "table-responsive"
  override def tableWidthFull  = cls := ""

  override def tableRowSuccess = cls := "success"
  override def tableRowInfo    = cls := "info"
  override def tableRowWarning = cls := "warning"
  override def tableRowDanger  = cls := "danger"
  override def tableRowActive  = cls := "active"

  override def tableDataSuccess = cls := "success"
  override def tableDataInfo    = cls := "info"
  override def tableDataWarning = cls := "warning"
  override def tableDataDanger  = cls := "danger"
  override def tableDataActive  = cls := "active"
}
