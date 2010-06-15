package morema.business;

import java.util.Hashtable;
import java.util.Vector;

import morema.model.Answer;
import morema.model.FloatNumberQuestion;
import morema.model.MultipleChoiceQuestion;
import morema.model.Question;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.persistence.AnswerDAO;
import morema.util.MoremaException;

public class AnswerBS {

	private AnswerBS() {
	}

	public static void save(Answer answer, Question question) throws MoremaException {
		new AnswerDAO(answer.surveyId).saveRecord(answer);
	}
	
	public static Hashtable getPercentageTrueFalseQuestion(TrueFalseQuestion question, Object[] answers) {
		int countTrue = 0;
		int countFalse = 0;
		int numAnswers = 0;
		for (int i = 0; i < answers.length; i++) {
			Answer answer = (Answer) answers[i];
			if (answer.questionTypeId.equals(Question.QUESTION_TYPE_TrueFalse) &&
					answer.questionId.equals(question.id)) {
				numAnswers++;
				if (((Boolean) answer.answer).booleanValue() == true) {
					countTrue++;
				} else {
					countFalse++;
				}
			}
		}
		Hashtable percentages = new Hashtable(2);
		float onePercent = 100f / numAnswers;
		percentages.put(Boolean.TRUE, new Float(countTrue * onePercent));
		percentages.put(Boolean.FALSE, new Float(countFalse * onePercent));
		return percentages;
	}
	
	public static Hashtable getPercentageMultipleChoiceQuestion(MultipleChoiceQuestion question, Object[] answers) {
		int[] countsChoices = new int[question.choices.size()];
		int numAnswers = 0;
		for (int i = 0; i < answers.length; i++) {
			Answer answer = (Answer) answers[i];
			if ((answer.questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer) || 
					answer.questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer)) &&
					answer.questionId.equals(question.id)) {
				numAnswers++;
				Vector selectedChoices = (Vector) answer.answer;
				for (int j = 0; j < selectedChoices.size(); j++) {
					countsChoices[Integer.valueOf((String) selectedChoices.elementAt(j)).intValue()]++;
				}
			}
		}
		Hashtable percentages = new Hashtable(2);
		float onePercent = 100f / numAnswers;
		for (int i = 0; i < question.choices.size(); i++) {			
			percentages.put(new Integer(i), new Float(countsChoices[i] * onePercent));
		}
		return percentages;
	}
	
	public static float getAverageFloatNumberQuestion(FloatNumberQuestion question, Object[] answers) {
		int numAnswers = 0;
		float sumFloatAnswers = 0f;
		for (int i = 0; i < answers.length; i++) {
			Answer answer = (Answer) answers[i];
			if (answer.questionTypeId.equals(Question.QUESTION_TYPE_FloatNumber) && 
					answer.questionId.equals(question.id)) {
				Float floatAnswer = (Float) answer.answer;
				if (floatAnswer != null) {
					numAnswers++;
					sumFloatAnswers += floatAnswer.floatValue();
				}
			}
		}
		return sumFloatAnswers / numAnswers;
	}
	
	public static Object[] list(Survey survey) throws MoremaException {
		return new AnswerDAO(survey.id).getRecords();
	}

	public static Object[] listByQuestion(Question question) throws MoremaException {
		return new AnswerDAO(question.surveyId).listByQuestion(question);
	}
}
