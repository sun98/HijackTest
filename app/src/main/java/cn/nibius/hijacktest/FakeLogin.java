package cn.nibius.hijacktest;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FakeLogin extends Activity {
    private LinearLayout mBtnLogin;
    private EditText mEdtUser;
    private EditText mEdtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("nib", "onCreate: ");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fake_login);

        mBtnLogin = findViewById(R.id.login_in);
        mEdtUser = findViewById(R.id.email_info_edit);
        mEdtPwd = findViewById(R.id.password_info_edit);

//        TextView qql = findViewById(R.id.qqlogin);
//        Drawable d = getResources().getDrawable(R.drawable.ic_login_qq);
//        d.setBounds(0, 0, d.getIntrinsicWidth() / 2, d.getIntrinsicHeight() / 2);
//        qql.setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = mEdtUser.getText().toString();
                String pw = mEdtPwd.getText().toString();
                Toast.makeText(getApplicationContext(), un + ", " + pw, Toast.LENGTH_LONG).show();
                MainActivity.up.put(un, pw);
                moveTaskToBack(true);
            }
        });


    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
