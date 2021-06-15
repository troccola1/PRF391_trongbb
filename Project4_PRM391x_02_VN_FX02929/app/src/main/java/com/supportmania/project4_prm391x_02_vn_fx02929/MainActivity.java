package com.supportmania.project4_prm391x_02_vn_fx02929;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    // Declare TAG
    private static final String TAG = "SignInActivity";
    // Declare CallbackManager
    private CallbackManager mCallbackManager;
    // Build a GoogleSignInClient with the options specified by gso.
    private GoogleSignInClient mGoogleSignInClient;
    // Declare RC_SIGN_IN
    private static final int RC_SIGN_IN = 9001;
    // Declare information facebook, google.
    String name, email, id, personName, personEmail, personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // callbackManager to handle login responses by calling
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setPermissions(Arrays.asList("public_profile", "email"));
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken1) {

                    }
                };
                accessTokenTracker.startTracking();

                ProfileTracker profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profile, Profile profile1) {

                    }
                };
                profileTracker.startTracking();

                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    // App code
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            (object, response) -> {
                                Log.d("JSON", response.getJSONObject().toString());

                                // Application code
                                try {
                                    email = object.getString("email");
                                    name = object.getString("name");
                                    id = Profile.getCurrentProfile().getId();

                                    Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
                                    intent.putExtra("loginType", "facebook");
                                    intent.putExtra("email", email);
                                    intent.putExtra("name", name);
                                    intent.putExtra("id", id);
                                    startActivityForResult(intent, Constants.LOGIN_BUTTON);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "name,email");
                    request.setParameters(parameters);
                    request.executeAsync();//get data here
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(MainActivity.this, "Đăng nhập không thành công.", Toast.LENGTH_LONG).show();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.sign_in_button).setOnClickListener(v -> signIn());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    // TODO: Starting the intent prompts the user to select a Google account to sign in with
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // TODO: Pass the login results
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the login results to the LoginManager via callbackManager.
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        if (requestCode == Constants.LOGIN_BUTTON) {
            LoginManager.getInstance().logOut();
        }
        if (requestCode == Constants.SIGN_IN_BUTTON) {
            signOut();
        }
    }

    // TODO: The GoogleSignInAccount object contains information about the signed-in user
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                personName = acct.getDisplayName();
                personEmail = acct.getEmail();
                personPhoto = Objects.requireNonNull(acct.getPhotoUrl()).toString();
            }

            // Signed in successfully, show authenticated UI.
            updateUI(account);
            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
            intent.putExtra("loginType", "google");
            intent.putExtra("personPhoto", personPhoto);
            intent.putExtra("personEmail", personEmail);
            intent.putExtra("personName", personName);
            startActivityForResult(intent, Constants.SIGN_IN_BUTTON);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);

        } else {
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        }
    }

    // TODO: start sign out
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    // start exclude
                    updateUI(null);
                    // end exclude
                });
    }

}