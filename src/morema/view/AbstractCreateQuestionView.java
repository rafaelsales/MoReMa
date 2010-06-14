package morema.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import morema.model.Survey;
import morema.util.Constantes;
import morema.util.MoremaException;

public abstract class AbstractCreateQuestionView extends Form implements CommandListener {
	
	protected final Survey survey;
	protected final Displayable parentForm;
	protected final TextField tfTitle;
	protected final Command cmdSave = new Command("Salvar", Command.ITEM, 0);
	protected final Command cmdCancel = new Command("Cancelar", Command.CANCEL, 1);
	
	public AbstractCreateQuestionView(Survey survey, Displayable parentForm) {
		super("Adicionar pergunta");
		this.survey = survey;
		this.parentForm = parentForm;
		
		tfTitle = new TextField("Texto da pergunta", null, Constantes.TEXTFIELD_MAX_SIZE, TextField.ANY);
		
		append(tfTitle);
		addCommand(cmdCancel);
		addCommand(cmdSave);
		setCommandListener(this);
	}
	
	protected abstract void addQuestion() throws MoremaException;
	
	public void commandAction(Command c, Displayable d) {
		if (c == cmdSave) {
			try {
				addQuestion();
				MainView.showAlert(Constantes.MSG_DADOS_CADASTRADOS_SUCESSO, null, false);
				MainView.showConfirmation(Constantes.MSG_CADASTRAR_OUTRA_PERGUNTA, new PrepareCreateQuestionView(survey, MainView.getMainView()), MainView.getMainView());
			} catch (MoremaException e) {
				MainView.showAlert(e, this);
			}
		} else if (c == cmdCancel) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}
