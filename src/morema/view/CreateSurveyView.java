package morema.view;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import morema.business.SurveyBS;
import morema.model.Survey;
import morema.util.Constantes;
import morema.util.MoremaException;

public class CreateSurveyView extends Form implements CommandListener {

	private Displayable parentForm;
	private TextField tfTitle;
	private Survey survey;
	private Command cmdCreate = new Command("Criar", Command.ITEM, 0);
	private Command cmdCancel = new Command("Cancelar", Command.CANCEL, 1);
	
	public CreateSurveyView(Displayable parentForm) {
		super("Criar pesquisa");
		this.parentForm = parentForm;
		survey = new Survey();
		
		tfTitle = new TextField("Titulo", null, getWidth(), TextField.ANY);
		append(tfTitle);
		addCommand(cmdCreate);
		addCommand(cmdCancel);
		setCommandListener(this);
	}


	private void createSurvey() {
		try {
			survey.title = tfTitle.getString();
			SurveyBS.createSurvey(survey);
			MainView.getDisplay().setCurrent(new Alert(Constantes.MSG_DADOS_CADASTRADOS_SUCESSO), this);
		} catch (MoremaException e) {
			MainView.getDisplay().setCurrent(new Alert(e.getMessage()));
		}
	}

	public void prepareAddQuestion() {
		
	}


	public void commandAction(Command c, Displayable d) {
		if (c.getLabel().equals(cmdCreate.getLabel())) {
			createSurvey();
		} else if (c.getLabel().equals(cmdCancel.getLabel())) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}
