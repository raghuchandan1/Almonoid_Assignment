package com.example.almonoidassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almonoidassignment.model.PersonalInformation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Personal Information");
        setSupportActionBar(toolbar);
        ImageView person_icon = findViewById(R.id.person_imageView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this){
            //@Override
            //public boolean canScrollVertically() {
                //return false;
            //}
        };
        String[] questions = {
                "Question 1",
                "Question 2",
                "Question 3"
        };
        YesNoAdapter yesNoAdapter = new YesNoAdapter(this, questions);
        RecyclerView questionsRecyclerView = findViewById(R.id.questions_recyclerView);
        questionsRecyclerView.hasFixedSize();
        questionsRecyclerView.setLayoutManager(layoutManager);
        questionsRecyclerView.setAdapter(yesNoAdapter);

        LinearLayoutManager tagsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        String[] tags = {
                "TAGS",
                "Tag 1",
                "Tag 2"
        };
        TagsAdapter tagsAdapter = new TagsAdapter(this, tags);
        RecyclerView tagsRecyclerView = findViewById(R.id.tags_recyclerview);
        tagsRecyclerView.setLayoutManager(tagsLayoutManager);
        tagsRecyclerView.setAdapter(tagsAdapter);
        tagsRecyclerView.hasFixedSize();

        ImageButton birthdatebutton = findViewById(R.id.birthdate_imageButton);
        birthdatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });


        PersonalInformation pi =new PersonalInformation();
        pi.nickname = ((EditText) findViewById(R.id.nickname_editText)).getText().toString();
        pi.about = ((EditText) findViewById(R.id.about_editText)).getText().toString();
        pi.birthdate = ((EditText) findViewById(R.id.birthdate_editText)).getText().toString();
        pi.relationshipStatus = ((Spinner) findViewById(R.id.relationship_spinner)).getSelectedItem().toString();
        pi.hobbies = ((EditText) findViewById(R.id.hobbies_editText)).getText().toString();
        //Scanner sc = new Scanner(hobbiesAll);
        //String hobbies[] = new String[];
        //sc.useDelimiter(",");
        //int i=-1;
        //while(sc.hasNext()){

        //}
        pi.interests = ((EditText) findViewById(R.id.interests_editText)).getText().toString();
        //final String json="{\"Nickname\""+": \""+nickname+"\"}";
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        final String jsonString = gson.toJson(pi);
        Button save= findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,jsonString,LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        currentDateString=simpleDateFormat.format(c.getTime());
        TextView birthdatetextView = findViewById(R.id.birthdate_editText);
        birthdatetextView.setText(currentDateString);
    }
}