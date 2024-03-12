package com.example.projetintp2_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetintp2_android.ui.login.LoginActivity;

import Interfaces.InterfaceServeur;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    TextView tvWelcome;
    ImageView ivLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_profile);
        tvWelcome=findViewById(R.id.tvWelcome);
        ivLogout= findViewById(R.id.ivLogout);

        Users user = SharedPrefManager.getInstance(this).getUser();
        tvWelcome.setText("bienvenu "+ user.getName());
    }
    protected void onStart()
    {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            Users user = SharedPrefManager.getInstance(this).getUser();
            tvWelcome.setText("bienvenu "+ user.getName());
        /*
            Intent intent = new Intent(this,LoginActivitytest.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

*/
            //if()
        }

}
        public void Logout(View view)
        {
            // Effacer les informations d'authentification stockées localement
            SharedPrefManager.getInstance(this).clear();

            // Rediriger l'utilisateur vers la page de connexion
            Intent intent = new Intent(this, LoginActivitytest.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish(); // Fermer l'activité actuelle pour empêcher l'utilisateur de revenir en arrière
        }
}