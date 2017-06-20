
public class Pessoa extends TableMaster {

	@PKTableMaster
	private int pess_id;
	private String pess_nome;
	private Timestamp pess_nascimento;
	private String pess_cpf;
	private String pess_rg;
	private int pess_sexo;
	private String pess_login;
	private int pess_tipo;
	private String pess_status;

	// gets e sets dos atributos
	...
}

public class Contrato extends TableMaster {

	@PKTableMaster
	private int cnto_id;
	private String cnto_numero;
	private Timestamp cnto_data_inicio;
	private Timestamp cnto_data_cancelamento;
	private int cnto_pessoa;
	
	// gets e sets dos atributos
	...
}

public class ContratoPessoa {

	private Contrato contrato;	
	private Pessoa pessoa;
	
	// gets e sets de delegação do atributos da classe
	...
}

	private static String url = "DATABASE_URL";
	private static String user = "admin";
	private static String password = "senha_admin";
	private static String driver = "org.postgresql.Driver";
	
	public void consultaDepartamento(String args[], List listaDepartamento) throws Exception {

		String clausulaWhere = montaClausulaWhere(args);

		// (1) Estabelece uma conexão com o banco de dados
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);

		// (2) Cria Statement para consultar no banco de dados
		Statement statement = connection.createStatement();
		
		// (3) Realiza requisição de consulta ao banco de dados
		ResultSet rs = statement.executeQuery("SELECT * FROM DEPARTAMENTO" + clausulaWhere);
		
		// Percorre o resultado da consulta e monta instâncias de Pessoa.
		while(rs.next()) {
			
			Departamento departamento = new Departamento();
			
			departamento.setDept_id(rs.getInt("dept_id"));
			departamento.setDept_descricao(rs.getString("dept_descricao"));
			
			listaDepartamento.add(pessoa);
		}
	}



// Instância da classe
SigmaDB sigma = new SigmaDB();

// Classe que representa um espelho de uma tabela do banco		
Pessoa pessoa = new Pessoa();
pessoa.setPess_nome("Igor Gomes de Moisés");
		
// Realiza uma consulta ao banco.
List<Pessoa> listaPessoa = sigma.pesquisaTabela(pessoa);
		
if (!listaPessoa.isEmpty()) {
			
	ConnectionLog connection = sigma.abrirConexaoPersistencia();
		
	// Persiste uma informação no banco.
	sigma.applyUpdateTableMaster(pessoa, connection, TypeOperation.INSERT);
		
	sigma.concluirConexao(connection);
			
}

-----------------------------------------------------------------------------------------------------------------
// Principais funcionalidades
public ConnectionLog abrirConexaoPersistencia() throws Exception;
public void concluirConexao(ConnectionLog connectionLog);
public void abortarConexao(ConnectionLog connectionLog);
public void applyUpdateTableMaster(TableMaster tableMaster, ConnectionLog connectionLog, DBOperation operation);
public <E extends TableMaster> List<E extends TableMaster> pesquisaTabela(E extends TableMaster bean) ;

/**
 * Exemplo de persistência de dados
 */
public void exemploPersistênciaDados() {

	// Instância da classe
	SigmaDB sigma = new SigmaDB();

	ConnectionLog connection = sigma.abrirConexaoPersistencia();

	Pessoa pessoa = montaPessoa();
	Contrato contrato = montaContrato();
		
	// Persiste uma informação de pessoa no banco 
	//(TypeOperation.INSERT, TypeOperation.UPDATE, TypeOperation.DELETE).
	sigma.applyUpdateTableMaster(pessoa, connection, TypeOperation.INSERT);

	// Persiste uma informação de contrato no banco 
	//(TypeOperation.INSERT, TypeOperation.UPDATE, TypeOperation.DELETE).
	sigma.applyUpdateTableMaster(contrato, connection, TypeOperation.INSERT);
		
	sigma.concluirConexao(connection);
}

/**
 * Exemplo de consulta de dados
 */
public void exemploConsultaDados() {

	// Instância da classe
	SigmaDB sigma = new SigmaDB();

	// Classe que representa um espelho da tabela Pessoa
	Pessoa pessoa = new Pessoa();
	pessoa.setPess_nome("Igor Moisés");

	// Classe que representa um espelho da tabela Contrato
	Contrato contrato = new Contrato();
	contrato.setCnto_numero("0324");

	// Classe que representa um agrupamento da tabela Pessoa e Contrato
	ContratoPessoa contratoPessoa = new ContratoPessoa();
	contratoPessoa.setPess_nome("Igor Moisés");
	contratoPessoa.setCnto_numero("0324");

	// Realiza uma consulta por uma pessoa que tenha o nome Igor Moisés.
	List<Pessoa> listaPessoa = sigma.pesquisaTabela(pessoa);

	// Realiza uma consulta por um contrato de número 0324.
	List<Contrato> listaContrato = sigma.pesquisaTabela(contrato);

	// Realiza uma consulta com join entre Pessoa e Contrato.
	List<ContratoPessoa> listaJoin = sigma.pesquisaTabela(contratoPessoa);

}

