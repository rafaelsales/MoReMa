package morema.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import morema.business.QuestionBS;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.util.MoremaException;

public class CreateTrueFalseQuestionView extends Form implements CommandListener {
	
	private final Survey survey;
	private final Integer questionTypeId;
	private final Displayable parentForm;
	private final TextField tfTitle;
	private final Command cmdSave = new Command("Salvar", Command.ITEM, 0);
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 1);
	
	public CreateTrueFalseQuestionView(Survey survey, Integer questionTypeId, Displayable parentForm) {
		super("Adicionar pergunta");
		this.survey = survey;
		this.questionTypeId = questionTypeId;
		this.parentForm = parentForm;
		
		tfTitle = new TextField("Título", null, getWidth(), TextField.ANY);
		append(tfTitle);
		addCommand(cmdBack);
		addCommand(cmdSave);
		setCommandListener(this);
	}
	
	private void addQuestion() {
		TrueFalseQuestion question = new TrueFalseQuestion(tfTitle.getString());
		try {
			QuestionBS.addTrueFalseQuestion(question);
		} catch (MoremaException e) {
			MainView.showAlert(e.getMessage(), null);
		}
	}
	
	public void commandAction(Command c, Displayable d) {
		if (c.getLabel().equals(cmdSave.getLabel())) {
			addQuestion();
		} else if (c.getLabel().equals(cmdBack.getLabel())) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}
