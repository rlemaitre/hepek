package files.hepek.components

import ba.sake.hepek.katex.KatexDependencies
import utils.Imports.Bundle.*, Tags.*

object MathSupport extends HepekComponentsDocsPage with KatexDependencies {

  override def pageSettings =
    super.pageSettings.withTitle("Math support")

  override def blogSettings =
    super.blogSettings.withSections(mathSection)

  val mathSection = Section(
    "Math support",
    frag(
      """
        Hepek has support for KaTeX math library.  
        KaTeX is using the LaTeX syntax, so it is good to familiarize yourself with it.  
        You need to extend `KatexDependencies` to import the CSS/JS stuff.

        Since Hepek supports Markdown, it is hard to find an unused delimiter character.  
        Hepek chose acute accent mark ("forward-tick"), `´` for inline math.  
        On Windows the shortcut is <kbd>AltGr</kbd>+<kbd>9</kbd>.

        For block-level math double-dollar is used, `$$`.

        Example: 
          <pre>´A \setminus B = {x | x \in A \land x \notin B}´</pre>  
        becomes:  
          ´A \setminus B = \\{x | x \\in A \land x \\notin B\\}´.

        ---
        Example (block-level): 
          <pre>$$A \setminus B = {x | x \in A \land x \notin B}$$</pre>  
        becomes:  
          $$A \setminus B = \\{x | x \\in A \land x \\notin B\\}$$

        You need to be careful with Scala's character escaping, e.g. when you want to type
        `\notin` Scala will try to parse `\n` as newline...  
        So you'll get into that with trial and error.
      """.md
    )
  )
}
