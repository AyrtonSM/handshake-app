package com.example.handshaking.Activities.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActionBar;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handshaking.Entity.Chat;
import com.example.handshaking.Model.AsyncChatImage;
import com.example.handshaking.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.microedition.khronos.egl.EGLDisplay;

import static androidx.appcompat.widget.AppCompatDrawableManager.get;

public class ChatActivity extends AppCompatActivity {

    public Button send;
    public LinearLayout chatWindowLayout;
    public EditText messageField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Chat chat = (Chat) getIntent().getSerializableExtra("chat_info");
        try {

            TextView tv = findViewById(R.id.user_information_chat_name);
            tv.setText(chat.getUserName());

//            String url = "https://static-cdn.123rf.com/images/v5/index-thumbnail/84170952-b.jpg";
            String url = "https://www.springboard.com/images/springboard/default-profile-mentor-rounded@2x.70dc0c67.png";
            AsyncTask<String, Void, Bitmap> asyncTaskBitMap = new AsyncChatImage().execute(url);
            Bitmap bmp = asyncTaskBitMap.get();


            CardView cv = findViewById(R.id.chat_image_holder);

            Resources res = getResources();
            Drawable drawable = new BitmapDrawable(getResources(), bmp);
            cv.setBackground(drawable);

        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        chatWindowLayout = findViewById(R.id.chat_window_layout);
        messageField = findViewById(R.id.message_field);
        send = findViewById(R.id.send_message);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = messageField.getText().toString();
                TextView messageView = new TextView(getApplicationContext());
                messageView.setText(message);

                messageView.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                        ActionBar.LayoutParams.WRAP_CONTENT,
                                        ActionBar.LayoutParams.WRAP_CONTENT)
                            );

                messageView.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.message_background_user));

                messageView.setTextColor(getResources().getColor(android.R.color.white));

                int dpPaddingValue = pxToDp(40);

                messageView.setPadding(dpPaddingValue,dpPaddingValue,dpPaddingValue,dpPaddingValue);

                LinearLayout.LayoutParams vlp = (LinearLayout.LayoutParams) messageView.getLayoutParams();
                vlp.setMargins(5,5,5,5);
                vlp.gravity = Gravity.RIGHT;
                messageView.setLayoutParams(vlp);
                messageView.requestFocus();
                chatWindowLayout.addView(messageView);
                chatWindowLayout.requestChildFocus(messageView, messageView);
                messageField.setText("");




            }
        });


    }

    private int pxToDp(int px) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
