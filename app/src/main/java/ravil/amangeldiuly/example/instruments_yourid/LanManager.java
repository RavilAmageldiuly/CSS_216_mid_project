package ravil.amangeldiuly.example.instruments_yourid;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanManager {

    private Context mContext;
    public LanManager(Context context){
        mContext = context;
    }
    public void update(String context){
        Locale locale = new Locale(context);
        Locale.setDefault(locale);

        Resources resources = mContext.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale =  locale;
        resources.updateConfiguration( configuration, resources.getDisplayMetrics());
    }
}