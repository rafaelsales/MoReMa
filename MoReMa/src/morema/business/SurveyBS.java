package morema.business;

import morema.persistence.SurveyDAO;

public class SurveyBS {

	public static short createSurvey(String title) {
		
		return 0;
	}

	public static void removeSurvey(short surveyId) {
	}

	public static short addTrueFalseQuestion(String question) {
		return 0;
	}

	public static short addMultipleChoiceQuestion(String question, String[] choices, boolean multipleAnswer) {
		return 0;
	}

	public static short addIntegerNumberQuestion(String question, Integer lowerBound, Integer upperBound) {
		return 0;
	}

	public static short addFloatNumberQuestion(String question, Float lowerBound, Float	upperBound) {
		return 0;
	}

	public static short addOpenQuestion(String question) {
		return 0;
	}

	public static void removeQuestion(short surveyId, short questionId) {
	}

	public static void answerTrueFalse(short surveyId, short questionId, boolean answer) {
	}

	public static void answerMultipleChoice(short surveyId, short questionId, short answerId) {
	}

	public static void answerMultipleChoite(short surveyId, short questionId, short[] answersIds) {
		
	}

	public static void answerIntegerNumber(short surveyId, short questioId, Integer answer) {
		
	}

	public static void answerFloatNumber(short surveyId, short questioId, Float answer) {
		
	}

	public static void answerOpen(short surveyId, short questionId, String answer) {
		
	}
}
