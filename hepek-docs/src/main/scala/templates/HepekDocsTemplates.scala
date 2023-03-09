package templates

import ba.sake.hepek.anchorjs.AnchorjsDependencies
import ba.sake.hepek.fontawesome5.FADependencies
import ba.sake.hepek.prismjs
import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.theme.bootstrap3.HepekBootstrap3BlogPage
import ba.sake.hepek.theme.bootstrap3.TocSettings
import ba.sake.hepek.theme.bootstrap3.TocType
import utils.Imports.Bundle._
import utils.Imports._
import utils._

import resources._

trait HepekDocsAbstractPage
    extends HepekBootstrap3BlogPage
    with HepekDocsStaticPage
    with prismjs.PrismDependencies {
  // dont have to remember ordering of these.. filter below!
  val hlLangs = List("core", "clike", "scala", "java", "markup")

  override def prismSettings =
    super.prismSettings
      .withTheme(prismjs.Themes.Okaidia)
      .withLanguages(prismjs.PrismConsts.languages filter hlLangs.contains)

  override def tocSettings =
    Some(
      TocSettings(
        tocType = TocType.Scrollspy(45)
      )
    )

}

trait HepekDocsStaticPage extends StaticPage with AnchorjsDependencies with FADependencies {

  override def siteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(images.ico("favicon").ref)
      .withFaviconInverted(images.ico("favicon-small").ref)

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(docs.Index)
      .withMainPages(docs.hepek.components.Index, docs.hepek.Index)

  override def navbar = Some(Navbar)

  // CSS
  override def styleURLs =
    super.styleURLs ++ List(styles.css("main").ref)

  override def bootstrapDependencies = super.bootstrapDependencies.withCssDependencies(
    Dependencies()
      .withDeps(Dependency("yeti/bootstrap.min.css", bootstrapSettings.version, "bootswatch"))
  )

  override def scriptURLs = super.scriptURLs :+ scripts.js("main").ref

  override def pageContent = {
    import Classes._
    frag(
      super.pageContent,
      footer(txtAlignCenter, bgInfo, cls := "navbar-fixed-bottom")(
        hyperlink("https://github.com/sake92/hepek", btnClass)(
          FA.github()
        ),
        hyperlink("https://gitter.im/sake92/hepek", btnClass)(
          FA.gitter()
        )
      )
    )
  }
}
