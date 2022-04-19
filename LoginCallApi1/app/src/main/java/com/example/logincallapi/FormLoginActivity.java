package com.example.logincallapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logincallapi.api.ApiService;

import java.util.regex.Pattern;

import okhttp3.internal.framed.ErrorCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormLoginActivity<isHasUser> extends AppCompatActivity {
    EditText txtmk;
    Button btnlogin;
    //public static final String BUNDLE = "BUNDLE";
    //private User mUser;
    //private List<User> mListUser;
    String sDT;
    //private List<User> mListUser;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 4 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        txtmk = (EditText) findViewById(R.id.mk);
        btnlogin = (Button) findViewById(R.id.btndangnhap);

        //mListUser = new ArrayList<>();
        //senduser();

        //Lấy dữ liệu từ bundle của form 1
        Bundle bundle = getIntent().getBundleExtra(MainActivity.BUNDLE);
        if(bundle != null){
            sDT = bundle.getString("sdt");
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        String strmk = txtmk.getText().toString().trim();
        String strsdt = sDT;

        if(!validatePassWord()){
            return;
        }
        //if(mUser == null ){
        //    return;
       // }

       // boolean isHasUser = false;

       // if(strsdt.equals(mUser.getSoDienThoai()) && strmk.equals(mUser.getPassWord())) {
       //         isHasUser = true;
       // }

      //  if(isHasUser){
        //   Intent intent = new Intent(FormLoginActivity.this,HomeActivity.class);
         //   Bundle bundle = new Bundle();
         //   bundle.putSerializable("object user", mUser);
        //    intent.putExtras(bundle);
         //   startActivity(intent);
       // }else{
        //    Toast.makeText(FormLoginActivity.this, "giá trị của sdt và pass không tồn tại !", Toast.LENGTH_SHORT).show();
       // }
        senduser();
    }

    private void senduser() {
        String strmk = txtmk.getText().toString().trim();
        String strsdt = sDT;
        //User user = new User("0943813049","Bmis@3049",171,"12","Android","auto");
        User user = new User(strsdt, strmk, 171, "12", "Android", "auto");

        ApiService.apiservice.senduser(user).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response != null){
                    if(response.body() != null){
                        Toast.makeText(FormLoginActivity.this, "Call Api thành công", Toast.LENGTH_SHORT).show();
                        String token = response.body().getToken();
                        int errorcode =response.body().getErrorCode();
                        String errordesc = response.body().getErrorDesc();
                        String tencudan = response.body().getTenCuDan();
                        Result result =  new Result(errorcode,errordesc,token,tencudan);

                        Intent intent = new Intent(FormLoginActivity.this,HomeActivity.class);
                        Bundle bundle =  new Bundle();
                        bundle.putSerializable("object result",result);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(FormLoginActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validatePassWord() {
        String val = txtmk.getText().toString().trim();
        if(val.isEmpty()){
            txtmk.setError("không được để trống");
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(val).matches()){
            txtmk.setError("Mật khẩu ít nhất 6 ký tự");
            return false;
        }
        else{
            txtmk.setError(null);
            txtmk.setEnabled(false);
            return true;
        }
    }

    //Hàm quay lại màn hình chính
    public void backFromLogin(View view)
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        //tạo animation cho thành phần
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.layoutLogin),"transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(FormLoginActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }
}