// Informa a ferramenta que o usuário de login "login_usuario" fará uma persistência
ConnectionLog con = sigma.abrirConexaoPersistencia("login_usuario");

// Informa a ferramenta que o usuário de login "login_usuario" fará uma persistência a partir da tela CadastroProcess
ConnectionLog con = sigma.abrirConexaoPersistencia("login_usuario", "CadastroProcess");


/**
 * Exemplo de consulta de dados CommandQuery
 */
public void pesquisaContratoPessoa() {

	SigmaDB sigmaDB = new SigmaDB();

	CommandQuery command = new CommandQuery();
	
	// Informa que a tabela da cláusula FROM da consulta é a tabela representada pela classe Pessoa.
	command.setClausulaFrom(Pessoa.class);

	// Informa por meio do enumerator Join.INNER_JOIN que deve ser aplicada a restrição
	// de JOIN com a tabela Contrato, que é representada pela classe Contrato.
	command.addJoin(Join.INNER_JOIN, Contrato.class, "cnto_pessoa = pess_id");

	// Classe que representa um agrupamento da tabela Pessoa e Contrato
	ContratoPessoa contratoPessoa = new ContratoPessoa();
	contratoPessoa.setPess_nome("Igor Moisés");
	contratoPessoa.setCnto_numero("0324");

	// Realiza uma consulta utilizando as restrições criadas em command e contratoPessoa.
	List<ContratoPessoa> listaJoin = sigma.pesquisaTabela(contratoPessoa, command);
}


/**
 * @param connection Objeto que representa uma conexão aberta com o banco. 
 */
public CommandQuery(ConnectionLog connection)
		
/**
 * Adiciona ordenações a consulta.
 * @param nomeColuna Nome da coluna que receberá o critério de ordenação.
 * @param enumSort Critétio de ordenação, ASC ou DESC.
 * @see EnumSortType
 * @throws Exception
 */
public void addOrdenacao(String nomeColuna, EnumSortType enumSort) throws Exception

/**
 * Adiciona ao objeto uma nova restrição do tipo IN para uma coluna, tendo como referência os valores dos atributos especificados da lista informada.
 * @param nomeColunaTabela Nome da coluna na qual a restrição IN deverá ser aplicada. 
 * @param listaObjetos Lista de qualquer Bean que deverá ter um atributo listado.
 * @param nomeAtributoSelecao Nome de um atributo do bean da lista acima, onde seus respectivos valores serão os valores da restrição do tipo IN.
 * @throws Exception
 */
public void addRestricaoSqlIN(String nomeColunaTabela, List listaObjetos, String nomeAtributoSelecao) throws Exception

/**
 * Adiciona ao objeto uma nova restrição do tipo NOT IN para uma coluna, tendo como referência os valores dos atributos especificados da lista informada.
 * @param nomeColunaTabela Nome da coluna na qual a restrição IN deverá ser aplicada. 
 * @param listaObjetos Lista de qualquer Bean que deverá ter um atributo listado.
 * @param nomeAtributoSelecao Nome de um atributo do bean da lista acima, onde seus respectivos valores serão os valores da restrição do tipo IN.
 * @throws Exception
 */
public void addRestricaoSqlNOT_IN(String nomeColunaTabela, List listaObjetos, String nomeAtributoSelecao) throws Exception

/**
 * Adiciona uma nova restrição ao objeto que será precedida pelo operador lógico AND.<br>
 * Ex. AND nomePropriedade = valorPropriedade
 * @param filtro Objeto contendo as definições da restrição que será adicionada ao objeto.<br>
 * Os tipos de filtro são {@link RelationalOperatorFilter} e {@link NullOperatorFilter}
 * @see RelationalOperatorFilter
 * @see NullOperatorFilter
 * @throws SigmaDBException
 */
public void addRestricaoAND(Filter filtro) throws SigmaDBException

/**
 * Adiciona uma nova restrição ao objeto que será precedida pelo operador lógico AND.<br>
 * Ex. AND nomePropriedade = valorPropriedade
 * @param filtro Objeto contendo as definições da restrição que será adicionada ao objeto.<br>
 * Os tipos de filtro são {@link RelationalOperatorFilter} e {@link NullOperatorFilter}
 * @see RelationalOperatorFilter
 * @see NullOperatorFilter
 * @throws SigmaDBException
 */
public void addRestricaoOR(Filter filtro) throws SigmaDBException

