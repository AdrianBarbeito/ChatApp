package institute.immune.chatapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import institute.immune.chatapp.R;
import institute.immune.chatapp.Services.Bot;
import institute.immune.chatapp.Services.Bot2;
import institute.immune.chatapp.Services.Bot3;

public class ChatActivity extends AppCompatActivity {
    Intent intentService = null;
    private ImageView profileImage;
    private TextView nickNameTView, sendTView, receivedTView;;
    private EditText writeInput;
    private ImageButton sendBt;
    LinearLayout messageFrame;
    View sentmessage, receivedmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        bindings();

        selectBot();
    }

    private void bindings(){
        profileImage = findViewById(R.id.imageUserChat);
        nickNameTView = findViewById(R.id.nicknameChat);
        writeInput = findViewById(R.id.msgEtext);
        sendBt = findViewById(R.id.sendBt);
        sendBt.setOnClickListener(messageListener);
        messageFrame = findViewById(R.id.chatLayout);
    }
    /**
     * Recoge la categoría para elegir el bot correspondiente (futuro usuario online)
     * Establece el nombre del contacto con la categoría ya que se tratan de bots, pero se podría cambiar por el nombre del usuario.
     */
    public void selectBot(){

    String category = getIntent().getStringExtra("category");
    switch (category) {
        case "Politic":
            intentService = new Intent(this, Bot.class);
            break;
        case "Sports":
            intentService = new Intent(this, Bot2.class);
            break;
        case "Movies":
            intentService = new Intent(this, Bot3.class);
            break;

    }
    nickNameTView.setText(category);
    startService(intentService);
}

    /**
     * Cuando se envía un mensaje llama a la función añadir mensaje
     */
    public View.OnClickListener messageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addMessages();
        }
    };

    /**
     * Envia el mensaje escrito y eañade el de respuesta
     */
    public void addMessages(){
        sentmessage = getLayoutInflater().inflate(R.layout.message_sent_layout, null, false);
        receivedmessage = getLayoutInflater().inflate(R.layout.message_received_layout, null, false);

        sendTView = sentmessage.findViewById(R.id.messagesent);
        receivedTView = receivedmessage.findViewById(R.id.messagereceived);

        sendTView.setText(writeInput.getText());
        receivedTView.setText(chatWord(writeInput.getText().toString()));

        messageFrame.addView(sentmessage);
        messageFrame.addView(receivedmessage);
    }

    /**
     * Devuelve el mensaje enviado, en un futuro debería cambiarse por una conversación desarrollada con el bot u otro usuario.
     * @param word
     * @return
     */
    private String chatWord(String word) {
        //Aqui se debería hacer la logíca para saber la respuesta y entonces hacer el return
        return word;
    }
}
