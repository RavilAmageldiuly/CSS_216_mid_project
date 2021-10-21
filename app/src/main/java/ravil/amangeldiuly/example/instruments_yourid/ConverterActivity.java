package ravil.amangeldiuly.example.instruments_yourid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class ConverterActivity extends AppCompatActivity {

    String currentCurrency = "USD";

    public static class DownloadCurrency extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "null";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200)
                    throw new RuntimeException("HttpResponseCode: " + responseCode);
                else {
                    String inline = "";
                    Scanner scanner = new Scanner(url.openStream());
                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }
                    scanner.close();
                    result = inline;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        ImageButton backButton = findViewById(R.id.converterBackButton);
        backButton.setOnClickListener(view -> {
            finish();
        });

        EditText sum = findViewById(R.id.editTextNumber);
        AtomicReference<Double> currencyValue = new AtomicReference<>(1.0);

        RadioGroup currencyGroup = findViewById(R.id.currencyRadioGroup);


        TextView textViewUSDval = findViewById(R.id.textViewUSDval);
        TextView textViewEURval = findViewById(R.id.textViewEURval);
        TextView textViewCNYval = findViewById(R.id.textViewCNYval);
        TextView textViewKZTval = findViewById(R.id.textViewKZTval);
        TextView textViewRUBval = findViewById(R.id.textViewRUBval);

        List<TextView> textViewList = new ArrayList<TextView>();
        textViewList.add(textViewUSDval);
        textViewList.add(textViewEURval);
        textViewList.add(textViewCNYval);
        textViewList.add(textViewKZTval);
        textViewList.add(textViewRUBval);

        String[] allCurrencies = {"USD", "EUR", "CNY", "KZT", "RUB"};
        double[] coefficients = new double[5];

        Button button = findViewById(R.id.convertButton);
        button.setOnClickListener(view -> {

            DownloadCurrency downloadCurrency = new DownloadCurrency();
            String res = "";

            if (currencyGroup.getCheckedRadioButtonId() == -1) {

                Toast message = Toast.makeText(getApplicationContext(), "Please, select the currency!", Toast.LENGTH_SHORT);
                message.show();

            } else {

                switch (currencyGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButtonUSD:
                        currentCurrency = "USD";
                        break;
                    case R.id.radioButtonEUR:
                        currentCurrency = "EUR";
                        break;
                    case R.id.radioButtonCNY:
                        currentCurrency = "CNY";
                        break;
                    case R.id.radioButtonKZT:
                        currentCurrency = "KZT";
                        break;
                    case R.id.radioButtonRUB:
                        currentCurrency = "RUB";
                        break;
                }

                try {
                    currencyValue.set(Double.parseDouble(sum.getText().toString()));
                    res = downloadCurrency.execute("https://freecurrencyapi.net/api/v2/latest?apikey=fc31b5c0-2cdf-11ec-9937-63acfe28423f&base_currency=" + currentCurrency).get();
                    JSONParser parser = new JSONParser();
                    JSONObject dataObj = (JSONObject) parser.parse(res);
                    JSONObject currency = (JSONObject) dataObj.get("data");
                    for (int i = 0; i < allCurrencies.length; i++) {
                        if (allCurrencies[i].equals(currentCurrency)) {
                            coefficients[i] = 1;
                        } else {
                            coefficients[i] = (Double) currency.get(allCurrencies[i]);
                        }
                    }

                    for (int i = 0; i < coefficients.length; i++) {
                        textViewList.get(i).setText(String.format("%.2f", (currencyValue.get() * coefficients[i])));
                    }

                } catch (NumberFormatException e) {
                    Toast message = Toast.makeText(getApplicationContext(), "Please, enter the value!", Toast.LENGTH_SHORT);
                    message.show();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {

                }
            }
        });

    }

//    public void checkInternetConnection(View view) {
//        if (!isConnected(this)) {
//            showCustomDialog();
//        }
//    }
//
//    public boolean isConnected(ConverterActivity converterActivity) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) converterActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void showCustomDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(ConverterActivity.this);
//        builder.setMessage("Please connect to the internet first!").setCancelable(false).setPositiveButton("Connect", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//            }
//        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                finish();
//            }
//        });
//    }
}