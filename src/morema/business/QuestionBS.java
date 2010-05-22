package morema.business;

import morema.model.FloatNumberQuestion;
import morema.model.IntegerNumberQuestion;
import morema.model.MultipleChoiceMultipleAnswerQuestion;
import morema.model.OpenQuestion;
import morema.model.Question;
import morema.model.TrueFalseQuestion;

public class QuestionBS {
	
	private QuestionBS() {
	}
	
	public static TrueFalseQuestion addTrueFalseQuestion(TrueFalseQuestion question) {
		return question;
	}

	public static MultipleChoiceMultipleAnswerQuestion addMultipleChoiceMultipleAnswerQuestion(MultipleChoiceMultipleAnswerQuestion question) {
		return question;
	}

	public static IntegerNumberQuestion addIntegerNumberQuestion(IntegerNumberQuestion question) {
		return question;
	}

	public static FloatNumberQuestion addFloatNumberQuestion(FloatNumberQuestion question) {
		return question;
	}

	public static OpenQuestion addOpenQuestion(OpenQuestion question) {
		return question;
	}

	public static void removeQuestion(Question question) {
	}
}
