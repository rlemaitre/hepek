package ba.sake.hepek.plain.component

import scalatags.Text.all
import all.{form => _, _}
import ba.sake.hepek.html.component.FormComponents
import ba.sake.stone.Wither

object PlainFormComponents {
  val DefaultType = new FormComponents.Type {}

  trait ValidationStateClasses {
    def success: String = "success"
    def warning: String = "warning"
    def error: String   = "error"
  }
}

@Wither
final case class PlainFormComponents(
    formType: FormComponents.Type = PlainFormComponents.DefaultType
) extends PlainFormComponentsImpl

// handy to extend for INCOMPLETE frameworks (see Pure, Bulma..)
private[hepek] trait PlainFormComponentsImpl extends FormComponents {
  import FormComponents._

  /* Validation stuff */
  protected override def validationStateClasses: ValidationStateClasses = new ValidationStateClasses {
    override def success: String = "success"
    override def warning: String = "warning"
    override def error: String   = "error"
  }

  /** normal input "constructor", should override in impl */
  protected override def constructInputNormal(
      inputType: String,
      inputName: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputValidationState: Option[ValidationState],
      inputMessages: Seq[String],
      inputAttrs: Seq[AttrPair],
      inputTransform: Frag => Frag
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputFrag =
      if (inputType == "textarea") textarea(commonAttrs)(inputValue)
      else input(commonAttrs)
    val inputFragTransformed = inputTransform(inputFrag)
    val inputContentFrag = inputLabel match {
      case None => inputFragTransformed
      case Some(inputLabel) =>
        label(inputId.map(`for` := _))(
          inputFragTransformed,
          inputLabel
        )
    }
    div(inputValidationState.map(cls := _.clazz))(
      inputFrag,
      inputMessages.map(span(_)),
      inputHelp.map(span(_))
    )
  }

  /** button input "constructor", should override in impl */
  protected override def constructInputButton(
      inputType: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Frag, // <button> can have e.g. glyphs as content...
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label#Buttons
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/button#Validation
    // no label, name, validation
    // <button> is preferred to <input type="button">
    val commonAttrs = Seq(tpe := inputType) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    if (inputType == "button") button(commonAttrs)(inputLabel)
    else input(commonAttrs)
  }

  /** checkbox input "constructor", should override in impl */
  protected override def constructInputCheckbox(
      inputName: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox
    // no validation
    val commonAttrs = Seq(tpe := "checkbox", name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputFrag = inputLabel match {
      case None => input(commonAttrs)
      case Some(inputLabel) =>
        label(inputId.map(`for` := _))(
          input(commonAttrs),
          inputLabel
        )
    }
    div(
      inputFrag,
      inputHelp.map(span(_))
    )
  }

  protected override def constructInputCheckboxes(
      inputName: String,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag =
    valueAndLabelAndAttrs.map {
      case (cbValue, cbLabel, inputAttrs) =>
        val commonAttrs = Seq(tpe := "checkbox", name := inputName, value := cbValue) ++ inputAttrs
        label(input(commonAttrs), cbLabel)
    }

  protected override def constructInputRadio(
      inputName: String,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag =
    valueAndLabelAndAttrs.map {
      case (radioValue, radioLabel, inputAttrs) =>
        val commonAttrs = Seq(tpe := "radio", name := inputName, value := radioValue) ++ inputAttrs
        label(input(commonAttrs), radioLabel)
    }

  protected override def constructInputSelect(
      inputName: String,
      inputId: Option[String],
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val optionFrags = valueAndLabelAndAttrs.map {
      case (optionValue, optionLabel, optionAttrs) =>
        val commonAttrs = Seq(value := optionValue) ++ optionAttrs
        option(commonAttrs)(optionLabel)
    }
    val selectAttrs = inputAttrs ++ Seq(name := inputName) ++ inputId.map(id := _)
    div(
      inputLabel.map(l => label(inputId.map(`for` := _))),
      select(selectAttrs)(optionFrags)
    )
  }

  // only possible attribute for <optgroup> is "disabled", so we dont bother...
  protected override def constructInputSelectGrouped(
      inputName: String,
      inputId: Option[String],
      valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val optionGroupFrags = valueAndLabelAndAttrsGrouped.map {
      case (optGroupLabel, valueAndLabelAndAttrs) =>
        val optionFrags = valueAndLabelAndAttrs.map {
          case (optionValue, optionLabel, optionAttrs) =>
            val commonAttrs = Seq(value := optionValue) ++ optionAttrs
            option(commonAttrs)(optionLabel)
        }
        optgroup(attr("label") := optGroupLabel)(optionFrags)
    }
    val selectAttrs = inputAttrs ++ Seq(name := inputName) ++ inputId.map(id := _)
    div(
      inputLabel.map(l => label(inputId.map(`for` := _))),
      select(selectAttrs)(optionGroupFrags)
    )
  }
}
