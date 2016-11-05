package victorrezende.digibook;

/**
 * Created by VICTOR on 29/06/2016.
 */
public class ScriptSQL {

    public static String getCreateGrafo(){


        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS GRAFO ( ");
        sqlBuilder.append("_id                INTEGER       NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("ESTADO           VARCHAR (1), ");
        sqlBuilder.append("CAMINHO1         VARCHAR (1), ");
        sqlBuilder.append("CAMINHO2         VARCHAR (1) ");
        sqlBuilder.append(" );");

        return sqlBuilder.toString();

    }

    public static String getCreateAcao(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS TBLAACOES ( ");
        sqlBuilder.append("_id                INTEGER   ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("ACAO           VARCHAR (20), ");
        sqlBuilder.append("IMG           INTEGER (20), ");
        sqlBuilder.append("TXT           VARCHAR (50), ");
        sqlBuilder.append("AUDIO           INTERGER (50) ");
        sqlBuilder.append(" );");
        return sqlBuilder.toString();
    }

}

