package morema.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import morema.model.Survey;

public abstract class AbstractCreateQuestionView extends Form implements CommandListener {
	
	protected final Survey survey;
	protected final Displayable parentForm;
	protected final TextField tfTitle;
	protected final Command cmdSave = new Command("Salvar", Command.ITEM, 0);
	protected final Command cmdBack = new Command("Voltar", Command.CANCEL, 1);
	
	public AbstractCreateQuestionView(Survey survey, Displayable parentForm) {
		super("Adicionar pergunta");
		this.survey = survey;
		this.parentForm = parentForm;
		
		tfTitle = new TextField("Texto da pergunta", null, getWidth(), TextField.ANY);
		
		append(tfTitle);
		addCommand(cmdBack);
		addCommand(cmdSave);
		setCommandListener(this);
	}
	
	protected abstract void addQuestion();
	
	public void commandAction(Command c, Displayable d) {
		if (c == cmdSave) {
			addQuestion();
		} else if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}
