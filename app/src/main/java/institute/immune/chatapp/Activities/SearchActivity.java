package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import institute.immune.chatapp.R;

public class SearchActivity extends AppCompatActivity {
    private TextView politicBt, sportBt, movieBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindings();
        setListeners();
    }

    private void bindings(){
        politicBt = findViewById(R.id.politicTview);
        sportBt = findViewById(R.id.sportsTview);
        movieBt = findViewById(R.id.moviesTview);
    }

    private void setListeners() {
        politicBt.setOnClickListener(listener);
        sportBt.setOnClickListener(listener);
        movieBt.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chatapp, menu);
        return true;
    }

    public View.OnClickListener menuCentralListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    public MenuItem.OnMenuItemClickListener menuListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = null;
            switch (menuItem.getItemId()){
                case R.id.itemChat:
                    intent = new Intent(menuItem.getActionView().getContext(), ChatActivity.class);
                    break;

                case R.id.itemPerson:
                    intent = new Intent(menuItem.getActionView().getContext(), ProfileActivity.class);
                    break;
            }
            startActivity(intent);
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.itemChat:
                intent = new Intent(this, ChatActivity.class);
                break;

            case R.id.itemPerson:
                intent = new Intent(this, ProfileActivity.class);
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ChatActivity.class);
            intent.putExtra("idText", (Integer) view.getId());
            startActivity(intent);
        }
    };
}