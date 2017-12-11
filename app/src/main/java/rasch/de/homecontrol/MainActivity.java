package rasch.de.homecontrol;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    public static final String PREF_FILE_NAME = "raschHomeControl";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(PREF_FILE_NAME,  MODE_WORLD_WRITEABLE);
        String urlText = preferences.getString("HOME_URL", null);
        if (urlText == null) 
        {
            dialogSetting();
            urlText = preferences.getString("HOME_URL", null);
        }
        
        WebView webview = (WebView)findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);
        //webview.setWebViewClient(new webClient());
        //"http://192.168.98.114/hc/hc.php"
        
        webview.loadUrl( urlText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            dialogSetting();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    
    private void dialogSetting() {

        try{

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.settings_dlg);
            dialog.setTitle("Settings..." );

            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            TextView dialogUrl = (TextView) dialog.findViewById(R.id.textUrl);

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    SharedPreferences.Editor editor = preferences.edit();
                    editor = preferences.edit();
                    editor.putString("HOME_URL", nextUrl);
                    editor.commit();

                    
                    
                    dialog.dismiss();
                }
            });
            dialog.show();

        }catch(Exception e) {
        }
    }	
    
    
    
}
