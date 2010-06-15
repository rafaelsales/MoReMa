package morema.util;

public interface Constants {
	
	String APPLICATION_TITLE = "MoReMa";
	
	String MSG_ERROR_PERSISTENCE = "Erro ao gravar/consultar os dados.";
	String MSG_ERROR_PERSISTENCE_CLOSED = "Unidade de persistencia não está aberta.";
	String MSG_ERROR_PERSISTENCE_NOT_FOUND = "Unidade de persistencia não encontrada.";
	String MSG_ERROR_PERSISTENCE_ITEM_NOT_FOUND = "Item não encontrado.";
	String MSG_ERROR_FULL_MEMORY = "Não há espaço suficiente para gravar os dados.";
	String MSG_ERROR_UNEXPECTED = "Erro inesperado.";
	String MSG_ERROR_INVALID_DATA = "Preencha os campos corretamente.";
	String MSG_ERROR_MUST_SAVE = "Salve antes de prosseguir";
	String MSG_ERROR_SURVEY_EXISTING_TITLE = "Já existe uma pesquisa com este nome.";
	
	String MSG_DATA_SAVED_SUCCESSFULLY = "Dados salvos com sucesso.";
	String MSG_DATA_REMOVED_SUCCESSFULLY = "Dados removidos com sucesso.";
	String MSG_CONFIRM_REMOVE_SURVEY = "Deseja realmente remover a pesquisa?";
	String MSG_CREATE_ANOTHER_QUESTION = "Deseja cadastrar outra pergunta?";
	String MSG_CREATE_QUESTION = "Cadastre as perguntas da pesquisa.";
	String MSG_QUESTION_TYPE = "Tipo de pergunta";
	String MSG_AVERAGE = "Média";
	String MSG_REMOVE_SURVEY = "Remover Pesquisa";
	String MSG_DETAILS = "Detalhes";
	String MSG_QUESTION_TEXT = "Texto da pergunta";
	String MSG_NO = "Não";
	String MSG_YES = "Sim";
	String MSG_TITLE = "Título";
	String MSG_QUESTION = "Pergunta";
	String MSG_ABOUT = "Aplicação desenvolvida por Camila Ferreira, Danusa Ribeiro, Lucas Assunção e Rafael Sales.";
	String MSG_APPLICATION_NAME = "Mobile Research Manager";
	String MSG_ANSWER = "Resposta";
	
	String QUESTION_LABEL_FALSE = "Não";
	String QUESTION_LABEL_TRUE = "Sim";
	String QUESTION_LABEL_CHOICE = "Opção";
	
	String COMMAND_CREATE_SURVEY = "Criar Pesquisa";
	String COMMAND_MANAGE_SURVEY = "Gerenciar Pesquisa";
	String COMMAND_ADD_QUESTION = "Adicionar pergunta";
	String COMMAND_SAVE = "Salvar";
	String COMMAND_BACK = "Voltar";
	String COMMAND_CANCEL = "Cancelar";
	String COMMAND_QUESTION_MULTIPLE_CHOICE_ADD_CHOICE = "Adicionar opção";
	String COMMAND_QUESTION_MULTIPLE_CHOICE_REMOVE_BLANK_CHOICES = "Remover opções vazias";
	String COMMAND_ANSWER = "Responder";
	String COMMAND_REMOVE = "Remover";
	String COMMAND_SHOW_REPORT = "Exibir Relatório";
	String COMMAND_ABOUT = "Sobre";
	String COMMAND_EXIT = "Sair";
	String COMMAND_VIEW_OPEN_QUESTION_ANSWERS = "Visualizar respostas";
	String COMMAND_SELECT = "Selecionar";

	String QUESTION_TYPE_OPEN = "Aberta";
	String QUESTION_TYPE_FLOAT_NUMBER = "Número decimal";
	String QUESTION_TYPE_MULTIPLE_CHOICE = "Múltipla escolha";
	String QUESTION_TYPE_TRUE_FALSE = "Sim/Não";
	
	int TEXTFIELD_MAX_SIZE = 200;



}
