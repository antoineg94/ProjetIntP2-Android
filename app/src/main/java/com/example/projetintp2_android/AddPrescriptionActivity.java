package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.Manifest;
import android.content.pm.PackageManager;



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.DAO.AlertDAO;
import com.example.projetintp2_android.Classes.DAO.CalendarDAO;
import com.example.projetintp2_android.Classes.DAO.DeviceDAO;
import com.example.projetintp2_android.Classes.DAO.LogDAO;
import com.example.projetintp2_android.Classes.DAO.MedicationDAO;
import com.example.projetintp2_android.Classes.DAO.PrescriptionDAO;
import com.example.projetintp2_android.Classes.Databases.MainDB;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Objects.Medications;
import com.example.projetintp2_android.Classes.Objects.Prescription;
import com.example.projetintp2_android.Classes.RecyclerViewAdapter.AdapterMedications;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;
import com.example.projetintp2_android.Classes.SharedPrefs.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPrescriptionActivity extends AppCompatActivity {

    private static final String PREF_LANGUAGE_KEY = "pref_language";
    TextInputEditText edNameOfPrescription, edDurationOfPrescriptionInDays, edFrequencyOfIntakeInDays, edDateOfPrescription, edDateOfStart, edFrequencyBetweenDosesInHours, edFirstIntakeHour;
    ImageView ivTime, ivDate, ivDateD;
    Context context;
    Button btAjoutM;
    List<Medications> listeMedications;
    MainDB mainDB;
    PrescriptionDAO pdao;
    MedicationDAO mdao;
    CalendarDAO cdao;
    AlertDAO adao;
    DeviceDAO ddao;
    LogDAO ldao;
    AdapterMedications adapter;
    Spinner spinner;
    private static final int PERMISSION_REQUEST_WRITE_CALENDAR = 1;
    String nameOfPrescription, locale, token;
    Date dateOfPrescription, dateOfStart;
    LocalTime firstIntakeHour;
    int durationOfPrescriptionInDays,
            frequencyBetweenDosesInHours, frequencyOfIntakeInDays;


    int hour ;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_prescription);

        String savedLanguage = getSavedLanguage();
        setLocale(savedLanguage);

        createLocalDB();
        LoadUserProfil();
        LoadMedicationsFromLocalDB();

        locale = Locale.getDefault().getLanguage();
        LoadRefsForUI();

        btAjoutM = findViewById(R.id.btAjoutM);
        spinner = findViewById(R.id.spinner);
        context = this;
        btAjoutM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateInputs();
            }
        });
    }

    private void generateEvent() {
        Calendar startTime = Calendar.getInstance();
        //startTime.setTime(dateOfStart); // Date de début de la prescription
        startTime.setTime(dateOfStart);
        startTime.set(Calendar.HOUR_OF_DAY, hour); // Heure de la première prise
        startTime.set(Calendar.MINUTE, minute);

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);

        intent.putExtra(CalendarContract.Events.TITLE, edNameOfPrescription.getText().toString()) // Simple title
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,startTime.getTimeInMillis());


        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_WRITE_CALENDAR) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                generateEvent();
            } else {
                Toast.makeText(this, "Permission refusée, impossible d'ajouter l'événement au calendrier", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void LoadRefsForUI() {
        edNameOfPrescription = findViewById(R.id.edNameOfPrescription);
        edDurationOfPrescriptionInDays = findViewById(R.id.edDurationOfPrescriptionInDays);
        edFrequencyBetweenDosesInHours = findViewById(R.id.edFrequencyBetweenDosesInHours);
        edFrequencyOfIntakeInDays = findViewById(R.id.edFrequencyOfIntakeInDays);
        edDateOfPrescription = findViewById(R.id.edDateOfPrescription);
        edDateOfStart = findViewById(R.id.edDateOfStart);
        edFirstIntakeHour = findViewById(R.id.edFirstIntakeHour);
        ivTime = findViewById(R.id.ivTime);
        ivDate = findViewById(R.id.ivDate);
        ivDateD = findViewById(R.id.ivDateD);

        //edFrequencyBetweenDosesInHours.setOnClickListener(v -> showTimePickerDialog(edFrequencyBetweenDosesInHours));
        ivTime.setOnClickListener(v -> showTimePickerDialog(edFirstIntakeHour));
        ivDate.setOnClickListener(v -> showDatePickerDialog(edDateOfPrescription));
        ivDateD.setOnClickListener(v -> showDatePickerDialog(edDateOfStart));


    }

    private void createLocalDB() {
        mainDB = MainDB.getDatabase(this);
        mdao = mainDB.mdao();
        pdao = mainDB.pdao();
    }

    private void LoadUserProfil() {
        token = SharedPrefManager.getInstance(this).getToken();
    }

    private void LoadMedicationsFromLocalDB() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    listeMedications = mdao.getAfficherM();
                    Log.d("Medications", mdao.getAfficherM().toString());

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Erreur lors de la récupération des médicaments", Toast.LENGTH_SHORT).show();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateSpinner();
                    }
                });
            }
        });
    }

    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
            String date = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;
            editText.setText(date);
        }, year, month, dayOfMonth);


        datePickerDialog.show();
    }


    private void showDatePickerDialog(EditText editText, boolean hasMaxDate, boolean hasMinDate, long minDate, long maxDate) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    String date = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;
                    editText.setText(date);
                }, year, month, dayOfMonth);

        if (hasMaxDate) {
            datePickerDialog.getDatePicker().setMaxDate(maxDate);
        }
        if (hasMinDate) {
            datePickerDialog.getDatePicker().setMinDate(minDate);
        }
        datePickerDialog.show();
    }

    private void showTimePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, selectedMinute) -> {
            String time = hourOfDay + ":" + selectedMinute;
            editText.setText(time);
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void populateSpinner() {
        ArrayAdapter<Medications> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listeMedications);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private void addPrescriptionToLocalDB(Prescription prescription) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Medications medication = mdao.getMedicationById(spinner.getSelectedItemPosition());
                pdao.insertPrescription(prescription);
                Toast.makeText(context, "Prescription ajoutée", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(context, "Prescription ajoutée", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void addPrescriptionToDistantDB() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Log.d("ADDP_locale", locale);
        Log.d("ADDP_Token", "Bearer " + token);
        Log.d("ADDP_Name", nameOfPrescription);
        Log.d("ADDP_dateOfPrescription", dateOfPrescription.toString());
        Log.d("ADDP_dateOfStart", dateOfStart.toString());
        Log.d("ADDP_durationOfPrescriptionInDays", String.valueOf(durationOfPrescriptionInDays));
        Log.d("ADDP_frequencyBetweenDosesInHours", String.valueOf(frequencyBetweenDosesInHours));
        Log.d("ADDP_frequencyOfIntakeInDays", String.valueOf(frequencyOfIntakeInDays));
        Log.d("ADDP_firstIntakeHour", firstIntakeHour.toString());
        Log.d("ADDP_medication_id", String.valueOf(listeMedications.get(spinner.getSelectedItemPosition()).getId()));


        Call<APIResponse> call = api.postPrescriptions(locale, "Bearer " + token, nameOfPrescription, dateOfPrescription,
                dateOfStart, durationOfPrescriptionInDays, frequencyBetweenDosesInHours, frequencyOfIntakeInDays,
                firstIntakeHour, listeMedications.get(spinner.getSelectedItemPosition()).getId());
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getStatus().equals("error")) {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("AddPrescriptionError", response.body().getMessage());
                    return;
                }

                if (response.body().getData() != null) {
                    Prescription prescription = response.body().getData().getPrescriptions().get(0);
                    addPrescriptionToLocalDB(prescription);
                } else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("AddPrescriptionFailure", t.getMessage());
                Toast.makeText(context, "Erreur lors de l'ajout de la prescription", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle("Ajouter une prescription");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (item.getItemId() == R.id.itGestionMedic) {
            Intent intent = new Intent(this, GestionPrescriptionActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDevicesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_language_switch) {
            Switch languageSwitch = findViewById(R.id.languageSwitch);
            boolean isChecked = languageSwitch.isChecked();

            if (isChecked) {
                saveLanguage("fr");
            } else {
                saveLanguage("en");
            }

            recreate();
            return true;
        } else if (id == R.id.itDeconnexion) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveLanguage(String language) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_LANGUAGE_KEY, language);
        editor.apply();
    }

    private String getSavedLanguage() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return preferences.getString(PREF_LANGUAGE_KEY, "fr");
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        Context context = createConfigurationContext(config);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        /*Locale locale = new Locale(languageCode);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // Redémarrez votre activité pour appliquer les modifications
        recreate();*/
    }

    private void ValidateInputs() {

        if (verifyEmptyInput(edNameOfPrescription) == 1 || verifyEmptyInput(edDurationOfPrescriptionInDays) == 1 || verifyEmptyInput(edFrequencyBetweenDosesInHours) == 1 || verifyEmptyInput(edFrequencyOfIntakeInDays) == 1 || verifyEmptyInput(edFirstIntakeHour) == 1 || verifyEmptyInput(edDateOfPrescription) == 1 || verifyEmptyInput(edDateOfStart) == 1) {
            return;
        }

        nameOfPrescription = edNameOfPrescription.getText().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            firstIntakeHour = LocalTime.parse(edFirstIntakeHour.getText().toString(), formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erreur lors de la conversion de l'heure", Toast.LENGTH_SHORT).show();
        }

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateOfPrescription = new Date(sdf2.parse(edDateOfPrescription.getText().toString()).getTime());
            dateOfStart = new Date(sdf2.parse(edDateOfStart.getText().toString()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erreur lors de la conversion de la date", Toast.LENGTH_SHORT).show();
        }

        durationOfPrescriptionInDays = Integer.parseInt(edDurationOfPrescriptionInDays.getText().toString());
        frequencyBetweenDosesInHours = Integer.parseInt(edFrequencyBetweenDosesInHours.getText().toString());
        frequencyOfIntakeInDays = Integer.parseInt(edFrequencyOfIntakeInDays.getText().toString());

        if (durationOfPrescriptionInDays > 365) {
            edDurationOfPrescriptionInDays.setError("La durée maximale de la prescription est de 365 jours");
            return;
        }

        if (frequencyBetweenDosesInHours > 24) {
            edFrequencyBetweenDosesInHours.setError("La fréquence maximale entre les doses est de 24 heures");
            return;
        }

        if (frequencyOfIntakeInDays > 31) {
            edFrequencyOfIntakeInDays.setError("La fréquence maximale d'intake est de 31 jours");
            return;
        }

        if (dateOfPrescription.after(dateOfStart)) {
            edDateOfPrescription.setError("La date de prescription doit être antérieure à la date de début");
            edDateOfStart.setError("La date de début doit être postérieure à la date de prescription");
            Toast.makeText(context, "La date de prescription doit être antérieure à la date de début", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            edDateOfPrescription.setError(null);
            edDateOfStart.setError(null);
        }
        if (verifyNumberInputForPositiveIntegers(edDurationOfPrescriptionInDays) == 1 || verifyNumberInputForPositiveIntegers(edFrequencyBetweenDosesInHours) == 1 || verifyNumberInputForPositiveIntegers(edFrequencyOfIntakeInDays) == 1) {
            return;
        }


        addPrescriptionToDistantDB();
        generateEvent();

    }


    private int verifyNumberInputForPositiveIntegers(EditText editText) {
        if (Integer.parseInt(editText.getText().toString()) < 0) {
            editText.setError("Le nombre ne peut pas être négatif");
            Toast.makeText(context, "Veuillez entrer une valeur correcte", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }

    private int verifyEmptyInput(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError("Le champ est requis");
            String editTextName = editText.getHint().toString();
            String fillInputMessage = getResources().getString(R.string.fillTheInput);
            Toast.makeText(context, fillInputMessage + editTextName, Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }

    private void logout() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.logout(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getStatus().equals("success")) {
                    SharedPrefManager.getInstance(AddPrescriptionActivity.this).clear();
                    Intent intent = new Intent(AddPrescriptionActivity.this, LoginActivitytest.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("logout", t.getMessage());
                Toast.makeText(AddPrescriptionActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
