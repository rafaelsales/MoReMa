package morema.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import morema.business.AnswerBS;
import morema.model.Answer;
import morema.model.OpenQuestion;
import morema.model.Survey;
import morema.util.Constants;
import morema.util.MoremaException;

public class AnswersOpenQuestionView extends Form implements CommandListener {

	private final Command cmdBack = new Command(Constants.COMMAND_BACK, Command.CANCEL, 0);
	private final Displayable parentForm;

	public AnswersOpenQuestionView(Survey survey, OpenQuestion question, Displayable parentForm) {
		super(survey.title);
		this.parentForm = parentForm;
		
		String prefixQuestion = Constants.MSG_QUESTION + " No." + question.id.toString() + ": ";
		StringItem strItem = new StringItem(prefixQuestion + question.question, null);
		strItem.setLayout(StringItem.LAYOUT_NEWLINE_AFTER);
		append(strItem);
		
		try {
			Object[] answers = AnswerBS.listByQuestion(question);
			for (int i = 0; i < answers.length; i++) {
				String answer = (String) ((Answer) answers[i]).answer;
				strItem = new StringItem("No." + i + ": ", answer);
				strItem.setLayout(StringItem.LAYOUT_NEWLINE_AFTER);
				append(strItem);
			}
		} catch (MoremaException e) {
			MainView.getDisplay().setCurrent(parentForm);
			return;
		}
		
		addCommand(cmdBack);
		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}

}
