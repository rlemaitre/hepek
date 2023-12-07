package ba.sake.hepek.plain.component.classes

import ba.sake.hepek.html.component.classes.ButtonClasses
import ba.sake.hepek.scalatags.all._

private[hepek] trait PlainButtonClasses extends ButtonClasses:

  override def btnClass   = cls := "btn"
  override def btnPrimary = cls := "btn-primary"
  override def btnSuccess = cls := "btn-success"
  override def btnInfo    = cls := "btn-info"
  override def btnWarning = cls := "btn-warning"
  override def btnDanger  = cls := "btn-danger"
  override def btnLink    = cls := "btn-link"
  override def btnActive  = cls := "btn-active"

  override def btnSizeLg = cls := "btn-lg"
  override def btnSizeMd = cls := "btn-md"
  override def btnSizeSm = cls := "btn-sm"
  override def btnSizeXs = cls := "btn-xs"

  override def btnWidthFull = cls := "btn-block"