/**
 * Adiciona no objeto a descrição de colunas que não deverão ser retornadas na consulta.
 * @param classe Classe do Bean que contem os atributos que deverão ser desconsiderados na consulta.
 * @param prefixosAtributosExclusao Prefixos ou nome dos atributos que deverão ser listados.
 * @throws Exception
 */
public void addAtributosExclusaoConsulta(Class classe, String...prefixosAtributosExclusao) throws Exception

/**
 * Adiciona uma restrição do tipo like para a consulta.
 * @param nomeColuna Nome da coluna que recebera esta restrição de Like.
 * @param valor Valor do conteúdo do like.
 * @param percentInicio True se o método deve incluir % no início do valor. False se o método não deve incluir % no início do parâmetro valor.
 * @param percentFinal True se o método deve incluir % no final do parâmetro valor. False se o método não deve incluir % no final do parâmetro valor.
 */
public void addRestricaoLike(String nomeColuna, String valor, boolean percentInicio, boolean percentFinal)

/**
 * Insere um join na consulta.
 * @param enumJoin Tipo do Join que será inserido.
 * @param classeTabelaJoin Classe que representa uma tabela do banco de dados e que será adicionada como Join.
 * @param restricao_ON Restriçãoo ON do Join ligando uma tabela a outra. Ex. "nome_fk = nome_pk"
 * @throws SigmaDBException
 */
public void addJoin(Join enumJoin, Class<? extends TableMaster> classeTabelaJoin, String restricao_ON) throws SigmaDBException

/**
 * Insere um join na consulta com a possibilidade de aplicar um alias a tabela deste join.
 * @param enumJoin Tipo do Join que será inserido.
 * @param classeTabelaJoin Classe que representa uma tabela do banco de dados e que será adicionada como Join.
 * @param alias Alias para a tabela que ficará na sintaxe sql. <b>Este mesmo alias deverá estar contido no parâmetro restricao_ON.</b>
 * @param restricao_ON Restriçãoo ON do Join ligando uma tabela a outra. Ex. "z.nome_fk = nome_pk"
 * @throws SigmaDBException
 */
public void addJoin(Join enumJoin, Class<? extends TableMaster> classeTabelaJoin, String alias, String restricao_ON) throws SigmaDBException

/**
 * Insere o nome da tabela para qual o objeto irá apontar como a tabela "FROM" na geração da consulta.
 * @param classeTabelaFrom Classe que represente qualquer tabela do banco de dados. 
 * @throws SigmaDBException
 */
public void setClausulaFrom(Class<? extends TableMaster> classeTabelaFrom) throws SigmaDBException


/**
 * Classe que apresenta um exemplo de consultas com sintaxe SQL pela ferramenta
 */
public class PessoaDAO extends SigmaDB {

	/**
 	 * Apresenta um exemplo de consultas com sintaxe SQL pela ferramenta
 	 * @param pessoa Objeto contendo as restrições da consulta.
 	 * @return Lista de objetos do tipo @link{ContratoPessoa} contendo o resultado da consulta.
 	 */
	public List<ContratoPessoa> pesquisaPessoa(ContratoPessoa pessoa) {

		String sql = "select * from Pessoa " + 
						" inner join contrato on cnto_pessoa = pess_id";

		// Realiza uma consulta cujo o retorno será uma lista contendo instâncias do objeto de restrição informado.				
		return pesquisaTabela(sql, pessoa);
	}

	/**
 	 * Apresenta um exemplo de consultas com sintaxe SQL pela ferramenta
 	 * @param pessoa Objeto contendo as restrições da consulta.
 	 * @param command Objeto contendo restrições complementares a consulta
 	 * @return Lista de objetos do tipo @link{ContratoPessoa} contendo o resultado da consulta.
 	 */
	public List<ContratoPessoa> pesquisaPessoa(ContratoPessoa pessoa, CommandQuery command) {

		String sql = "select * from Pessoa " + 
						" inner join contrato on cnto_pessoa = pess_id";

		// Realiza uma consulta cujo o retorno será uma lista contendo instâncias do objeto de restrição informado.
		// Aplica as restrições definidas na estrutura de CommandQuery a consulta.				
		return pesquisaTabela(sql, pessoa, command);
	}
}



public class Pessoa extends TableMaster{
	@PKTableMaster
    private int pess_id;
    private int pess_tipo;
    private String pess_nome;
    private Timestamp pess_nascimento;
    private String pess_cpf;
    private String pess_rg;
    private int pess_departamento;
	// gets e sets dos atributos
	...
}

public class Departamento extends TableMaster{
	@PKTableMaster
    private int dept_id;
    private String dept_descricao;
	// gets e sets dos atributos
	...
}    

