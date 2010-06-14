package morema.view;

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

	private final Displayable parentForm;
	private final TextField tfTitle;
	private final Survey survey;
	private final Command cmdSave = new Command("Salvar", Command.ITEM, 0);
	private final Command cmdAddQuestion = new Command("Adicionar pergunta", Command.ITEM, 1);
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 2);
	
	public CreateSurveyView(Displayable parentForm) {
		this(new Survey(), parentForm);
	}
	
	public CreateSurveyView(Survey survey, Displayable parentForm) {
		super("Criar pesquisa");
		this.parentForm = parentForm;
		this.survey = survey;
		
		tfTitle = new TextField("Titulo", survey.title, Constantes.TEXTFIELD_MAX_SIZE, TextField.ANY);
		append(tfTitle);
		addCommand(cmdSave);
		addCommand(cmdAddQuestion);
		addCommand(cmdBack);
		setCommandListener(this);
	}

	private void saveSurvey() {
		try {
			survey.title = tfTitle.getString();
			SurveyBS.save(survey);
			MainView.showAlert(Constantes.MSG_DADOS_CADASTRADOS_SUCESSO, null, false);
		} catch (MoremaException e) {
			MainView.showAlert(e, null);
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmdSave) {
			saveSurvey();
		} else if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		} else if (c == cmdAddQuestion) {
			if (survey.id == null) {
				MainView.showAlert(Constantes.MSG_ERRO_NECESSARIO_SALVAR, null, true);
			} else {
				MainView.getDisplay().setCurrent(new PrepareCreateQuestionView(survey, this));
			}
		}
	}
}
