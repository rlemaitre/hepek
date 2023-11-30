package utils

import ba.sake.hepek.bootstrap5.statik.BootstrapStaticBundle
import ba.sake.hepek.prismjs.PrismCodeHighlightComponents

object Imports {
  val Bundle = locally {
    val b = BootstrapStaticBundle()
    import b.*

    val ratios = Ratios(Ratio(1, 4, 1), Ratio(1, 1), Ratio(1, 4, 1))
    val grid = Grid.withScreenRatios(
      Grid.screenRatios.withSm(None).withXs(None).withLg(ratios).withMd(ratios)
    )
    b.withGrid(grid)
  }
  import Bundle.Tags.*

  object chl extends PrismCodeHighlightComponents

  val FA = ba.sake.hepek.fontawesome5.FA

  // class field/method description
  case class ClassProperty(
      name: String,
      tpe: String,
      desc: String = "",
      defaultValue: Option[String] = None
  )

  import Bundle.Classes._

  def renderClassProps(props: List[ClassProperty]) =
    div(tableResponsive)(
      table(tableClass, tableHoverable)(
        tr(th("Name"), th("Mandatory"), th("Type"), th("Default value"), th("Description")),
        props.map { cp =>
          tr(
            td(cp.name),
            td(cp.defaultValue.map(_ => "No").getOrElse("Yes")),
            td(cp.tpe),
            td(cp.defaultValue),
            td(cp.desc)
          )
        }
      )
    )
}