public class Tipo_pessoa extends TableMaster{
	@PKTableMaster
	private int tppe_id;
	private String tppe_descricao;
	// gets e sets dos atributos
	...
}

public class PessoaDepartamento {
	private Pessoa pessoa = new Pessoa();

	@FkTableMaster(on = "pess_departamento = dept_id")
	private Departamento departamento = new Departamento();

	// delegação dos métodos dos atributos
	...
}


private static String url = "DATABASE_URL";
private static String user = "admin";
private static String password = "senha_admin";

public void insereDepartamento(Departamento departamento) throws Exception{
		
	// (1) Estabelece uma conexão com o banco de dados
	Connection connection = DriverManager.getConnection(url, user, password);

	// (2) Cria Statement para persistir no banco de dados
	Statement stmt = con.createStatement();
		
	String sql = "insert into Departamento (dpt_descricao) values ('" + departamento.getDept_descricao() + "')";
	
	// (3) Persiste dado no banco	
	stmt.executeUpdate(sql);
}

public void alteraDepartamento(Departamento departamento) throws Exception{
		
	// (1) Estabelece uma conexão com o banco de dados
	Connection connection = DriverManager.getConnection(url, user, password);

	// (2) Cria Statement para persistir no banco de dados
	Statement stmt = con.createStatement();
		
	String sql = "update Departamento set dept_descricao = '" + departamento.getDept_descricao + "'" +
				 " where dept_id = " + departamento.gerDept_id();
	
	// (3) Persiste dado no banco	
	stmt.executeUpdate(sql);
}

public void deletaDepartamento(Departamento departamento) throws Exception{
		
	// (1) Estabelece uma conexão com o banco de dados
	Connection connection = DriverManager.getConnection(url, user, password);

	// (2) Cria Statement para persistir no banco de dados
	Statement stmt = con.createStatement();
		
	String sql = "delete from Departamento where dept_id = " + departamento.gerDept_id();
	
	// (3) Persiste dado no banco	
	stmt.executeUpdate(sql);
}



public void exemploConsultaDados() {

	// Instância da classe
	SigmaDB sigma = new SigmaDB();

	// Classe que representa um espelho da tabela Pessoa
	Pessoa pessoa = new Pessoa();
	pessoa.setPess_nome("Igor Moisés");
	
	// Classe que representa um espelho da tabela Departamento
	Departamento departamento = new Departamento();
	departamento.setDept_id(1);
	
	// Classe que representa um agrupamento da tabela Pessoa e Contrato
	PessoaDepartamento pessoaDepartamento = new PessoaDepartamento();
	pessoaDepartamento.setPess_nome("Igor Moisés");
	pessoaDepartamento.setDept_id(1);

	List<Pessoa> listaPessoa = sigma.pesquisaTabela(pessoa);
	//SAÍDA: Select * from pessoa where pess_nome = 'Igor Moisés';
	
	List<Contrato> listaContrato = sigma.pesquisaTabela(departamento);
	//SAÍDA: Select * from departamento where dept_id = 1;

	List<PessoaDepartamento> listaJoin = sigma.pesquisaTabela(pessoaDepartamento);

	//SAÍDA: Select * from pessoa
	//			inner join departamento on pess_departamento = dept_id
	//		 where dept_id = 1 and pess_nome = 'Igor Moisés';
}


public void exemploConsultaDados() {

	// Instância da classe
	SigmaDB sigma = new SigmaDB();

	// Classe que representa um espelho da tabela Pessoa
	Pessoa pessoa = new Pessoa();
	pessoa.setPess_nome("Igor Moisés");
	

	List<Pessoa> listaPessoa = sigma.pesquisaTabela(pessoa);
	//SAÍDA: Select * from pessoa where pess_nome = 'Igor Moisés';
}


public void exemploConsultaDados() {

	// Instância da classe
	SigmaDB sigma = new SigmaDB();

	// Classe que representa um espelho da tabela Pessoa
	Pessoa pessoa = new Pessoa();
	pessoa.setPess_nome("Igor Moisés");	

	List<Pessoa> listaPessoa = sigma.pesquisaTabela(pessoa);
	
	SAÍDA: Select * from pessoa where pess_nome = 'Igor Moisés';
}



public void exemploPersistênciaDados() {

	// Instância da classe
	SigmaDB sigma = new SigmaDB();

	ConnectionLog connection = sigma.abrirConexaoPersistencia();

	Pessoa pessoa = montaPessoa();
		
	// Persiste uma informação de pessoa no banco 
	(TypeOperation.INSERT, TypeOperation.UPDATE, TypeOperation.DELETE).
	sigma.applyUpdateTableMaster(pessoa, connection, TypeOperation.INSERT);
		
	sigma.concluirConexao(connection);
}


