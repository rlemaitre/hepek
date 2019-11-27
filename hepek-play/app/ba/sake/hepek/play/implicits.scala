package ba.sake.hepek.play

import play.api.http.ContentTypeOf
import play.api.http.ContentTypes
import play.api.http.Writeable
import play.api.mvc.Codec
import ba.sake.hepek.html.HtmlPage

package object implicits {

  implicit def writeableHtmlPage(implicit codec: Codec): Writeable[HtmlPage] =
    Writeable(frag => codec.encode(frag.contents))

  implicit def contentTypeHtmlPage(implicit codec: Codec): ContentTypeOf[HtmlPage] =
    ContentTypeOf(Some(ContentTypes.HTML))
}
