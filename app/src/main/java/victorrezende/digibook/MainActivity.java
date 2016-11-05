package victorrezende.digibook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DataBase dataBase;
    private SQLiteDatabase bd1;
    private MediaPlayer mp1, mp2, touch, background;
    private ImageView imgEstado, ivcaminho1, ivcaminho2;
    private TextView tvestado, tvcaminho1, tvcaminho2;
    public static int contador=0;
    String estado = "";
    String caminho1 = "";
    String caminho2 = "";
    String estadoimg = "";
    String caminho1img = "";
    String caminho2img = "";
    String estadotxt = "";
    String caminho1txt = "";
    String caminho2txt = "";
    String audioEstado = "";
    int audioCaminho1 = 0;
    int audioCaminho2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = MediaPlayer.create(MainActivity.this, R.raw.background);
        background.start();
        imgEstado = (ImageView) findViewById(R.id.img1);
        ivcaminho1 = (ImageView) findViewById(R.id.img2);
        ivcaminho2 = (ImageView) findViewById(R.id.img3);
        tvestado = (TextView) findViewById(R.id.tv1);
        tvcaminho1 = (TextView) findViewById(R.id.tv2);
        tvcaminho2 = (TextView) findViewById(R.id.tv3);
        touch = MediaPlayer.create(MainActivity.this, R.raw.button09);


        try {
            dataBase = new DataBase(this);
            bd1 = dataBase.getWritableDatabase();
            //inserAcao();
            //insereGrafo();


            //bd1.delete("GRAFO", null, null);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        preencher("1");
    }

    public void preencher(String acao) {
        Cursor c = bd1.rawQuery("SELECT a.ACAO as ESTADO, b.ACAO as CAMINHO1, c.ACAO as CAMINHO2,a.IMG as ESTADOIMG,b.IMG as CAMINHO1IMG,c.IMG as CAMINHO2IMG, d.ACAO as ESTADO, e.ACAO as CAMINHO1, f.ACAO as CAMINHO2, d.TXT as ESTADOTXT, e.TXT as CAMINHO1TXT, f.TXT as CAMINHO2TXT, g.AUDIO as AUDIOESTADO, h.AUDIO as AUDIOCAMINHO1, i.AUDIO as AUDIOCAMINHO2 FROM GRAFO INNER JOIN TBLAACOES as a ON GRAFO.ESTADO=a.ACAO INNER JOIN TBLAACOES as b ON GRAFO.CAMINHO1=b.ACAO INNER JOIN TBLAACOES as c ON GRAFO.CAMINHO2=c.ACAO INNER JOIN TBLAACOES as d ON GRAFO.ESTADO=d.ACAO INNER JOIN TBLAACOES as e ON GRAFO.CAMINHO1=e.ACAO INNER JOIN TBLAACOES as f ON GRAFO.CAMINHO2=f.ACAO INNER JOIN TBLAACOES as g ON GRAFO.ESTADO=g.ACAO INNER JOIN TBLAACOES as h ON GRAFO.CAMINHO1=h.ACAO INNER JOIN TBLAACOES as i ON GRAFO.CAMINHO2=i.ACAO WHERE GRAFO.ESTADO = '" + acao + "' ", null);

        if (c.moveToFirst()) {
            do {
                estado = c.getString(c.getColumnIndex("ESTADO"));
                caminho1 = c.getString(c.getColumnIndex("CAMINHO1"));
                caminho2 = c.getString(c.getColumnIndex("CAMINHO2"));
                estadoimg = c.getString(c.getColumnIndex("ESTADOIMG"));
                caminho1img = c.getString(c.getColumnIndex("CAMINHO1IMG"));
                caminho2img = c.getString(c.getColumnIndex("CAMINHO2IMG"));
                estadotxt = c.getString(c.getColumnIndex("ESTADOTXT"));
                caminho1txt = c.getString(c.getColumnIndex("CAMINHO1TXT"));
                caminho2txt = c.getString(c.getColumnIndex("CAMINHO2TXT"));
                audioEstado = c.getString(c.getColumnIndex("AUDIOESTADO"));
                audioCaminho1 = c.getInt(c.getColumnIndex("AUDIOCAMINHO1"));
                audioCaminho2 = c.getInt(c.getColumnIndex("AUDIOCAMINHO2"));

            } while (c.moveToNext());
        }

        imgEstado.setImageDrawable(ContextCompat.getDrawable(this, Integer.parseInt(estadoimg)));

        ivcaminho1.setImageDrawable(ContextCompat.getDrawable(this, Integer.parseInt(caminho1img)));

        ivcaminho2.setImageDrawable(ContextCompat.getDrawable(this, Integer.parseInt(caminho2img)));

        tvestado.setText(estadotxt);

        tvcaminho1.setText(caminho1txt);

        tvcaminho2.setText(caminho2txt);

        mp1 = MediaPlayer.create(MainActivity.this, audioCaminho1);

        mp2 = MediaPlayer.create(MainActivity.this, audioCaminho2);


        // mp2.selectTrack(Integer.parseInt(audioCaminho2));


        ivcaminho1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch.start();
                mp1.start();
                preencher(caminho1);

            }
        });

        ivcaminho2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch.start();
                mp2.start();
                preencher(caminho2);

            }
        });
    }

    public void insereGrafo() {
        ContentValues valores = new ContentValues();
        valores.put("ESTADO", 1);
        valores.put("CAMINHO1", 30);
        valores.put("CAMINHO2", 2);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 2);
        valores.put("CAMINHO1", 3);
        valores.put("CAMINHO2", 4);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 3);
        valores.put("CAMINHO1", 5);
        valores.put("CAMINHO2", 6);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 4);
        valores.put("CAMINHO1", 5);
        valores.put("CAMINHO2", 6);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 5);
        valores.put("CAMINHO1", 10);
        valores.put("CAMINHO2", 11);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 6);
        valores.put("CAMINHO1", 7);
        valores.put("CAMINHO2", 9);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 7);
        valores.put("CAMINHO1", 30);
        valores.put("CAMINHO2", 9);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 8);
        valores.put("CAMINHO1", 30);
        valores.put("CAMINHO2", 9);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 9);
        valores.put("CAMINHO1", 30);
        valores.put("CAMINHO2", 29);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 10);
        valores.put("CAMINHO1", 11);
        valores.put("CAMINHO2", 12);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 11);
        valores.put("CAMINHO1", 8);
        valores.put("CAMINHO2", 12);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 12);
        valores.put("CAMINHO1", 13);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 13);
        valores.put("CAMINHO1", 14);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 14);
        valores.put("CAMINHO1", 15);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 15);
        valores.put("CAMINHO1", 16);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 16);
        valores.put("CAMINHO1", 17);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 17);
        valores.put("CAMINHO1", 30);
        valores.put("CAMINHO2", 18);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 18);
        valores.put("CAMINHO1", 30);
        valores.put("CAMINHO2", 19);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 19);
        valores.put("CAMINHO1", 20);
        valores.put("CAMINHO2", 21);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 20);
        valores.put("CAMINHO1", 22);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 21);
        valores.put("CAMINHO1", 22);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 22);
        valores.put("CAMINHO1", 23);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 23);
        valores.put("CAMINHO1", 24);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 24);
        valores.put("CAMINHO1", 25);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 25);
        valores.put("CAMINHO1", 26);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 26);
        valores.put("CAMINHO1", 27);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 27);
        valores.put("CAMINHO1", 28);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 28);
        valores.put("CAMINHO1", 29);
        valores.put("CAMINHO2", 30);
        bd1.insert("GRAFO", null, valores);
        valores.clear();

        valores.put("ESTADO", 29);
        valores.put("CAMINHO1", 30);
        valores.put("CAMINHO2", 1);
        bd1.insert("GRAFO", null, valores);
        valores.clear();





    }

    public void inserAcao() {
        ContentValues valores = new ContentValues();
        valores.put("ACAO", "1");
        valores.put("IMG", R.drawable.img1);
        valores.put("TXT", "Iniciar Jogo");
        valores.put("AUDIO", R.raw.audio1);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "2");
        valores.put("IMG", R.drawable.img2);
        valores.put("AUDIO", R.raw.audio2);
        valores.put("TXT", "Call Gramma");
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "3");
        valores.put("IMG", R.drawable.img3);
        valores.put("TXT", "Sair sem avisar a Mãe");
        valores.put("AUDIO", R.raw.audio3);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "4");
        valores.put("IMG", R.drawable.img4);
        valores.put("TXT", "Sair avisando a Mãe");
        valores.put("AUDIO", R.raw.audio4);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "5");
        valores.put("IMG", R.drawable.img5);
        valores.put("TXT", "Colher Flores");
        valores.put("AUDIO", R.raw.audio5);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "6");
        valores.put("IMG", R.drawable.img6);
        valores.put("TXT", "Ir pela cidade");
        valores.put("AUDIO", R.raw.audio6);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "7");
        valores.put("IMG", R.drawable.img7);
        valores.put("TXT", "Have icecream");
        valores.put("AUDIO", R.raw.audio7);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "8");
        valores.put("IMG", R.drawable.img8);
        valores.put("TXT", "Seguir estrada");
        valores.put("AUDIO", R.raw.audio8);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "9");
        valores.put("IMG", R.drawable.img9);
        valores.put("TXT", "ir para casa da Avó");
        valores.put("AUDIO", R.raw.audio9);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "10");
        valores.put("IMG", R.drawable.img10);
        valores.put("TXT", "Encontrar o lobo");
        valores.put("AUDIO", R.raw.audio10);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "11");
        valores.put("IMG", R.drawable.img11);
        valores.put("TXT", "Desviar do lobo");
        valores.put("AUDIO", R.raw.audio11);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "12");
        valores.put("IMG", R.drawable.img12);
        valores.put("TXT", "Encontrar lobo");
        valores.put("AUDIO", R.raw.audio12);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "13");
        valores.put("IMG", R.drawable.img13);
        valores.put("TXT", "Acreditar no lobo");
        valores.put("AUDIO", R.raw.audio13);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "14");
        valores.put("IMG", R.drawable.img14);
        valores.put("TXT", "Lobo indo pra Vovozinha");
        valores.put("AUDIO", R.raw.audio14);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "15");
        valores.put("IMG", R.drawable.img15);
        valores.put("TXT", "É você chapeuzinho?");
        valores.put("AUDIO", R.raw.audio15);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "16");
        valores.put("IMG", R.drawable.img16);
        valores.put("TXT", "Lobo devora Vovozinha");
        valores.put("AUDIO", R.raw.audio16);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "17");
        valores.put("IMG", R.drawable.img17);
        valores.put("TXT", "A chegada da Chapeuzinho");
        valores.put("AUDIO", R.raw.audio17);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "18");
        valores.put("IMG", R.drawable.img18);
        valores.put("TXT", "Vovozinha? Trouxe comida!");
        valores.put("AUDIO", R.raw.audio18);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "19");
        valores.put("IMG", R.drawable.img19);
        valores.put("TXT", "Vovó?");
        valores.put("AUDIO", R.raw.audio19);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "20");
        valores.put("IMG", R.drawable.img20);
        valores.put("TXT", "Bater no lobo");
        valores.put("AUDIO", R.raw.audio20);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "21");
        valores.put("IMG", R.drawable.img21);
        valores.put("TXT", "Correr");
        valores.put("AUDIO", R.raw.audio21);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "22");
        valores.put("IMG", R.drawable.img22);
        valores.put("TXT", "Correr para fora da casa");
        valores.put("AUDIO", R.raw.audio22);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "23");
        valores.put("IMG", R.drawable.img23);
        valores.put("TXT", "Parado lobo!");
        valores.put("AUDIO", R.raw.audio23);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "24");
        valores.put("IMG", R.drawable.img24);
        valores.put("TXT", "Fazer lobo dormir");
        valores.put("AUDIO", R.raw.audio24);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "25");
        valores.put("IMG", R.drawable.img25);
        valores.put("TXT", "Tirar Vovó do lobo.");
        valores.put("AUDIO", R.raw.audio25);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "26");
        valores.put("IMG", R.drawable.img26);
        valores.put("TXT", "Vovozinha está viva.");
        valores.put("AUDIO", R.raw.audio26);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "27");
        valores.put("IMG", R.drawable.img27);
        valores.put("TXT", "Fim");
        valores.put("AUDIO", R.raw.audio27);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "28");
        valores.put("IMG", R.drawable.img28);
        valores.put("AUDIO", R.raw.audio27);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "29");
        valores.put("IMG", R.drawable.imgpara);
        valores.put("AUDIO", R.raw.parab);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

        valores.put("ACAO", "30");
        valores.put("IMG", R.drawable.imgbranco);
        valores.put("AUDIO", R.raw.button09);
        bd1.insert("TBLAACOES", null, valores);
        valores.clear();

    }
}
