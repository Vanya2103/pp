package dialogs;
import buttons.Button;
import buttons.HtmlButton;
/**
 * HTML-диалог.
 */
public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}