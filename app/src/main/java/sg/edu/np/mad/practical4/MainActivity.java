package sg.edu.np.mad.practical4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receivingEnd = getIntent();
        Integer position = receivingEnd.getIntExtra("position",0);
        User user = ListActivity.userList.get(position);

        TextView namet = findViewById(R.id.username);
        TextView desct = findViewById(R.id.userdesc);

        namet.setText(String.format("%s", user.name));
        desct.setText(String.format("%s",user.description));

        Button button = findViewById(R.id.followbutton);
        //User user = initUser();
        setFollowing(user,button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toasted;
                if(user.followed == false){
                    user.followed = true;
                    toasted = "Followed";
                }
                else{
                    user.followed = false;
                    toasted = "Unfollowed";
                }
                setFollowing(user,button);
                Toast toastn = Toast.makeText(MainActivity.this, toasted,Toast.LENGTH_SHORT);
                toastn.show();
            }
        });

        Button msgbtn = findViewById(R.id.messagebutton);
        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(i);
            }
        });

    }
    public User initUser(){
        User user = new User("Username","Description",1,false);
        return user;
    }

    public void setFollowing(User user, Button button){
        TextView txt = button;
        if(user.followed == false){
            txt.setText("Follow");
        }
        else{
            txt.setText("Unfollow");
        }
    }
}