package ba.sake.hepek.plain.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.PlainPage
import ba.sake.hepek.plain.component._
import ba.sake.hepek.plain.component.classes.PlainClassesBundle
import ba.sake.stone.Wither

@Wither
case class PlainStaticBundle(
    Form: PlainFormComponents = PlainFormComponents(),
    Grid: PlainGridComponents = PlainGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: PlainNavbarComponents = PlainNavbarComponents(),
    Panel: PlainPanelComponents = PlainPanelComponents(),
    Classes: PlainClassesBundle = PlainClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with PlainMarkdownComponents {
  override type HtmlPage   = PlainPage
  override type StaticPage = PlainStaticPage
}
